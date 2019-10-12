package com.swpu.uchain.takeawayapplet.form;

import com.swpu.uchain.takeawayapplet.dto.CreatMenuOrderDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertMenuForm {


    @ApiModelProperty("订单创建时间")
    private Long creatTime;

    private List<CreatMenuOrderDTO> dto;
}
