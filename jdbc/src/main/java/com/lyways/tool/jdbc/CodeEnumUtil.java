package com.lyways.tool.jdbc;


/**
 * @author austin
 * @createDate 2017/11/30.
 */
public class CodeEnumUtil {
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, Object code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
}
