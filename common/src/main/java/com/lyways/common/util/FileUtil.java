package com.lyways.common.util;

import java.io.File;

/**
 * Created by austin on 9/25/17.
 */
public class FileUtil {

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
            else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i=0; i<files.length; i++) {
                    delete(files[i]);
                }
            }
            file.delete();
        }
    }
}
