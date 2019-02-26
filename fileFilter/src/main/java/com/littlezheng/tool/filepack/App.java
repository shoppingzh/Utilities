package com.littlezheng.tool.filepack;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args) throws ParseException {

        Date startDate = df.parse("2019-02-25 9:00:00");
        Date endDate = new Date();
        
        for (File f : FileFilterUtils.listFiles("d:/dir", (f) -> {
            TimeBetweenFilterRule r1 = new TimeBetweenFilterRule(startDate, endDate);
            ExtensionFilterRule r2 = new ExtensionFilterRule("class", "jsp");
            return r1.filter(f) && r2.filter(f);
        })) {
            System.out.println(f);
        }
    }
    
}
