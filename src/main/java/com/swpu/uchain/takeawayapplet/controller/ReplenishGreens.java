package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.accessctro.RoleContro;
import com.swpu.uchain.takeawayapplet.enums.RoleEnum;
import com.swpu.uchain.takeawayapplet.form.InsertGreenBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertGreensForm;
import com.swpu.uchain.takeawayapplet.form.UpdateGreenBaseForm;
import com.swpu.uchain.takeawayapplet.service.ReplenishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("获取菜单原料列表")
    @GetMapping("/greens")
    public Object getGreenBaseByPid() {
        return replenishService.getGreensList();
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("更新菜品原料")
    @PostMapping("/greens/update")
    public Object updateGreenBase(@RequestBody UpdateGreenBaseForm form){
        return replenishService.updateGreenBase(form);
    }

    @ApiOperation("获取原料种类列表")
    @GetMapping("/greens/type/all")
    public Object getGreensType(){
        return replenishService.getGreensTypeList();
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("删除菜品原料")
    @GetMapping("/greens/delete")
    public Object deleteGreenBase(Integer id){
        return replenishService.deleteGreenBase(id);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("添加菜品原料")
    @PostMapping("/greens/insert")
    public Object insertGreenBase(@RequestBody List<InsertGreenBaseForm> form){
        return replenishService.insertGreenBase(form);
    }


    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("添加原料种类")
    @PostMapping("/greens/type/insert")
    public Object insertGreenBaseType(@RequestBody String baseType){
        return replenishService.insertGreenBaseType(baseType);
    }
}
