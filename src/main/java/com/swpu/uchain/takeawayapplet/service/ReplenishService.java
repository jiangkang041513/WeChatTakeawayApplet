package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;
import com.swpu.uchain.takeawayapplet.form.UpdateGreenBaseForm;

import java.util.List;

/**
 * @author hobo
 * @description
 */
public interface ReplenishService {

    ResultVO getGreensList();

    ResultVO insertGreens(InsertGreensForm form);

    ResultVO getAllOrderByPreorderId(String preorderId);

    ResultVO insertGreenBase(List<InsertGreenBaseForm> form);

    ResultVO updateGreenBase(UpdateGreenBaseForm form);

    ResultVO deleteGreenBase(Integer id);

    ResultVO insertGreenBaseType(String baseType);

    ResultVO getGreensTypeList();
}
