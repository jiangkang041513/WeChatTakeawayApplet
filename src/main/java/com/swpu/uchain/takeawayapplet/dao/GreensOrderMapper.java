package com.swpu.uchain.takeawayapplet.dao;

import com.swpu.uchain.takeawayapplet.VO.GreensOrderVO;
import com.swpu.uchain.takeawayapplet.entity.GreensOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GreensOrderMapper {
    int deleteByPrimaryKey(@Param("preorderId") String preorderId, @Param("greenProductId") Integer greenProductId);

    int insert(GreensOrder record);

    GreensOrder selectByPrimaryKey(@Param("preorderId") String preorderId, @Param("greenProductId") Integer greenProductId);

    List<GreensOrder> selectAll();

    int updateByPrimaryKey(GreensOrder record);

    List<GreensOrderVO> selectAllByPreorderId(String preorderId);
}