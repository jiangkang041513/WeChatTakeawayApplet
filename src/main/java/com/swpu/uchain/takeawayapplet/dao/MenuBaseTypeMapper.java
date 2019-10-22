package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseTypeVO;
import com.swpu.uchain.takeawayapplet.dto.MenuBaseDTO;
import com.swpu.uchain.takeawayapplet.entity.MenuBaseType;
import java.util.List;

public interface MenuBaseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuBaseType record);

    MenuBaseType selectByPrimaryKey(Integer id);

    List<MenuBaseType> selectAll();

    List<MenuBaseTypeVO> selectAllTypeAndBaseNum();

    int updateByPrimaryKey(MenuBaseType record);

    List<MenuBaseDTO> selectAllMenuBaseType();

    MenuBaseType selectByBaseName(String menuBaseType);

}