package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility Class");
    }

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIMETZ_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String DATETIME_WITHOUT_SEC_PATTERN = "yyyy-MM-dd HH:mm";

    public static String customFormatTimestamp(Timestamp timestamp, String pattern) {
        if (timestamp == null)
            return null;
        return new SimpleDateFormat(pattern).format(timestamp);
    }

    public static String formatTimestamp(Timestamp timestamp) {
        return customFormatTimestamp(timestamp, DATE_PATTERN);
    }

    public static String formatTimestamp2(Timestamp timestamp) {
        return customFormatTimestamp(timestamp, DATETIME_PATTERN);
    }

    public static String formatTimestamp3(Timestamp timestamp) {
        return customFormatTimestamp(timestamp, DATETIMETZ_PATTERN);
    }

    public static String formatTimestamp4(Timestamp timestamp) {
        return customFormatTimestamp(timestamp, DATETIME_WITHOUT_SEC_PATTERN);
    }

    public static String customFormatTimestampWithTimezone(Timestamp timestamp, String timezone, String pattern) {
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatTimestampWithTimezone(Timestamp timestamp, String timezone) {
        return customFormatTimestampWithTimezone(timestamp, timezone, DATE_PATTERN);
    }

    public static String formatTimestampWithTimezone2(Timestamp timestamp, String timezone) {
        return customFormatTimestampWithTimezone(timestamp, timezone, DATETIME_PATTERN);
    }

    public static String formatTimestampWithTimezone3(Timestamp timestamp, String timezone) {
        return customFormatTimestampWithTimezone(timestamp, timezone, DATETIMETZ_PATTERN);
    }

    public static String formatTimestampWithTimezone4(Timestamp timestamp, String timezone) {
        return customFormatTimestampWithTimezone(timestamp, timezone, DATETIME_WITHOUT_SEC_PATTERN);
    }

    private static ZoneOffset convertToZoneOffset(ZoneId zoneId) {
        return zoneId.getRules().getOffset(Instant.now());
    }

    private static ZonedDateTime convertLocalDateTimeToTimezone(LocalDateTime localDateTime, String fromTimezone) {
        return localDateTime.atOffset(convertToZoneOffset(ZoneId.of(fromTimezone)))
                .toZonedDateTime();
    }

    private static Timestamp formatLocalDateTimeWithTimezone(LocalDateTime localDateTime, String fromTimezone) {
        ZonedDateTime zonedDateTime = convertLocalDateTimeToTimezone(localDateTime, fromTimezone);
        return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
    }

    public static Timestamp customFormatStringWithTimezone(String string, String fromTimezone, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern(pattern));
        return formatLocalDateTimeWithTimezone(localDateTime, fromTimezone);
    }

    public static Timestamp formatStringWithTimezone(String string, String fromTimezone) {
        LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return formatLocalDateTimeWithTimezone(localDate.atStartOfDay(), fromTimezone);
    }

    public static Timestamp formatStringWithTimezone2(String string, String fromTimezone) {
        return customFormatStringWithTimezone(string, fromTimezone, DATETIME_PATTERN);
    }

    public static Timestamp formatStringWithTimezone3(String string, String fromTimezone) {
        return customFormatStringWithTimezone(string, fromTimezone, DATETIMETZ_PATTERN);
    }

    public static Timestamp formatStringWithTimezone4(String string, String fromTimezone) {
        return customFormatStringWithTimezone(string, fromTimezone, DATETIME_WITHOUT_SEC_PATTERN);
    }

    public static Timestamp customFormatString(String string, String pattern) {
        if (string == null)
            return null;
        try {
            return new Timestamp(new SimpleDateFormat(pattern).parse(string).getTime());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Timestamp formatString(String string) {
        return customFormatString(string, DATE_PATTERN);
    }

    public static Long countDiffDays(Timestamp timestamp, Timestamp timestamp2) {
        Long time1 = timestamp.getTime();
        Long time2 = timestamp2.getTime();

        Long diffMilis = Math.abs(time2 - time1);
        return diffMilis / (1000 * 60 * 60 * 24);
    }

    public static boolean isTimestampSameDay(Timestamp timestamp, Timestamp timestamp2) {
        Long diffDays = countDiffDays(timestamp, timestamp2);

        return diffDays.equals(Long.valueOf(0));
    }

    public static Timestamp removeTimeFromTimestamp(Timestamp timestamp) {
        return formatString(formatTimestamp(timestamp));
    }

    public static Timestamp convertTimestampToTimezone(Timestamp timestamp, String timeZone) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = convertLocalDateTimeToTimezone(localDateTime, timeZone);

        return Timestamp.from(zonedDateTime.toInstant());
    }

    public static Timestamp minusMonth(Timestamp timestamp, Long minusValue) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        return Timestamp.from(zonedDateTime.minusMonths(minusValue).toInstant());
    }
}
