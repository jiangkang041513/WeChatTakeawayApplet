package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class GetClickForm {

    @ApiModelProperty("起始时间")
    private long beginDate;

    @ApiModelProperty("结束时间")
    private long endDate;
}
