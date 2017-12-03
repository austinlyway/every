package com.lyways.business.user.service;

import com.lyways.business.user.entity.User;
import com.lyways.bizcommon.business.dao.Crud;

/**
 * @author austin
 * @createDate 2017/11/26.
 */
public interface IUserService extends Crud<User> {
    /**
     *xx
     * @param user
     * @return
     */
    boolean register(User user);
}
