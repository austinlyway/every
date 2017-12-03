package com.lyways.bizcommon.business.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author: austin
 * @createDate: 10/25/17
 */
public interface Crud<T> {

    /**
     * 根据PK删除对象
     * @param pk
     * @return
     */
    int deleteByPrimaryKey(Serializable pk);

    /**
     * 插入对象
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     *  根据PK查询对象
     * @param pk
     * @return
     */
    T selectByPrimaryKey(Serializable pk);

    /**
     * 根据条件查询
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * 根据查询条件,查询唯一一条
     * @param entity
     * @return
     */

    T selectUnique(T entity);
    /**
     * 根据PK更新对象数据
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据参数计算行数
     * @param entity
     * @return
     */
    int selectCount(T entity);

    /**
     *
     * @param page
     * @param entity
     * @return
     */
    Page<T> selectByPage(Page<T> page, T entity);
}
