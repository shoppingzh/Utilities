package com.littlezheng.tool.filepack.rules;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.littlezheng.tool.filefilter.FilterRule;

public class TrashDirFilterRule implements FilterRule {

    public static final Set<String> TRASH_DIRS = new HashSet<String>();

    static {
        TRASH_DIRS.add("build");
        TRASH_DIRS.add(".svn");
    }

    @Override
    public boolean filter(File file) {
        if (file.isDirectory()) {
            String path = file.getAbsolutePath();
            for (String dir : TRASH_DIRS) {
                if (path.contains(dir)) {
                    return false;
                }
            }
        }
        return true;
    }

}
