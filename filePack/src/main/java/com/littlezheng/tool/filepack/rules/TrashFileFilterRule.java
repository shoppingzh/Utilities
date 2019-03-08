package com.littlezheng.tool.filepack.rules;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.littlezheng.tool.filefilter.FilterRule;

public class TrashFileFilterRule implements FilterRule {

    private static final Set<String> TRASH_FILE_NAMES = new HashSet<String>();

    static {
        TRASH_FILE_NAMES.add("AppInfo.class");
    }

    @Override
    public boolean filter(File file) {
        for (String fileName : TRASH_FILE_NAMES) {
            if (fileName.equalsIgnoreCase(file.getName())) {
                return false;
            }
        }
        return true;
    }

}
