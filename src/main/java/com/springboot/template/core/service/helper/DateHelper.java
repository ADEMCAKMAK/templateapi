package com.springboot.template.core.service.helper;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateHelper {

    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_LONG_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_SHORT_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_PERIOD_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM"));
    private static final ThreadLocal<SimpleDateFormat> format = ThreadLocal.withInitial(() -> {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        return df;
    });

    /**********************************************************************************************************************/

    // arithmetic Date methods started
    public static Date add(Date date, int calenderUnit, int amount) {
        Calendar calendar = toCalendar(date);
        calendar.add(calenderUnit, amount);
        return calendar.getTime();
    }

    public static Date addDays(int amount) {
        return addDays(today(), amount);
    }

    public static Date addDays(Date date, int amount) {
        return DateHelper.add(date, Calendar.DATE, amount);
    }

    public static Date addDate(Date date, int hour, int minute, int second, int ms) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, ms);
        return calendar.getTime();
    }

    public static Date addTime(Date date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date addWeeks(Date date, int amount) {
        Calendar calendar = toCalendar(date);
        return addDays(date, amount * calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
    }

    public static Date addWeeks(int amount) {
        return addWeeks(today(), amount);
    }

    // arithmetic Date methods ended

    /**********************************************************************************************************************/

    // comparison Date methods started
    public static boolean lessThan(Date d1, Date d2) {
        return d1.before(d2) && !d1.equals(d2);
    }

    public static boolean lessThanOrEqual(Date d1, Date d2) {
        return d1.before(d2) || d1.equals(d2);
    }

    public static boolean greaterThan(Date d1, Date d2) {
        return d1.after(d2) && !d1.equals(d2);
    }

    public static boolean greaterThanOrEqual(Date d1, Date d2) {
        return d1.after(d2) || d1.equals(d2);
    }

    // comparison Date methods ended

    /**********************************************************************************************************************/

    // Date methods started
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static Date today() {
        Calendar calendar = toCalendar(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date tomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date tomorrow() {
        return tomorrow(today());
    }

    public static Date yesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date yesterday() {
        return yesterday(today());
    }

    public static Date startOfDay() {
        return startOfDay(today());
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date endOfDay() {
        return endOfDay(today());
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date firstDayOfWeek(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    public static Date firstDayOfWeek() {
        return firstDayOfWeek(today());
    }

    public static Date lastDayOfWeek(Date date) {
        Calendar calendar = toCalendar(firstDayOfWeek(date));
        calendar.add(Calendar.DATE, 6);
        return calendar.getTime();
    }

    public static Date lastDayOfWeek() {
        return lastDayOfWeek(today());
    }

    public static Date firstDayOfMonth() {
        return firstDayOfMonth(today());
    }

    public static Date firstDayOfMonth(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date lastDayOfMonth() {
        return lastDayOfMonth(today());
    }

    public static Date lastDayOfMonth(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date toDate(SimpleDateFormat simpleDateFormat, String source) {
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date toShortDate(String source) {
        return toDate(DEFAULT_SHORT_DATE_FORMAT.get(), source);
    }

    public static Date toDate(String source) {
        return toDate(DEFAULT_DATE_FORMAT.get(), source);
    }

    public static Date toLongDate(String source) {
        return toDate(DEFAULT_LONG_DATE_FORMAT.get(), source);
    }

    public static Date toDate(String pattern, String source) {
        return toDate(new SimpleDateFormat(pattern), source);
    }

    // date methods ended

    /**********************************************************************************************************************/

    // toString methods started
    public static String toString(String pattern, Date date) {
        return toString(new SimpleDateFormat(pattern), date);
    }

    public static String toString(SimpleDateFormat simpleDateFormat, Date date) {
        return simpleDateFormat.format(date);
    }

    public static String toString(SimpleDateFormat simpleDateFormat, Date date, TimeZone timeZone) {
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    public static String toShortString(Date date) {
        return toString(DEFAULT_SHORT_DATE_FORMAT.get(), date);
    }

    public static String toString(Date date) {
        return toString(DEFAULT_DATE_FORMAT.get(), date);
    }

    public static String toLongString(Date date) {
        return toString(DEFAULT_LONG_DATE_FORMAT.get(), date);
    }

    // toString methods ended

    /**********************************************************************************************************************/

    // time methods started
    public static long timeDifference(Date start, Date end) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT.get().toPattern());
        LocalDateTime s = LocalDateTime.parse(DateHelper.toString(start), dateTimeFormatter);
        LocalDateTime e = LocalDateTime.parse(DateHelper.toString(end), dateTimeFormatter);
        Duration duration = Duration.between(s, e);
        return Math.abs(duration.toMinutes());
    }

    // time methods ended

    /**********************************************************************************************************************/

    // other methods
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String dayOfName(Date date) {
        Calendar calendar = toCalendar(date);
        return getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];
    }

    public static int dayOfNumber(Date date) {
        Calendar calendar = toCalendar(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getStartOfDay(String date) {
        Date d = DateHelper.toShortDate(date);
        Date start = DateHelper.startOfDay(d);
        return DateHelper.toLongString(start);
    }

    public static String getEndOfDay(String date) {
        Date d = DateHelper.toShortDate(date);
        Date end = DateHelper.endOfDay(d);
        return DateHelper.toLongString(end);
    }

    public static String[] getWeekdays() {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        return symbols.getWeekdays();
    }

    public static Integer currentYear() {
        Calendar calendar = toCalendar(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public static Integer currentMonth() {
        Calendar calendar = toCalendar(new Date());
        return calendar.get(Calendar.MONTH);
    }
}
