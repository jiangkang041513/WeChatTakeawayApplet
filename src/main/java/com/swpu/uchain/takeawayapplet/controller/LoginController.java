package com.swpu.uchain.takeawayapplet.controller;

import com.swpu.uchain.takeawayapplet.form.LoginForm;
import com.swpu.uchain.takeawayapplet.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Author hobo
 * @Date 19-3-26 下午5:15
 * @Description
 **/
@RestController
@Api(tags = "登录")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login", name = "管理员登录")
    public Object login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        return userService.login(loginForm, response);
    }

}
