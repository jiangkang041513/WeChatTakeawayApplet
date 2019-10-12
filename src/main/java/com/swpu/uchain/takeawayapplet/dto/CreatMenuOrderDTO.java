package com.swpu.uchain.takeawayapplet.dto;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class CreatMenuOrderDTO {

    private Integer foodType;

    private List<Integer> menuBaseId;

}
