package com.littlezheng.tool.filepack;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.littlezheng.tool.filefilter.FileFilterUtils;
import com.littlezheng.tool.filefilter.FilterRule;
import com.littlezheng.tool.filefilter.rules.ExtensionFilterRule;
import com.littlezheng.tool.filefilter.rules.TimeBetweenFilterRule;
import com.littlezheng.tool.filepack.rules.TrashDirFilterRule;
import com.littlezheng.tool.filepack.rules.TrashFileFilterRule;

public class PackUtils {
    
    private static final FilterRule TRASH_DIR_FILTER_RULE = new TrashDirFilterRule();
    private static final FilterRule TRASH_FILE_FILTER_RULE = new TrashFileFilterRule();
    
    /**
     * 打包，截止日期默认到现在
     * @param srcDir    源目录
     * @param destDir   目标目录
     * @param start     修改的起始时间
     */
    public static void pack(String srcDir, String destDir, Date start) {
        pack(srcDir, destDir, start, new Date());
    }
    
    /**
     * 打包
     * @param srcDir     源目录
     * @param destDir    目标目录
     * @param start      修改的起始时间
     * @param end        修改的截止时间
     */
    public static void pack(String srcDir, String destDir, Date start, Date end) {
        Collection<File> files = FileFilterUtils.listFiles(srcDir, (f) -> {
            TimeBetweenFilterRule r1 = new TimeBetweenFilterRule(start, end);
            FilterRule r2 = new ExtensionFilterRule("class", "jsp");
            return r1.filter(f) && r2.filter(f) && TRASH_FILE_FILTER_RULE.filter(f);
        }, (f) -> {
            return TRASH_DIR_FILTER_RULE.filter(f);
        });

        File dest = new File(destDir);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        for (File file : files) {
            File parent = makeDirs(dest, file);
            try {
                FileUtils.copyFile(file, new File(parent, file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    /**
     * 在parent下生成file的目录结构<br>
     * 如parent为D盘下的test目录，file是E盘下的a/b/c目录或是该目录下的一个文件，
     * 则将在D盘test目录下生成a/b/c目录，最终的目录结构为：D:/test/a/b/c
     * @param parent     父目录
     * @param file       文件或目录
     * @return 生成的目录
     */
    private static File makeDirs(File parent, File file) {
        if (parent == null || !parent.exists()) {
            throw new IllegalArgumentException("parent不能为空！");
        }
        if (file == null) {
            throw new IllegalArgumentException("file不能为空！");
        }
        String path = (!file.exists() || file.isDirectory()) ? file.getAbsolutePath() : file.getParent();
        File dir = new File(parent, getPathWithoutDrive(path));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
    
    // 获取不带盘符的文件路径
    private static String getPathWithoutDrive(String path) {
        Pattern p = Pattern.compile("^[A-Z]+:[/|\\\\]");
        Matcher m = p.matcher(path);
        String result = StringUtils.EMPTY;
        while (m.find()) {
            result = path.substring(m.end());
        }
        return result;
    }
    
}
