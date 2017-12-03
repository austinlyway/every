package com.lyways.bizcommon.business.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyways.bizcommon.annotation.EveryId;
import com.lyways.framework.annotation.AnnotatedClass;
import com.lyways.framework.annotation.AnnotatedProperty;
import com.lyways.bizcommon.business.dao.BaseDAO;
import com.lyways.bizcommon.business.dao.Crud;
import com.lyways.bizcommon.business.dao.Page;
import com.lyways.framework.exception.BusinessException;
import com.lyways.bizcommon.business.SequenceGenerator;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author austin
 * @createDate 10/26/17.
 */
public abstract class BaseService <E extends BaseDAO<T>, T> implements Crud<T> {

    @Autowired
    protected E baseDAO;

    @Override
    public int deleteByPrimaryKey(Serializable pk) {
        return baseDAO.deleteById(pk);
    }

    @Override
    public int insert(T entity) {
        ensurePrimaryKey(entity);
        return baseDAO.insert(entity);
    }

    @Override
    public T selectByPrimaryKey(Serializable pk) {
        return baseDAO.selectById(pk);
    }

    @Override
    public List<T> select(T entity) {
        return baseDAO.selectList(new EntityWrapper<>(entity));
    }

    @Override
    public T selectUnique(T entity) {
        return baseDAO.selectOne(entity);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return baseDAO.updateById(entity);
    }

    @Override
    public int selectCount(T entity) {
        return baseDAO.selectCount(new EntityWrapper<>(entity));
    }

    @Override
    public Page<T> selectByPage(Page<T> page, T entity) {
        EntityWrapper<T> entityWrapper = new EntityWrapper<>(entity);
        List<T> list = baseDAO.selectPage(new RowBounds(page.getOffset(), page.getPageSize()), entityWrapper);
        page.setDataList(list);
        int totalCount = baseDAO.selectCount(entityWrapper);
        page.setTotalCount(totalCount);
        return page;
    }

    private void ensurePrimaryKey(T entity){
        AnnotatedClass annotatedClass = new AnnotatedClass(entity.getClass());
        List<AnnotatedProperty> annotatedPropertyList = annotatedClass.getAnnotatedPropertyByAnnotaion(EveryId.class);
        if(annotatedPropertyList != null && annotatedPropertyList.size() > 1){
            throw new BusinessException("only on EveryId");
        }
        AnnotatedProperty everyIdAnno = annotatedPropertyList.get(0);
        Object idValue = everyIdAnno.getValue(entity);
        if(idValue == null){
            EveryId everyId = everyIdAnno.getAnnotation(EveryId.class);
            everyIdAnno.setValue(entity, SequenceGenerator.getInstance().generateSequence(everyId.value()));
        }
    }
}
