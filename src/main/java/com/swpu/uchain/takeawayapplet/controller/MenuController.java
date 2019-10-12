package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.form.GetClickForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuBaseTypeForm;
import com.swpu.uchain.takeawayapplet.form.InsertMenuForm;
import com.swpu.uchain.takeawayapplet.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取所有商品及种类")
    @GetMapping("/base/all")
    public Object selectAllBase() {
        return menuService.selectAllMenuBase();
    }

    @ApiOperation("添加基础菜品种类")
    @PostMapping("/base/type/insert")
    public Object insertMenuBase(InsertMenuBaseTypeForm form) {
        return menuService.insertMenuBaseType(form);
    }

    @ApiOperation("添加基础菜品")
    @PostMapping("/base/insert")
    public Object insertMenuBase(InsertMenuBaseForm form) {
        return menuService.insertMenuBase(form);
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

    @ApiOperation("查询一定时间段内的菜品点击量")
    @GetMapping("/getClickNum")
    public Object getHighestClickMenuBase(GetClickForm form) {
        return menuService.getHighestClickMenuBase(form);
    }


}
