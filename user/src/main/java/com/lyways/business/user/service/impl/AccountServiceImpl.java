package com.lyways.business.user.service.impl;

import com.lyways.business.user.dao.AccountDAO;
import com.lyways.business.user.entity.Account;
import com.lyways.business.user.service.IAccountService;
import com.lyways.bizcommon.business.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author austin
 * @createDate 2017/11/29.
 */
@Service("accountService")
public class AccountServiceImpl extends BaseService<AccountDAO, Account> implements IAccountService{
}
