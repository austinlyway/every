package com.lyways.business.user.service.auth;

import com.lyways.business.user.entity.Account;
import com.lyways.business.user.entity.User;

/**
 * @author austin
 * @createDate 2017/11/29.
 */
public abstract class AbstractAuth {
    /**
     * 登录逻辑
     * @param account
     * @return
     */
    public abstract User login(Account account);

    /**
     * 注册逻辑
     * @param account
     * @return
     */
    public abstract User register(Account account);

}
