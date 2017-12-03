package com.lyways.tool.jdbc;

/**
 * 用于支持对枚举类型的数据持久化, 在定义需要持久化的enum类型时, 实现此接口
 *  E: 为当前的enum类型
 *  T: 为需要持久化的数据类型
 * @author austin
 * @createDate 2017/11/30.
 */
public interface BaseCodeEnum <E extends Enum<?>, T>
{
    /**
     * 返回需要被持久化到数据库中的值
     * @return
     */
    T getCode();

    /**
     * 返回persistValue所对应的枚举对象
     * @param code
     * @return
     */
    E getEnum(T code);
}