package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertGreenBaseForm {

    @ApiModelProperty("父类Id")
    @NotNull(message = "父类Id不能为空")
    private Integer pid;

    @ApiModelProperty("产品名称")
    @NotNull(message = "产品名称不能为空")
    private String productName;

}
