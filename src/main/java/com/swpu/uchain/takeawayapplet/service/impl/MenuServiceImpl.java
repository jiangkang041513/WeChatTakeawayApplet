package com.swpu.uchain.takeawayapplet.service.impl;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseAndClickNumVO;
import com.swpu.uchain.takeawayapplet.VO.MenuBaseVO;
import com.swpu.uchain.takeawayapplet.VO.MenuListVO;
import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.dao.MenuBaseMapper;
import com.swpu.uchain.takeawayapplet.dao.MenuBaseTypeMapper;
import com.swpu.uchain.takeawayapplet.dao.MenuOrderMapper;
import com.swpu.uchain.takeawayapplet.dto.CreatMenuOrderDTO;
import com.swpu.uchain.takeawayapplet.dto.MenuBaseDTO;
import com.swpu.uchain.takeawayapplet.dto.MenuDTO;
import com.swpu.uchain.takeawayapplet.entity.MenuBase;
import com.swpu.uchain.takeawayapplet.entity.MenuBaseType;
import com.swpu.uchain.takeawayapplet.entity.MenuOrder;
import com.swpu.uchain.takeawayapplet.enums.ResultEnum;
import com.swpu.uchain.takeawayapplet.form.GetClickForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseTypeForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuForm;
import com.swpu.uchain.takeawayapplet.service.MenuService;
import com.swpu.uchain.takeawayapplet.util.RandomUtil;
import com.swpu.uchain.takeawayapplet.util.ResultUtil;
import com.swpu.uchain.takeawayapplet.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuBaseMapper baseMapper;

    @Autowired
    private MenuBaseTypeMapper baseTypeMapper;

    @Autowired
    private MenuOrderMapper menuOrderMapper;

    private static Integer LUNCH = 0;
    private static Integer DINNER = 1;

    public List<MenuBaseType> getAllMenuBaseType() {
        return baseTypeMapper.selectAll();
    }

    @Override
    public ResultVO selectAllMenuBase() {
        List<MenuBaseDTO> dtos = baseTypeMapper.selectAllMenuBaseType();
        List<MenuBaseDTO> result = new ArrayList<>();
        for (MenuBaseDTO dto : dtos) {
            List<MenuBaseVO> vos = baseMapper.selectByBaseTypeId(dto.getId());
            if (vos != null) {
                dto.setMenuBaseVOList(vos);
            }
            result.add(dto);
        }
        return ResultUtil.success(result);
    }

    @Override
    public ResultVO insertMenuBaseType(InsertMenuBaseTypeForm typeForm) {
        if (baseTypeMapper.selectByBaseName(typeForm.getMenuBaseType()) != null) {
            return ResultUtil.error(ResultEnum.BASE_TYPE_ALREADY_EXIST);
        }
        MenuBaseType baseType = new MenuBaseType();
        BeanUtils.copyProperties(typeForm, baseType);
        if (baseTypeMapper.insert(baseType) == 1) {
            return ResultUtil.success(baseType);
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO insertMenuBase(InsertMenuBaseForm baseForm) {
        MenuBase base = baseMapper.selectByProductName(baseForm.getProductName());
        if (base != null) {
            return ResultUtil.error(ResultEnum.PRODUCT_EXIST);
        }
        BeanUtils.copyProperties(baseForm, base);
        if (baseMapper.insert(base) == 1) {
            return ResultUtil.success(base);
        }
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO insertMenu(InsertMenuForm menuForm) {
        if (menuForm != null) {
            // 生成10位随机数作为订单号
            List<MenuOrder> orders = new ArrayList<>();
            String orderId = RandomUtil.getRandomStringByLength(10);
            String time = TimeUtil.stampToTime(menuForm.getCreatTime());
            for (CreatMenuOrderDTO dto : menuForm.getDto()) {
                for (Integer baseId : dto.getMenuBaseId()) {
                    MenuOrder order = new MenuOrder();
                    order.setOrderId(orderId);
                    order.setFoodType(dto.getFoodType());
                    order.setMenuBaseId(baseId);
                    order.setCreatTime(time);
                    if (menuOrderMapper.insert(order) == 1) {
                        baseMapper.updateBaseClickNum(order.getMenuBaseId());
                        orders.add(order);
                    }
                }
            }
            return ResultUtil.success(orders);
        }
        return ResultUtil.error(ResultEnum.INSERT_MSG_CANNOT_NULL);
    }

    @Override
    public ResultVO selectMenuByCreatTime(long date) {
        MenuListVO result = new MenuListVO();
        List<MenuDTO> dtoList = new ArrayList<>();
        String realDate = TimeUtil.stampToTime(date);
        String orderId = menuOrderMapper.selectByCreatTime(realDate);

        result.setCreatTime(realDate);
        result.setOrderId(orderId);

        MenuDTO lunchDTO = getDto(orderId, LUNCH);
        MenuDTO dinnerDTO = getDto(orderId, DINNER);

        dtoList.add(lunchDTO);
        dtoList.add(dinnerDTO);

        result.setFoodList(dtoList);
        return ResultUtil.success(result);
    }

    @Override
    public ResultVO deleteOrderByDate(long date) {
        String realDate = TimeUtil.stampToTime(date);
        String beforeDate = TimeUtil.addCurrentTime(date);
        String orderId = menuOrderMapper.selectIdByCreatTime(realDate, beforeDate);
        if (orderId == null) {
            return ResultUtil.error(ResultEnum.ORDER_NOT_FOUND);
        }
        menuOrderMapper.deleteByOrderId(orderId);
        return ResultUtil.success();
    }

    @Override
    public ResultVO getHighestClickMenuBase(GetClickForm form) {
        String beginTime = TimeUtil.stampToTime(form.getBeginDate());
        String endTime = TimeUtil.addCurrentTime(form.getEndDate());
        List<MenuBaseAndClickNumVO> vo = menuOrderMapper.getClickNum(beginTime,endTime);
        return ResultUtil.success(vo);

    }


    private MenuDTO getDto(String orderId, Integer foodType) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setFoodType(foodType);
        List<MenuOrder> menuOrderList = menuOrderMapper.selectByOrderId(orderId, foodType);
        List<MenuBaseVO> baseVOList = new ArrayList<>();
        for (MenuOrder order : menuOrderList) {
            MenuBaseVO vo = baseMapper.selectById(order.getMenuBaseId());
            baseVOList.add(vo);
        }
        menuDTO.setVos(baseVOList);
        return menuDTO;
    }
}
