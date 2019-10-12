package com.swpu.uchain.takeawayapplet.VO;

import com.swpu.uchain.takeawayapplet.dto.MenuDTO;
import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class MenuListVO {

    private String orderId;

    private String creatTime;

    private List<MenuDTO> foodList;
}
