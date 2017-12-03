package com.lyways.tool.jdbc.adapter.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
public interface SequenceDAO{

    /***
     *
     * @param sequenceName
     * @param prefix
     * @param length
     * @return
     */
    @Select("select `getSequence`(#{sequenceName}, #{prefix}, #{length})")
    String selectSequence(@Param("sequenceName") String sequenceName, @Param("prefix") String prefix, @Param("length") int length);
}
