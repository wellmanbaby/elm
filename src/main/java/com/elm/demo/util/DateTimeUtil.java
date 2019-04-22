package com.elm.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by weiyao on 2019/4/17.
 */
public class DateTimeUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    /**
     * The constant PRINT_FORMATTER.
     */
    public final static DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The constant PRINT_TIME_FORMATTER.
     */
    public final static DateTimeFormatter PRINT_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The constant PRINT_TIME_SECONDS_FORMATTER.
     */
    public final static DateTimeFormatter PRINT_TIME_SECONDS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * The constant DATA_FORMATTER.
     */
    public final static DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * The constant DATA_TIME_FORMATTER.
     */
    public final static DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH");

    /**
     * The constant DATA_TIME_SECONDS_FORMATTER.
     */
    public final static DateTimeFormatter DATA_TIME_SECONDS_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * get start day
     *
     * @return start today
     */
    public static Date getStartToday() {
        GregorianCalendar now = new GregorianCalendar();
        return new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).getTime();
    }

    /**
     * Gets start month.
     *
     * @return the start month
     */
    public static Date getStartMonth() {
        GregorianCalendar now = new GregorianCalendar();
        return new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1).getTime();
    }

    /**
     * parse string to date
     *
     * @param time  the time
     * @param parse the parse
     * @return date
     */
    public static Date parse(String time, String parse) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(parse);
            Date dateFromString = sdf.parse(time);
            return dateFromString;
        } catch (ParseException e) {
            logger.info("error when parse");
        }
        return null;
    }

    /**
     * parse date to string
     *
     * @param date  the date
     * @param parse the parse
     * @return string
     */
    public static String format(Date date, String parse) {
        SimpleDateFormat sdf = new SimpleDateFormat(parse);
        String dateFromDate = sdf.format(date);
        return dateFromDate;
    }

    /**
     * Reduce minutes to date date.
     *
     * @param minutes the minutes
     * @param time    the time
     * @return date
     */
    public static Date reduceMinutesToDate(int minutes, Date time) {
        long curTimeInMs = time.getTime();
        Date afterAddingMins = new Date(curTimeInMs - (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }

    /**
     * 将UTC时间转为本地时间(Date).
     *
     * @param utc the utc
     * @return the date
     */
    public static Date toLocalZonedDate(LocalDateTime utc) {
        return Date.from(ZonedDateTime.of(utc, ZoneId.of("UTC")).toInstant());
    }

    /**
     * 将UTC时间转为本地时间(LocalDateTime).
     *
     * @param utc the utc
     * @return the local date time
     */
    public static LocalDateTime toLocalDateTime(LocalDateTime utc) {
        return LocalDateTime.ofInstant(ZonedDateTime.of(utc, ZoneId.of("UTC")).toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将本地时间转为UTC时间
     *
     * @param localTime
     * @return
     */
    public static LocalDateTime toUTCDateTime(LocalDateTime localTime) {
        return LocalDateTime.ofInstant(localTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.of("UTC"));
    }

    public static LocalDateTime getLocalDateTimeFromGMTTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    /**
     *
     * @param time
     * @param zoneIdNew
     * @return
     */
    public static ZonedDateTime parseTimeZongId(ZonedDateTime time, ZoneId zoneIdNew) {
        return ZonedDateTime.ofInstant(time.toInstant(), zoneIdNew);
    }
}
