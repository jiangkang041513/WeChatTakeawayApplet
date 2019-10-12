package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseAndClickNumVO;
import com.swpu.uchain.takeawayapplet.VO.MenuListVO;
import com.swpu.uchain.takeawayapplet.entity.MenuOrder;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MenuOrderMapper {
    int deleteByPrimaryKey(@Param("orderId") String orderId, @Param("menuBaseId") Integer menuBaseId, @Param("foodType") Integer foodType);

    int insert(MenuOrder record);

    MenuOrder selectByPrimaryKey(@Param("orderId") String orderId, @Param("menuBaseId") Integer menuBaseId, @Param("foodType") Integer foodType);

    List<MenuOrder> selectAll();

    int updateByPrimaryKey(MenuOrder record);

    String selectByCreatTime(String realDate);

    List<MenuOrder> selectByOrderId(String orderId, Integer foodType);

    void deleteByOrderId(String orderId);

    String selectIdByCreatTime(String creatTime, String BeforeCreatTime);


    List<MenuBaseAndClickNumVO> getClickNum(String beginTime, String endTime);
}