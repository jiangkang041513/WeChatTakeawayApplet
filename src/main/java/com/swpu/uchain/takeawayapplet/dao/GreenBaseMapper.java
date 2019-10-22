package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.VO.GreenBaseListVO;
import com.swpu.uchain.takeawayapplet.dto.GreenBaseDTO;
import com.swpu.uchain.takeawayapplet.entity.GreenBase;

import java.util.List;

public interface GreenBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GreenBase record);

    GreenBase selectByPrimaryKey(Integer id);

    List<GreenBase> selectAll();

    int updateByPrimaryKey(GreenBase record);

    List<GreenBaseListVO> selectAllByPid(Integer pid);

    List<GreenBase> selectByPid(Integer pid);

    GreenBase selectByPidAndProductName(Integer pid, String productName);

    List<GreenBaseDTO> selectBaseAndExPandByPid(Integer pid);

}