package com.lyways.framework.util;

import java.io.File;

/**
 * Created by austin on 9/25/17.
 */
public class PathUtil {

    public static String getPath(Class clazz) {
        String path = clazz.getResource("").getPath();
        return new File(path).getAbsolutePath();
    }

    public static String getPath(Object object) {
        return getPath(object.getClass());
    }

    public static String getPackagePath(Object object) {
        Package p = object.getClass().getPackage();
        return p != null ? p.getName().replaceAll("\\.", "/") : "";
    }

    public static boolean isAbsolutelyPath(String path) {
        return path.startsWith("/") || path.indexOf(":") == 1;
    }
}
