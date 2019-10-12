package com.swpu.uchain.takeawayapplet.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertGreensDTO {

    private Integer greenProductId;

    private Integer greensNum;

    private BigDecimal unitPrice;

    private String address;

    private String contact;

    private String phoneNum;

}
