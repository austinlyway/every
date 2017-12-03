package com.lyways.business.user.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lyways.bizcommon.SequenceRule;
import com.lyways.bizcommon.annotation.EveryId;
import com.lyways.bizcommon.business.entity.BaseEntity;
import com.lyways.tool.jdbc.BaseCodeEnum;
import com.lyways.tool.jdbc.CodeEnumUtil;
import com.lyways.tool.jdbc.adapter.mybatis.EnumTypeHandler;
import org.apache.ibatis.annotations.TypeDiscriminator;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;


/**
 * @author austin
 * @createDate 10/26/17.
 */
@TableName("user_t_user")
public class User extends BaseEntity<User> {


    @TableId
    private String userId;

    private String userName;

    private String phone;

    private String realName;

    private UserStatus status;

    @EveryId(SequenceRule.USER_ID)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @TypeDiscriminator(column = "status",cases = {}, javaType = UserStatus.class,jdbcType = JdbcType.INTEGER,typeHandler = EnumTypeHandler.class)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    public enum UserStatus implements BaseCodeEnum<UserStatus, Integer>{
        REGISTERED(1);

        private int code;

        UserStatus(int code){
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public UserStatus getEnum(Integer code) {
            return CodeEnumUtil.codeOf(UserStatus.class, code);
        }
    }
}
