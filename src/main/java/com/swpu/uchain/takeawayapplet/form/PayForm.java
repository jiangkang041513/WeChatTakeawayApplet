package com.swpu.uchain.takeawayapplet.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName PayForm
 * @Author hobo
 * @Date 19-3-27 下午1:27
 * @Description
 **/
@Data
public class PayForm {

    @NotNull(message = "code不能为空")
    @ApiModelProperty("微信code")
    private String code;

    @NotNull(message = "订单号不能为空")
    @ApiModelProperty("商家订单号")
    private Long id;

    @NotNull(message = "订单总金额不能为空")
    @ApiModelProperty("订单总金额")
    private BigDecimal orderAmount;

}
