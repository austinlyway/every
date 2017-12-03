package com.lyways.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileUtil {

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
            else if (file.isDirectory()) {
                deleteDirectory(file);
            }
        }
    }

    public static void deleteDirectory(File directory){
        File[] files = directory.listFiles();
        List<String> d = new ArrayList<>();
        Iterator<String> iterator = d.iterator();
//        for (int i=0; i<files.length; i++) {
//            delete(files[i]);
//        }
        directory.delete();
    }
}
