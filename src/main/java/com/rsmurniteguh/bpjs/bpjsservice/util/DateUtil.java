package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

    private DateUtil(){
        throw new IllegalStateException("Utility Class");
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String formatTimestamp(Timestamp timestamp){
        return new SimpleDateFormat(DATE_FORMAT).format(timestamp);
    }
}
