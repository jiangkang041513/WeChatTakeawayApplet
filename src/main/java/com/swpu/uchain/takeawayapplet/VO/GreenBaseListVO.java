package com.swpu.uchain.takeawayapplet.VO;

import com.swpu.uchain.takeawayapplet.dto.GreenBaseDTO;
import com.swpu.uchain.takeawayapplet.entity.GreenBase;
import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class GreenBaseListVO {

    private Integer id;

    private String productName;

    private List<GreenBaseDTO> baseList;

}
