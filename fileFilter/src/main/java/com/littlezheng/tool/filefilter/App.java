package com.littlezheng.tool.filefilter;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.littlezheng.tool.filefilter.rules.ExtensionFilterRule;
import com.littlezheng.tool.filefilter.rules.TimeBetweenFilterRule;

public class App {
    
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args) throws ParseException {

        Date startDate = df.parse("2019-02-25 11:43:00");
        Date endDate = new Date();
        
        Collection<File> files = FileFilterUtils.listFiles("d:/dir", (f) -> {
            TimeBetweenFilterRule r1 = new TimeBetweenFilterRule(startDate, endDate);
            ExtensionFilterRule r2 = new ExtensionFilterRule("class", "jsp");
            return r1.filter(f);
        });
        List<File> newFiles = new ArrayList<File>(files);
        Collections.sort(newFiles, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return f1.getName().compareToIgnoreCase(f2.getName());
            }
        });
        for(File f : newFiles){
            System.out.println(f);
        }
    }
    
}
