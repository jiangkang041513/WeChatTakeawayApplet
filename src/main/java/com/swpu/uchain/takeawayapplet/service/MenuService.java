package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.entity.MenuBaseType;
import com.swpu.uchain.takeawayapplet.form.*;

import java.util.List;

/**
 * @author hobo
 * @description
 */
public interface MenuService {


    ResultVO selectAllMenuBase();

    ResultVO insertMenuBaseType(InsertMenuBaseTypeForm typeForm);

    ResultVO insertMenuBase(List<InsertMenuBaseForm> forms);

    ResultVO insertMenu(InsertMenuForm menuForm);

    ResultVO selectMenuByCreatTime(long date);

    ResultVO deleteOrderByDate(long date);

    ResultVO getHighestClickMenuBase(GetClickForm form);

    ResultVO selectAllMenuBaseType();

    ResultVO deleteBaseType(Integer id);

    ResultVO updateBaseType(MenuBaseType type);

    ResultVO deleteMenuBase(Integer id);

    ResultVO updateMenuBaseName(UpdateMenuBaseForm form);
}
