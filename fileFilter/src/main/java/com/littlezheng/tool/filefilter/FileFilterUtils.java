package com.littlezheng.tool.filefilter;

import java.io.File;
import java.util.Collection;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class FileFilterUtils {
    
    /**
     * 查找文件
     * @param dir                    目标目录
     * @param fileFilterRuleFunc     文件过滤规则
     * @return
     */
    public static Collection<File> listFiles(String dir, Function<File, Boolean> fileFilterRuleFunc) {
        return listFiles(dir, fileFilterRuleFunc, (f) -> {
            return true;
        });
    }
    
    /**
     * 查找文件
     * @param dir                     目标目录
     * @param fileFilterRuleFunc      文件过滤规则
     * @param dirFilterRuleFunc       目录过滤规则
     * @return
     */
    public static Collection<File> listFiles(String dir, Function<File, Boolean> fileFilterRuleFunc,
            Function<File, Boolean> dirFilterRuleFunc) {
        Collection<File> files = FileUtils.listFiles(new File(dir), new IOFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }

            @Override
            public boolean accept(File file) {
                return fileFilterRuleFunc.apply(file);
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }

            @Override
            public boolean accept(File file) {
                return dirFilterRuleFunc.apply(file);
            }
        });
        return files;
    }
    
}
