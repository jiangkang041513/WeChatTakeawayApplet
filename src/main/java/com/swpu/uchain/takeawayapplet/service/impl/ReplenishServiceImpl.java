package com.swpu.uchain.takeawayapplet.service.impl;

import com.swpu.uchain.takeawayapplet.VO.GreenBaseListVO;
import com.swpu.uchain.takeawayapplet.VO.GreensOrderListVO;
import com.swpu.uchain.takeawayapplet.VO.GreensOrderVO;
import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.dao.GreenBaseExpandMapper;
import com.swpu.uchain.takeawayapplet.dao.GreenBaseMapper;
import com.swpu.uchain.takeawayapplet.dao.GreensOrderMapper;
import com.swpu.uchain.takeawayapplet.dto.GreenBaseDTO;
import com.swpu.uchain.takeawayapplet.dto.InsertGreensDTO;
import com.swpu.uchain.takeawayapplet.entity.GreenBase;
import com.swpu.uchain.takeawayapplet.entity.GreenBaseExpand;
import com.swpu.uchain.takeawayapplet.entity.GreensOrder;
import com.swpu.uchain.takeawayapplet.enums.ResultEnum;
import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;
import com.swpu.uchain.takeawayapplet.form.UpdateGreenBaseForm;
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

    @Autowired
    private GreenBaseExpandMapper expandMapper;

    @Override
    public ResultVO getGreensList() {
        List<GreenBaseListVO> vos = baseMapper.selectAllByPid(0);
        List<GreenBaseListVO> result = new ArrayList<>();

        for (GreenBaseListVO vo : vos) {
            List<GreenBaseDTO> bases = baseMapper.selectBaseAndExPandByPid(vo.getId());
            for (GreenBaseDTO base : bases) {
                GreenBaseExpand expand = expandMapper.selectByBaseId(base.getId());
                if (expand != null) {
                    base.setContact(expand.getContact());
                    base.setPhoneNum(expand.getPhoneNum());
                    base.setAddress(expand.getAddress());
                }
            }
            vo.setBaseList(bases);
            result.add(vo);
        }
        return ResultUtil.success(result);
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
    public ResultVO insertGreenBase(List<InsertGreenBaseForm> form) {
        if (form != null) {
            List<InsertGreenBaseForm> result = new ArrayList<>();
            for (InsertGreenBaseForm baseForm : form) {

                GreenBase base = new GreenBase();
                if (baseMapper.selectByPidAndProductName(baseForm.getPId(),
                        baseForm.getProductName()) != null) {
                    return ResultUtil.error(baseForm.getProductName() + "," + "已经存在");
                }

                BeanUtils.copyProperties(baseForm, base);
                if (baseMapper.insert(base) != 1) {
                    return ResultUtil.error(base.getProductName() + "," + "添加失败");
                }

                GreenBaseExpand expand = new GreenBaseExpand();
                BeanUtils.copyProperties(baseForm, expand);
                GreenBase a = baseMapper.selectByPidAndProductName(base.getpId(), base.getProductName());
                expand.setGreensBaseId(a.getId());

                if (expandMapper.insert(expand) != 1) {
                    return ResultUtil.error(expand.getContact() + "," + "联系人信息添加失败");
                }
                result.add(baseForm);
            }
            return ResultUtil.success(result);
        }
        return ResultUtil.error(ResultEnum.INSERT_MSG_CANNOT_NULL);
    }

    @Override
    public ResultVO updateGreenBase(UpdateGreenBaseForm form) {
        if (baseMapper.selectByPrimaryKey(form.getId()) == null) {
            return ResultUtil.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        GreenBase greenBase = new GreenBase();
        BeanUtils.copyProperties(form, greenBase);
        if (baseMapper.updateByPrimaryKey(greenBase) == 1) {
            return ResultUtil.success(greenBase);
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteGreenBase(Integer id) {
        if (baseMapper.selectByPrimaryKey(id) == null) {
            return ResultUtil.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (baseMapper.deleteByPrimaryKey(id) == 1) {
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO insertGreenBaseType(String baseType) {
        if (baseMapper.selectByPidAndProductName(0, baseType) != null) {
            return ResultUtil.error(ResultEnum.BASE_TYPE_ALREADY_EXIST);
        }
        GreenBase base = new GreenBase();
        base.setpId(0);
        base.setProductName(baseType);
        if (baseMapper.insert(base) == 1) {
            base = baseMapper.selectByPidAndProductName(0, base.getProductName());
            return ResultUtil.success(base);
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getGreensTypeList() {
        List<GreenBase> result = baseMapper.selectByPid(0);
        return ResultUtil.success(result);
    }
}
