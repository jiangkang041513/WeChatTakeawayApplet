package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdateGreenBaseForm {

    @ApiModelProperty("原料编号")
    private Integer id;

    @ApiModelProperty("原料父级种类编号")
    private Integer pId;

    @ApiModelProperty("原料名称")
    private String productName;

}
