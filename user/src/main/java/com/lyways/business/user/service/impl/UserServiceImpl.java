package com.lyways.business.user.service.impl;

import com.lyways.business.user.dao.UserDAO;
import com.lyways.business.user.entity.Account;
import com.lyways.business.user.entity.User;
import com.lyways.business.user.service.IUserService;
import com.lyways.bizcommon.business.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author austin
 * @createDate 2017/11/26.
 */
@Service("userService")
public class UserServiceImpl extends BaseService<UserDAO, User> implements IUserService {

    @Override
    public boolean register(User user){
        super.insert(user);
        return true;
    }

    public boolean login(Account account){
        return true;
    }
}
