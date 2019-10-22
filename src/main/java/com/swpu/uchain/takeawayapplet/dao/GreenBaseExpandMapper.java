package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.entity.GreenBaseExpand;
import java.util.List;

public interface GreenBaseExpandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GreenBaseExpand record);

    GreenBaseExpand selectByPrimaryKey(Integer id);

    List<GreenBaseExpand> selectAll();

    int updateByPrimaryKey(GreenBaseExpand record);

    GreenBaseExpand selectByBaseId(Integer id);
}