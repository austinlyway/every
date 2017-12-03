package com.lyways.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
public class ThreadContext {
    private static final ThreadLocal<Map<String, Object>> threadContext = new ThreadLocal<>();


    public void add(String key, Object object){
        ensureContextMap();
        threadContext.get().put(key, object);
    }

    public static <T> T get(String key)
    {
        ensureContextMap();
        Object object = threadContext.get().get(key);
        return object != null ? (T)object : null;
    }

    public static Integer getInteger(String key)
    {
        ensureContextMap();
        Object object = threadContext.get().get(key);
        return object != null ? Integer.parseInt(String.valueOf(object)) : null;
    }

    private static void ensureContextMap()
    {
        Map<String, Object> map = threadContext.get();
        if(map == null)
        {
            threadContext.set(new HashMap<>());
        }
    }
}
