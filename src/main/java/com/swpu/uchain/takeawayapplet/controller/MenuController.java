package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.accessctro.RoleContro;
import com.swpu.uchain.takeawayapplet.entity.MenuBaseType;
import com.swpu.uchain.takeawayapplet.enums.RoleEnum;
import com.swpu.uchain.takeawayapplet.form.*;
import com.swpu.uchain.takeawayapplet.service.MenuService;
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
@RequestMapping("/menu")
@Api(tags = "菜谱")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取所有菜品种类")
    @GetMapping("/base/type/all")
    public Object selectAllBaseType() {
        return menuService.selectAllMenuBaseType();
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("删除商品种类")
    @GetMapping("/base/type/delete")
    public Object deleteBaseType(Integer id) {
        return menuService.deleteBaseType(id);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("更新商品种类")
    @PostMapping("/base/type/update")
    public Object updateBaseType(@RequestBody MenuBaseType type){
       return menuService.updateBaseType(type);
    }

    @ApiOperation("获取所有商品及种类")
    @GetMapping("/base/all")
    public Object selectAllBase() {
        return menuService.selectAllMenuBase();
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("添加基础菜品种类")
    @PostMapping("/base/type/insert")
    public Object insertMenuBase(@RequestBody InsertMenuBaseTypeForm form) {
        return menuService.insertMenuBaseType(form);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("添加基础菜品")
    @PostMapping("/base/insert")
    public Object insertMenuBase(@RequestBody List<InsertMenuBaseForm> forms) {
        return menuService.insertMenuBase(forms);
    }


    @ApiOperation("添加菜谱 foodType 0-午饭 1-晚饭")
    @PostMapping("/insert")
    public Object insertMenu(@RequestBody InsertMenuForm form) {
        return menuService.insertMenu(form);
    }

    @ApiOperation("根据创建时间查询当天菜谱")
    @GetMapping("/getMenu")
    public Object selectByCreatTime(long date) {
        return menuService.selectMenuByCreatTime(date);
    }

    @ApiOperation("删除当天菜谱")
    @GetMapping("/deleteMenu")
    public Object deleteByOrderId(long date) {
        return menuService.deleteOrderByDate(date);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("删除基础菜品")
    @GetMapping("/deleteBase")
    public Object deleteMenuBase(Integer id){
        return menuService.deleteMenuBase(id);
    }

    @ApiOperation("查询一定时间段内的菜品点击量")
    @GetMapping("/getClickNum")
    public Object getHighestClickMenuBase(GetClickForm form) {
        return menuService.getHighestClickMenuBase(form);
    }

    @ApiOperation("更新菜品名称")
    @PostMapping("/base/update")
    public Object updateMenuBaseName(@RequestBody UpdateMenuBaseForm form){
        return menuService.updateMenuBaseName(form);
    }


}
