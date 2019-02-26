package com.littlezheng.tool.filefilter;

import java.io.File;

public interface FilterRule {
    
    boolean filter(File file);

}
