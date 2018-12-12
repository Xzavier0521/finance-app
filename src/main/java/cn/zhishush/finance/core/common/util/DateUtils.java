package cn.zhishush.finance.core.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: DateUtils.java, v 0.1 2018/5/17 lili Exp $$
 */
public class DateUtils {

    public final static String SHORT_FORMAT    = "yyyyMMdd";
    public final static String WEB_FORMAT      = "yyyy/MM/dd";
    public final static String LONG_WEB_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String LONG_APP_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public final static String HOUR_FORMAT     = "HHmmss";

    public static Date getCurrentWorkDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date parseDateNoTimeFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_FORMAT);
        Date d = null;

        if ((sDate != null) && (sDate.length() == SHORT_FORMAT.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    public static Set<Date> getBetweenDates(Date startDate, Date endDate, boolean isLeftClose,
                                            boolean isRightClose) {
        Set<Date> result = new HashSet<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(startDate);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(endDate);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        if (isLeftClose) {
            result.add(startDate);
        }
        if (isRightClose) {
            result.add(endDate);
        }
        return result;
    }

    public static Integer getCurrentDay(LocalDate localDate) {
        return Integer.valueOf(localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public static String getFormatDateStr(LocalDateTime localDateTime, String format) {
        return String.valueOf(localDateTime.format(DateTimeFormatter.ofPattern(format)));
    }

    public static String format(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
