package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil(){
        throw new IllegalStateException("Utility Class");
    }

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String customFormatTimestamp(Timestamp timestamp, String pattern){
        return new SimpleDateFormat(pattern).format(timestamp);
    }

    public static String formatTimestamp(Timestamp timestamp){
        return customFormatTimestamp(timestamp, DATE_PATTERN);
    }

    public static String formatTimestamp2(Timestamp timestamp){
        return customFormatTimestamp(timestamp, DATETIME_PATTERN);
    }

    public static String customFormatTimestampWithTimezone(Timestamp timestamp, String timezone, String pattern){
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(timezone)).toLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatTimestampWithTimezone(Timestamp timestamp, String timezone){
        return customFormatTimestampWithTimezone(timestamp, timezone, DATE_PATTERN);
    }

    public static String formatTimestampWithTimezone2(Timestamp timestamp, String timezone){
        return customFormatTimestampWithTimezone(timestamp, timezone, DATETIME_PATTERN);
    }

    public static Timestamp customFormatStringWithTimezone(String string, String fromTimezone, String pattern){
        LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern(pattern));
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(fromTimezone));

        return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()); 
    }

    public static Timestamp formatStringWithTimezone(String string, String fromTimezone){
        return customFormatStringWithTimezone(string, fromTimezone, DATE_PATTERN);
    }

    public static Timestamp formatStringWithTimezone2(String string, String fromTimezone){
        return customFormatStringWithTimezone(string, fromTimezone, DATETIME_PATTERN);
    }
}
