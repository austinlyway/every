package com.lyways.tool.jdbc.adapter.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.lyways.framework.ThreadContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
@Component("mybatisMetaObjectHandler")
public class MybatisMetaObjectHandler extends MetaObjectHandler {

    private final String INSERT_FILL_CREATED_DATE = "createdDate";
    private final String INSERT_FILL_CREATED_BY = "createdBy";

    private final String UPDATE_FILL_MODIFIED_DATE = "modifiedDate";
    private final String UPDATE_FILL_MODIFIED_BY = "modifiedBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createdDate = getFieldValByName(INSERT_FILL_CREATED_DATE, metaObject);
        if (createdDate == null) {
            setFieldValByName(INSERT_FILL_CREATED_DATE, new Date(), metaObject);
        }

        Object createdBy = getFieldValByName(INSERT_FILL_CREATED_BY, metaObject);
        if (createdBy == null) {
            setFieldValByName(INSERT_FILL_CREATED_BY, ThreadContext.get("userId"), metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifiedDate = getFieldValByName(UPDATE_FILL_MODIFIED_DATE, metaObject);
        if (modifiedDate == null) {
            setFieldValByName(UPDATE_FILL_MODIFIED_DATE, new Date(), metaObject);
        }

        Object modifiedBy = getFieldValByName(UPDATE_FILL_MODIFIED_BY, metaObject);
        if (modifiedBy == null) {
            setFieldValByName(UPDATE_FILL_MODIFIED_BY, ThreadContext.get("userId"), metaObject);
        }
    }
}
