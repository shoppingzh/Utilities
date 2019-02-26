package com.littlezheng.tool.filefilter.rules;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.littlezheng.tool.filefilter.FilterRule;

public class ExtensionFilterRule implements FilterRule{
    
    private String[] extensions;
    private boolean ignoreCase;
    
    public ExtensionFilterRule(String... extensions) {
        this(true, extensions);
    }
    
    public ExtensionFilterRule( boolean ignoreCase, String... extensions) {
        super();
        this.ignoreCase = ignoreCase;
        if(extensions == null || extensions.length <= 0){
            throw new IllegalArgumentException("必须传入至少一个后缀名！");
        }
        this.extensions = extensions;
    }
    
    @Override
    public boolean filter(File file) {
        String fileExt = FilenameUtils.getExtension(file.getName());
        for (String ext : extensions) {
            if ((ignoreCase && ext.equalsIgnoreCase(fileExt)) || (!ignoreCase && ext.equals(fileExt))) {
                return true;
            }
        }
        return false;
    }

}
