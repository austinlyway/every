package com.lyways.api.vo;

/**
 * @author austin
 * @createDate 2017/11/29.
 */
public class LoginUser {

    private int type;

    private String account;

    private String password;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
