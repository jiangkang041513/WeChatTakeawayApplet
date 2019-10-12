package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertMenuBaseForm {


    @ApiModelProperty("菜品名称")
    @NotNull(message = "菜品名称不能为空")
    private String productName;

    @ApiModelProperty("菜品类型Id" )
    @NotNull(message = "菜品类型不能为空")
    private Integer baseTypeId;

}
