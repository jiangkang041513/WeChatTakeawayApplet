package com.swpu.uchain.takeawayapplet.service;

import com.swpu.uchain.takeawayapplet.VO.ResultVO;
import com.swpu.uchain.takeawayapplet.entity.User;
import com.swpu.uchain.takeawayapplet.form.LoginForm;
import com.swpu.uchain.takeawayapplet.form.UpdateUserForm;
import com.swpu.uchain.takeawayapplet.form.UserForm;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    boolean insert(User user);

    boolean update(User user);

    boolean delete(Long id);

    User getCurrentUser();

    User findUserByUserName(String username);

    ResultVO insertUser(UserForm userForm);

    ResultVO deleteUser(Long id);

    ResultVO login(LoginForm loginForm, HttpServletResponse response);

    ResultVO addRole(Long id,Integer role);

    ResultVO selectAll();

    ResultVO updatePw(UpdateUserForm form);

    ResultVO resetUserPw(long id);

    ResultVO updateUserName(long id,String username);
}
