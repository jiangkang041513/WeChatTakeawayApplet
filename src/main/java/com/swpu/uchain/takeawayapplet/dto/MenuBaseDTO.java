package com.swpu.uchain.takeawayapplet.dto;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseVO;
import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class MenuBaseDTO {

    private Integer id;

    private String menuBaseType;

    private List<MenuBaseVO> menuBaseVOList;

}
