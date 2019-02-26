package com.littlezheng.tool.filepack;

import java.io.File;
import java.util.Collection;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class FileFilterUtils {
    
    public static Collection<File> listFiles(String dir, Function<File, Boolean> filterFunc) {
        Collection<File> files = FileUtils.listFiles(new File(dir), new IOFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
            @Override
            public boolean accept(File file) {
                return filterFunc.apply(file);
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
            @Override
            public boolean accept(File file) {
                return true;
            }
        });
        return files;
    }
    
}
