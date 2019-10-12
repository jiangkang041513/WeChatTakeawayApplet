package com.swpu.uchain.takeawayapplet.dto;

import com.swpu.uchain.takeawayapplet.VO.MenuBaseVO;
import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class MenuDTO {

    private Integer foodType;

    private List<MenuBaseVO> vos;
}
