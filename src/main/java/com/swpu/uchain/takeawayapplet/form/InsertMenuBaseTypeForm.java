package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertMenuBaseTypeForm {

    @ApiModelProperty("菜品类型 如牛肉类")
    @NotNull(message = "菜品类型不能为空")
    private String menuBaseType;

}
