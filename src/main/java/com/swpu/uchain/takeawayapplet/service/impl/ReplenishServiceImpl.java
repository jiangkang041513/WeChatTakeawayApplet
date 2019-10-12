package com.swpu.uchain.takeawayapplet.service.impl;

import com.swpu.uchain.takeawayapplet.VO.GreensOrderListVO;
import com.swpu.uchain.takeawayapplet.VO.GreensOrderVO;
import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.dao.GreenBaseMapper;
import com.swpu.uchain.takeawayapplet.dao.GreensOrderMapper;
import com.swpu.uchain.takeawayapplet.dto.InsertGreensDTO;
import com.swpu.uchain.takeawayapplet.entity.GreenBase;
import com.swpu.uchain.takeawayapplet.entity.GreensOrder;
import com.swpu.uchain.takeawayapplet.enums.ResultEnum;
import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;
import com.swpu.uchain.takeawayapplet.service.ReplenishService;
import com.swpu.uchain.takeawayapplet.util.RandomUtil;
import com.swpu.uchain.takeawayapplet.util.ResultUtil;
import com.swpu.uchain.takeawayapplet.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
public class ReplenishServiceImpl implements ReplenishService {

    @Autowired
    private GreenBaseMapper baseMapper;

    @Autowired
    private GreensOrderMapper orderMapper;

    @Override
    public ResultVO getGreensList(String pid) {
        List<GreenBase> greens = baseMapper.selectAllByPid(pid);
        return ResultUtil.success(greens);
    }

    @Override
    public ResultVO insertGreens(InsertGreensForm form) {
        if (form != null) {
            String time = TimeUtil.getNowTime();
            String id = RandomUtil.getRandomStringByLength(10);
            List<GreensOrder> orders = new ArrayList<>();
            for (InsertGreensDTO dto : form.getDtos()) {
                GreensOrder order = new GreensOrder();
                BeanUtils.copyProperties(dto, order);
                order.setPreorderId(id);
                order.setCreatTime(time);
                order.setTotalPrice(order.getUnitPrice().multiply(new BigDecimal(order.getGreensNum())));
                if (orderMapper.insert(order) == 1) {
                    orders.add(order);
                }
            }
            if (orders.size() > 0) {
                return ResultUtil.success(orders);
            }
            return ResultUtil.error(ResultEnum.SERVER_ERROR);
        }
        return ResultUtil.error(ResultEnum.INSERT_MSG_CANNOT_NULL);
    }

    @Override
    public ResultVO getAllOrderByPreorderId(String preorderId) {
        List<GreensOrderVO> vos = orderMapper.selectAllByPreorderId(preorderId);
        if (vos != null) {
            List<GreensOrderVO> voList = new ArrayList<>();
            GreensOrderListVO result = new GreensOrderListVO();
            for (GreensOrderVO vo : vos) {
                vo.setProductName(baseMapper.selectByPrimaryKey(vo.getGreenProductId())
                        .getProductName());
                voList.add(vo);
                result.setPreorderId(preorderId);
                result.setCreatTime(vo.getCreatTime());
            }
            result.setOrderVos(voList);
            return ResultUtil.success(result);
        }
        return ResultUtil.error(ResultEnum.ORDER_NOT_FOUND);
    }

    @Override
    public ResultVO insertGreenBase(InsertGreenBaseForm form) {
        if (baseMapper.selectByPidAndProductName(form.getPid(),form.getProductName())!=null){
            return ResultUtil.error(ResultEnum.PRODUCT_EXIST);
        }
        GreenBase greenBase = new GreenBase();
        BeanUtils.copyProperties(form,greenBase);
        if (baseMapper.insert(greenBase)==1){
            return ResultUtil.success(greenBase);
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }
}
