package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.accessctro.RoleContro;
import com.swpu.uchain.takeawayapplet.enums.RoleEnum;
import com.swpu.uchain.takeawayapplet.form.ResetPwForm;
import com.swpu.uchain.takeawayapplet.form.UpdateUserForm;
import com.swpu.uchain.takeawayapplet.form.UpdateUserNameForm;
import com.swpu.uchain.takeawayapplet.form.UserForm;
import com.swpu.uchain.takeawayapplet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdminController
 * @Author hobo
 * @Date 19-3-26 下午4:45
 * @Description
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "用户管理界面")
public class AdminController {

    @Autowired
    private UserService userService;

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("添加用户 默认密码 123456")
    @PostMapping(value = "/insert", name = "添加用户")
    public Object insertUser(@RequestBody UserForm userForm) {
        return userService.insertUser(userForm);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("查询用户列表")
    @GetMapping(value = "/list", name = "查询用户列表")
    public Object selectAll() {
        return userService.selectAll();
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("删除用户")
    @GetMapping(value = "/delete", name = "删除用户")
    public Object deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    @RoleContro(role = RoleEnum.ADMIN)
    @ApiOperation("设置用户权限 role=0 设置为用户 role=1 设置为管理员")
    @PostMapping(value = "/updateRole", name = "将用户提升为管理员")
    public Object addAdminRole(@RequestParam("id") Long id, @RequestParam("role") Integer role) {
        return userService.addRole(id, role);
    }

    @ApiOperation("用户本人修改密码")
    @PostMapping(value = "/updatePw", name = "用户本人修改密码")
    public Object updatePw(@RequestBody UpdateUserForm form) {
        return userService.updatePw(form);
    }

    @ApiOperation("重置用户密码")
    @PostMapping(value = "/resetpw", name = "重置用户密码")
    public Object resetUserPw(@RequestBody ResetPwForm form) {
        return userService.resetUserPw(form.getId());
    }

    @ApiOperation("修改用户名")
    @PostMapping(value = "/updateusername",name = "修改用户名")
    public Object updateUserName(@RequestBody UpdateUserNameForm form){
        return userService.updateUserName(form.getId(),form.getUsername());
    }

}
