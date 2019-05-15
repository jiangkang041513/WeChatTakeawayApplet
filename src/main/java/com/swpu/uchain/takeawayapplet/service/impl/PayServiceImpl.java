package com.swpu.uchain.takeawayapplet.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.config.WeChatProperties;
import com.swpu.uchain.takeawayapplet.dto.OrderDTO;
import com.swpu.uchain.takeawayapplet.enums.PayStatusEnum;
import com.swpu.uchain.takeawayapplet.enums.ResultEnum;
import com.swpu.uchain.takeawayapplet.exception.GlobalException;
import com.swpu.uchain.takeawayapplet.form.PayForm;
import com.swpu.uchain.takeawayapplet.form.RefundForm;
import com.swpu.uchain.takeawayapplet.service.OrderService;
import com.swpu.uchain.takeawayapplet.service.PayService;
import com.swpu.uchain.takeawayapplet.util.RandomUtil;
import com.swpu.uchain.takeawayapplet.util.ResultUtil;
import com.swpu.uchain.takeawayapplet.util.TimeUtil;
import com.swpu.uchain.takeawayapplet.util.wechatUtil.GetOpenIdUtil;
import com.swpu.uchain.takeawayapplet.util.wechatUtil.PayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName PayServiceImpl
 * @Author hobo
 * @Date 19-3-21 下午8:11
 * @Description
 **/
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WeChatProperties properties;

    @Autowired
    private GetOpenIdUtil getOpenIdUtil;

    @Autowired
    private OrderService orderService;

    @Override
    public ResultVO creat(PayForm payForm, HttpServletRequest request) {
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            //组装参数
            orderRequest.setBody(properties.getTitle());
            orderRequest.setOutTradeNo(payForm.getId() + "");
            orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(payForm.getOrderAmount())));
            orderRequest.setOpenid(getOpenIdUtil.getOpenId(payForm.getCode()));
            orderRequest.setSpbillCreateIp(PayUtil.getIpAddr(request));
            orderRequest.setTimeStart(TimeUtil.getWxpayTime());
            orderRequest.setTimeExpire(TimeUtil.getWxpayTime());
            orderRequest.setNotifyUrl(properties.getNotifyUrl());
            return ResultUtil.success(wxPayService.createOrder(orderRequest));
        } catch (WxPayException e) {
            log.error("微信支付失败！订单号:{},失败原因:{}", payForm.getId(), e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.PAY_FILE);
        }
    }

    @Override
    public String notify(HttpServletRequest request, HttpServletResponse response) {
        String xmlResult = null;
        try {
            xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);
            //结果正确
            String orderId = result.getOutTradeNo();
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());

            OrderDTO orderDTO = orderService.findOrder(Long.valueOf(orderId));
            if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
                throw new GlobalException(ResultEnum.PAY_STATUS_ERROR);
            }
            if (!String.valueOf(orderDTO.getOrderAmount()).equals(totalFee)) {
                throw new GlobalException(ResultEnum.AMOUNT_ERROR);
            }
            //修改订单状态
            if (!orderService.paidOrder(orderDTO)) {
                throw new GlobalException(ResultEnum.PARAMETER_NOT_MATCH);
            }
            return WxPayNotifyResponse.success("处理成功");
        } catch (Exception e) {
            log.error("微信支付回调失败！原因:{}", e.getMessage());
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    @Override
    public ResultVO refund(RefundForm refundForm) {

        try {
            WxPayRefundRequest refundRequest = new WxPayRefundRequest();
            refundRequest.setOutTradeNo(refundForm.getOrderNO() + "");
            //生成64位退款单号
            String refundNo = RandomUtil.getRandomStringByLength(64);
            refundRequest.setOutRefundNo(refundNo);
            log.info("退款单号为: {}", refundNo);

            OrderDTO orderDTO = orderService.findOrder(refundForm.getOrderNO());
            refundRequest.setTotalFee(orderDTO.getOrderAmount().intValue());
            refundRequest.setRefundFee(BaseWxPayRequest.yuanToFen(String.valueOf(refundForm.getTotalFee())));
            refundRequest.setNotifyUrl(properties.getRefundNotifyUrl());
            return ResultUtil.success(wxPayService.refund(refundRequest));

        } catch (WxPayException e) {
            log.error("微信退款失败！退款订单号:{},失败原因:{}", refundForm.getOrderNO(), e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.REFUND_ERROR);
        }
    }

    @Override
    public String refundNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayRefundNotifyResult result = wxPayService.parseRefundNotifyResult(xmlResult);
            WxPayRefundNotifyResult.ReqInfo reqInfo = result.getReqInfo();
            String outTradeNo = reqInfo.getOutTradeNo();
            OrderDTO orderDTO = orderService.findOrder(Long.valueOf(outTradeNo));
            String totalFee = BaseWxPayResult.fenToYuan(reqInfo.getTotalFee());

            if (!totalFee.equals(String.valueOf(orderDTO.getOrderAmount()))) {
                return WxPayNotifyResponse.fail("金额不匹配,无法退款");
            }
            return WxPayNotifyResponse.success("处理成功");
        } catch (Exception e) {
            log.error("微信支付回调失败！原因:{}", e.getMessage());
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

}
