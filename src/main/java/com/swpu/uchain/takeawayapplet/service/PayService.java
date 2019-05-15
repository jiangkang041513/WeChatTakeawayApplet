package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.form.PayForm;
import com.swpu.uchain.takeawayapplet.form.RefundForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {

    /**
     * 发起预支付请求
     *
     * @param payForm
     * @param request
     * @return
     */
    ResultVO creat(PayForm payForm, HttpServletRequest request);

    /**
     * 发起退款请求
     *
     * @param refundForm
     * @return
     */
    ResultVO refund(RefundForm refundForm);

    /**
     * 支付回调
     *
     * @param request
     * @param response
     * @return
     */
    String notify(HttpServletRequest request, HttpServletResponse response);

    /**
     * 退款回调
     *
     * @param request
     * @param response
     * @return
     */
    String refundNotify(HttpServletRequest request, HttpServletResponse response);

}
