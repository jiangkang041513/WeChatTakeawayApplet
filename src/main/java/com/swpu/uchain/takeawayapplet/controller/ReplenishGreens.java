package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;
import com.swpu.uchain.takeawayapplet.service.ReplenishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hobo
 * @description
 */
@RestController
@RequestMapping("/replenish")
@Api(tags = "进货")
public class ReplenishGreens {

    @Autowired
    private ReplenishService replenishService;

    @ApiOperation("添加进货订单")
    @PostMapping("/insert")
    public Object insert(@RequestBody InsertGreensForm form) {
        return replenishService.insertGreens(form);
    }

    @ApiOperation("获取这次订单下的所有商品")
    @GetMapping("/all")
    public Object getAllOrderByPreorderId(String preorderId) {
        return replenishService.getAllOrderByPreorderId(preorderId);
    }

    @ApiOperation("获取菜单原料列表 首层pid为0")
    @GetMapping("/greens")
    public Object getGreenBaseByPid(String pid) {
        return replenishService.getGreensList(pid);
    }

    @ApiOperation("添加菜品原料")
    @PostMapping("/greens/insert")
    public Object insertGreenBase(InsertGreenBaseForm form){
        return replenishService.insertGreenBase(form);
    }
}
