package com.lyways.tool.jdbc.adapter.mybatis;

import com.lyways.tool.jdbc.BaseCodeEnum;
import com.lyways.tool.jdbc.CodeEnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author austin
 * @createDate 2017/11/30.
 */
@MappedTypes(BaseCodeEnum.class)
public class EnumTypeHandler<E extends Enum<?> & BaseCodeEnum> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E o, JdbcType jdbcType) throws SQLException {
        if(o != null && o instanceof BaseCodeEnum){
            preparedStatement.setObject(i, ((BaseCodeEnum)o).getCode());
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Object val = resultSet.getObject(s);
        if(val== null){
            return null;
        }
        return CodeEnumUtil.codeOf(type, val);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Object val = resultSet.getObject(i);
        if(val== null){
            return null;
        }
        return CodeEnumUtil.codeOf(type, val);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Object val = callableStatement.getObject(i);
        if(val== null){
            return null;
        }
        return CodeEnumUtil.codeOf(type, val);
    }
}
