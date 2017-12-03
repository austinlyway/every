package com.lyways.business.user.service.auth.strategy;

import com.lyways.bizcommon.Constants;
import com.lyways.business.user.entity.Account;
import com.lyways.business.user.entity.User;
import com.lyways.business.user.service.IAccountService;
import com.lyways.business.user.service.IUserService;
import com.lyways.business.user.service.auth.AbstractAuth;
import com.lyways.framework.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author austin
 * @createDate 2017/11/29.
 */
@Service("emailAuth")
public class EmailAuth extends AbstractAuth {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Override
    public User login(Account account) {
        account.setRecStatus(Constants.REC_STATUS_ACTIVE);
        Account dbAccount = accountService.selectUnique(account);
        if(dbAccount == null){
            throw new BusinessException("无法找到对应账号");
        }
        return userService.selectByPrimaryKey(dbAccount.getUserId());
    }

    @Override
    public User register(Account account) {
        Assert.notNull(account.getAccountType(), "账号类型不能为空");
        Assert.hasText(account.getAccountValue(), "账号信息不能为空");
        Assert.hasText(account.getAccountPassword(), "密码不能为空");
        String password = account.getAccountPassword();
        User user = new User();
        user.setStatus(User.UserStatus.REGISTERED);
        userService.insert(user);

        account.setUserId(user.getUserId());
        accountService.insert(account);
        return user;
    }
}
