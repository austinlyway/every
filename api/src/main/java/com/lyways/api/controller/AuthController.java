package com.lyways.api.controller;

import com.lyways.api.response.ResponseModel;
import com.lyways.api.vo.LoginUser;
import com.lyways.business.user.entity.Account;
import com.lyways.business.user.entity.User;
import com.lyways.business.user.service.IUserService;
import com.lyways.business.user.service.auth.AbstractAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: austin
 * @createDate: 10/25/17
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AbstractAuth emailAuth;

    @PostMapping(path = "/auth")
    public ResponseModel login(LoginUser loginUser){
        Account account = new Account();
        account.setAccountValue(loginUser.getAccount());
        return new ResponseModel().success().data(emailAuth.login(account));
    }

    @PostMapping(path = "/register")
    public ResponseModel<User> register(LoginUser loginUser){
        Account account = new Account();
        account.setAccountType(loginUser.getType());
        account.setAccountValue(loginUser.getAccount());
        account.setAccountPassword(loginUser.getPassword());
        User user = emailAuth.register(account);
        return new ResponseModel<User>().success().data(user);
    }
}
