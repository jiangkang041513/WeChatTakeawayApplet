package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.entity.MenuBaseType;
import com.swpu.uchain.takeawayapplet.form.GetClickForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseTypeForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuForm;

import java.util.List;

/**
 * @author hobo
 * @description
 */
public interface MenuService {


    ResultVO selectAllMenuBase();

    ResultVO insertMenuBaseType(InsertMenuBaseTypeForm typeForm);

    ResultVO insertMenuBase(InsertMenuBaseForm baseForm);

    ResultVO insertMenu(InsertMenuForm menuForm);

    ResultVO selectMenuByCreatTime(long date);

    ResultVO deleteOrderByDate(long date);

    ResultVO getHighestClickMenuBase(GetClickForm form);
}
