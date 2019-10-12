package com.swpu.uchain.takeawayapplet.VO;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class GreensOrderListVO {

    private String preorderId;

    private String creatTime;

    private List<GreensOrderVO> orderVos;

}
