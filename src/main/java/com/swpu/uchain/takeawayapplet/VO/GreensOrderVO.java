package com.swpu.uchain.takeawayapplet.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hobo
 * @description
 */
@Data
public class GreensOrderVO {

    private Integer greenProductId;

    private String productName;

    private Integer greensNum;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private String address;

    private String phoneNum;

    private String contact;

    private String creatTime;



}
