package com.swpu.uchain.takeawayapplet.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MenuOrder implements Serializable {
    private String orderId;

    private Integer menuBaseId;

    private Integer foodType;

    private String creatTime;


}