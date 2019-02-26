package com.littlezheng.tool.filepack;

import java.io.File;
import java.util.Date;

public class TimeBetweenFilterRule implements FilterRule {

    private long start;
    private long end;

    public TimeBetweenFilterRule(Date startTime, Date endTime) {
        super();
        this.start = startTime.getTime();
        this.end = endTime.getTime();
    }

    @Override
    public boolean filter(File file) {
        long lastModified = file.lastModified();
        return lastModified >= start && lastModified <= end;
    }

}
