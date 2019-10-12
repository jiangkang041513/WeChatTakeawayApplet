package com.swpu.uchain.takeawayapplet.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuBase implements Serializable {
    private Integer id;

    private String productName;

    private Integer baseTypeId;

    private Integer clickNum;


}