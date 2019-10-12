package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;

/**
 * @author hobo
 * @description
 */
public interface ReplenishService {

    ResultVO getGreensList(String pid);

    ResultVO insertGreens(InsertGreensForm form);

    ResultVO getAllOrderByPreorderId(String preorderId);

    ResultVO insertGreenBase(InsertGreenBaseForm form);
}
