package com.lyways.business.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lyways.bizcommon.SequenceRule;
import com.lyways.bizcommon.annotation.EveryId;
import com.lyways.bizcommon.business.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author austin
 * @createDate 10/26/17.
 */
@TableName("user_t_account")
public class Account extends BaseEntity<Account>{


    public static final int ACCOUNT_TYPE_USER_NAME = 1;

    public static final int ACCOUNT_TYPE_MOBILE_PHONE = 2;

    public static final int ACCOUNT_TYPE_EMAIL= 3;

    public static final int ACCOUNT_TYPE_WEIXIN = 4;

    public static final int ACCOUNT_TYPE_QQ = 5;

    @TableId
    private String userAccountId;

    private Integer accountType;

    private String accountValue;

    private String accountPassword;

    private String userId;

    @EveryId(SequenceRule.ACCOUNT_ID)
    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(String accountValue) {
        this.accountValue = accountValue;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userAccountId;
    }


}
