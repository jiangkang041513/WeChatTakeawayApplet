package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.entity.GreenBase;
import java.util.List;

public interface GreenBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GreenBase record);

    GreenBase selectByPrimaryKey(Integer id);

    List<GreenBase> selectAll();

    int updateByPrimaryKey(GreenBase record);

    List<GreenBase> selectAllByPid(String pid);

    GreenBase selectByPidAndProductName(Integer pid, String productName);
}