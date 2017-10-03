package com.lyways.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by austin on 9/25/17.
 */
public enum DateFormatter {
    YYYYMMDD("yyyy-MM-dd"), YYYMMDDHHmmss("yyyy-MM-dd HH:mm:ss");

    private String dateFormat;

    DateFormatter(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public Date convertToDate(String strDate)  {
        SimpleDateFormat df = new SimpleDateFormat(this.dateFormat);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String formatterToStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(this.dateFormat);
        return df.format(new Date());
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
