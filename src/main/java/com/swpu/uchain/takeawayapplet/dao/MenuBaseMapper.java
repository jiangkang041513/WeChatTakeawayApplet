package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseVO;
import com.swpu.uchain.takeawayapplet.entity.MenuBase;
import java.util.List;

public interface MenuBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuBase record);

    MenuBase selectByPrimaryKey(Integer id);

    List<MenuBase> selectAll();

    int updateByPrimaryKey(MenuBase record);

    List<MenuBaseVO> selectByBaseTypeId(Integer baseTypeId);

    MenuBaseVO selectById(Integer id);

    MenuBase selectByProductName(String productName);

    int updateBaseClickNum(Integer id);

    List<MenuBase> getListByTypeId(Integer id);
}