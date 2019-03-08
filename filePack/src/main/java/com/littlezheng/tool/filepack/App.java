package com.littlezheng.tool.filepack;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws ParseException, IOException {
        Date startDate = df.parse("2019-03-08 09:20:00");
        Date endDate = new Date();
        PackUtils.pack("D:/workspace-dascom/hawk2/webapp", "d:/dir", startDate, endDate);
    }

}
