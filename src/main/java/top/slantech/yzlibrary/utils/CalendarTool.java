package top.slantech.yzlibrary.utils;

import android.text.TextUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时间工具类
 * 1、String类型的日期时间转化为Date类型 getDateByFormat("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 2、获取Date偏移之后的Date getDateByOffset(date,Calendar.DATE,1);
 * 3、获取字符串日期时间的字符串(可偏移) getStringByOffset("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss",Calendar.DATE,1);
 * 4、Date类型转化为String类型(可偏移)  getStringByOffset(date,"yyyy-MM-dd HH:mm:ss",Calendar.DATE,1);
 * 5、Date类型转化为String类型 getStringByFormat((date,"yyyy-MM-dd HH:mm:ss");
 * 6、获取指定日期时间的字符串,用于导出想要的格式 getStringByFormat("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 7、获取milliseconds表示的日期时间的字符串 getStringByFormat(1470816365,"yyyy-MM-dd HH:mm:ss");
 * 8、获取表示当前日期时间的字符串 getCurrentDate("yyyy-MM-dd HH:mm:ss");
 * 9、计算两个日期所差的天数 getOffectDay(1470816365,1470816415); or getOffectDay(startDate,endDate)
 * 10、计算两个日期所差的小时数 getOffectHour(1470816365,1470816415);
 * 11、计算两个日期所差的分钟数 getOffectMinutes(1470816365,1470816415);
 * 12、获取本周一 getFirstDayOfWeek("2016-04-15 12:20:11");
 * 13、获取本周日 getLastDayOfWeek("2016-04-15 12:20:11");
 * 14、获取本周的某一天 getDayOfWeek("2016-04-15 12:20:11",Calendar.DATE);
 * 15、获取本月第一天 getFirstDayOfMonth("2016-04-15 12:20:11");
 * 16、获取本月最后一天 getLastDayOfMonth("2016-04-15 12:20:11");
 * 17、获取表示当前日期的0点时间毫秒数 getFirstTimeOfDay();
 * 18、获取表示当前日期24点时间毫秒数 getLastTimeOfDay();
 * 19、判断是否是闰年 isLeapYear(2014);
 * 20、根据时间返回格式化后的时间的描述 formatDateStr2Desc2("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 21、以友好的方式显示时间  formatDateStr2Desc("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 22、取指定日期为星期几 getWeekNumber("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 23、根据给定的日期判断是否为上下午 getTimeQuantum("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 24、根据给定的毫秒数算得时间的描述 getTimeDescription(1470816365);
 * 25、将Calendar转成字符串 calendarConvertString(calendar,"yyyy-MM-dd HH:mm:ss");
 * 26、将字符串转成Calendar stringConvertCalendar("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");
 * 27、比较2个时间的大小 calendarCompare(calendar1,calendar2);
 * 28、得到当前时间的随机数 getCurRandomDate();
 * 29、某年某月有多少天 getDaysInMonth(2016,5);
 * 30、某年某月的第一天是周几 getFirstWeekDayInMonth(2016,5);
 * 31、得到2个时间戳之间相差的 天 时 分 秒 getDate(1470816365,1470816415);
 * 32、阳历节日 calendarFestival(calendar);
 * 33、阴历节日 lunarHoliday(calendar);
 * Created by slantech on 2016/08/31 11:38
 */
public class CalendarTool {

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";
    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";
    /**
     * 时间日期格式化到年.
     */
    public static final String dateFormatY = "yyyy";
    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
    /**
     * 时间日期格式化到月日.
     */
    public static final String dateFormatMD = "MM/dd";
    /**
     * 时分秒.
     */
    public static final String dateFormatHMS = "HH:mm:ss";
    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";
    /**
     * 上午.
     */
    public static final String AM = "AM";
    /**
     * 下午.
     */
    public static final String PM = "PM";

    private static ChineseCalendarGB chineseCalendarGB = new ChineseCalendarGB();

    static final String[] solarTerms = {"立春", "雨水", "惊蛰", "春分", "清明", "谷雨",
            "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露",
            "霜降", "立冬", "小雪", "大雪", "冬至", "小寒", "大寒"};

    static final int[][] solarTermsDays = {{2, 4}, {2, 19}, {3, 6},
            {3, 21}, {4, 5}, {4, 20}, {5, 6}, {5, 21}, {6, 6},
            {6, 21}, {7, 7}, {7, 23}, {8, 8}, {8, 23}, {9, 8},
            {9, 23}, {10, 8}, {10, 23}, {11, 7}, {11, 22}, {12, 7},
            {12, 22}, {1, 6}, {1, 20}};

    static final String[] festivals = {"元旦", "情人", "女生", "妇女", "植树", "愚人",
            "劳动", "青年", "儿童", "建军", "教师", "国庆", "万圣", "光棍", "平安", "圣诞"};

    static final int[][] festivalsDays = {{1, 1}, {2, 14}, {3, 7},
            {3, 8}, {3, 12}, {4, 1}, {5, 1}, {5, 4}, {6, 1},
            {8, 1}, {9, 10}, {10, 1}, {10, 31}, {11, 11}, {12, 24},
            {12, 25}};

    static final String[] chineseFestivals = {"春节", "元宵", "花朝", "上巳节", "端午",
            "七夕", "中元", "中秋", "重阳", "祭祖", "下元", "腊八"};

    static final int[][] chineseFestivalsDays = {{1, 1}, {1, 15},
            {2, 12}, {3, 3}, {5, 5}, {7, 7}, {7, 15}, {8, 15},
            {9, 9}, {10, 1}, {10, 15}, {12, 8}};


    /**
     * String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取Date偏移之后的Date.
     *
     * @param date          日期时间
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = new GregorianCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获取字符串日期时间的字符串(可偏移).
     *
     * @param strDate       String形式的日期时间
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值，
     *                      如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * Date类型转化为String类型(可偏移).
     *
     * @param date          the date
     * @param format        the format
     * @param calendarField the calendar field
     * @param offset        the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format, int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format       格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }

    /**
     * 获取表示当前日期时间的字符串.
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;

    }

    /**
     * 获取表示当前日期时间的字符串(可偏移).
     *
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;

    }

    /**
     * 计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        //先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 计算两个日期所差的天数.
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @return int
     */
    public static int getOffectDay(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
        return m;
    }

    /**
     * 获取本周一.
     *
     * @param format format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 获取本周日.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 获取本周的某一天.
     *
     * @param format        format
     * @param calendarField calendarField
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 获取本月第一天.
     *
     * @param format format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            //当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 获取本月最后一天.
     *
     * @param format format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 获取表示当前日期的0点时间毫秒数.
     *
     * @return long the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 获取表示当前日期24点时间毫秒数.
     *
     * @return long the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 判断是否是闰年()(year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate     sdate
     * @param outFormat outFormat
     * @return String
     */
    public static String formatDateStr2Desc(String sdate, String outFormat) {
        try {

            if (!TextUtils.isEmpty(sdate) && (sdate.length() == 10 || sdate.length() == 9 || sdate.length() == 8)) {
                sdate = sdate + " 00:00";
            }

            Date time = getDateByFormat(sdate, dateFormatYMDHM);

            String ftime = "";
            Calendar cal = Calendar.getInstance();
            // 判断是否是同一天
            String curDate = getCurrentDate(dateFormatYMDHM);
            String paramDate = getStringByFormat(time, dateFormatYMDHM);

            if (curDate.equals(paramDate)) {
                int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
                if (hour == 0)
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                else
                    ftime = hour + "小时前";
                return ftime;
            }

            long lt = time.getTime() / 86400000;
            long ct = cal.getTimeInMillis() / 86400000;
            int days = (int) (ct - lt);
            if (days == 0) {
                int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
                if (hour == 0)
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                else
                    ftime = hour + "小时前";
            } else if (days == 1) {
                ftime = "昨天";
            } else if (days == 2) {
                ftime = "前天 ";
            } else if (days > 2 && days < 31) {
                ftime = days + "天前";
            } else if (days >= 31 && days <= 2 * 31) {
                ftime = "一个月前";
            } else if (days > 2 * 31 && days <= 3 * 31) {
                ftime = "2个月前";
            } else if (days > 3 * 31 && days <= 4 * 31) {
                ftime = "3个月前";
            } else {
                ftime = getStringByFormat(time, outFormat);
            }
            return ftime;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static String getWeekNumber(String strDate, String inFormat) {
        String week = "星期日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 根据给定的日期判断是否为上下午.
     *
     * @param strDate strDate
     * @param format  format
     * @return the time quantum
     */
    public static String getTimeQuantum(String strDate, String format) {
        Date mDate = getDateByFormat(strDate, format);
        int hour = mDate.getHours();
        if (hour >= 12)
            return "PM";
        else
            return "AM";
    }

    /**
     * 根据给定的毫秒数算得时间的描述.
     *
     * @param milliseconds the milliseconds
     * @return the time description
     */
    public static String getTimeDescription(long milliseconds) {
        if (milliseconds > 1000) {
            //大于一分
            if (milliseconds / 1000 / 60 > 1) {
                long minute = milliseconds / 1000 / 60;
                long second = milliseconds / 1000 % 60;
                return minute + "分" + second + "秒";
            } else {
                //显示秒
                return milliseconds / 1000 + "秒";
            }
        } else {
            return milliseconds + "毫秒";
        }
    }


    /**
     * 将Calendar转成字符串
     *
     * @param calendar calendar
     * @param format   指定格式
     * @return String
     */
    public static String calendarConvertString(Calendar calendar, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转成Calendar
     *
     * @param times  times
     * @param format 指定格式
     * @return Calendar
     */
    public static Calendar stringConvertCalendar(String times, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(times);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /***
     * 比较2个时间的大小
     *
     * @param one one
     * @param two two
     * @return int ==0相等 >0one大于two <0one小于two
     */
    public static int calendarCompare(Calendar one, Calendar two) {
        int num = -1;
        try {
            num = one.compareTo(two);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return num;
    }

    /**
     * 得到当前时间的随机数
     *
     * @return String
     */
    public static String getCurRandomDate() {
        Calendar calendar = Calendar.getInstance();
        String curTimes = calendar.get(Calendar.YEAR) + ""
                + (calendar.get(Calendar.MONTH) + 1) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + ""
                + calendar.get(Calendar.SECOND) + ""
                + calendar.get(Calendar.MILLISECOND);

        return curTimes;
    }

    /**
     * 某年某月有多少天
     *
     * @param year  year
     * @param month month
     * @return int
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 某年某月的第一天是周几
     *
     * @param year  year
     * @param month month
     * @return int
     */
    public static int getFirstWeekDayInMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 得到2个时间戳之间相差的 天 时 分 秒
     *
     * @param date1 date1
     * @param date2 date2
     * @return long[]
     */
    public static long[] getDate(long date1, long date2) {
        long[] times = new long[]{0, 0, 0, 0};
        try {
            SimpleDateFormat dfs = new SimpleDateFormat(dateFormatYMDHMS);
            Date begin = dfs.parse(getStringByFormat(date1, dateFormatYMDHMS));

            Date end = dfs.parse(getStringByFormat(date2, dateFormatYMDHMS));


            long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒

            long day1 = between / (24 * 3600);
            long hour1 = between % (24 * 3600) / 3600;
            long minute1 = between % 3600 / 60;
            long second1 = between % 60 / 60;
            times[0] = day1;
            times[1] = hour1;
            times[2] = minute1;
            times[3] = second1;
        } catch (Exception ex) {
        }
        return times;
    }


    /**
     * 阳历节日
     *
     * @param calendar calendar
     * @return String
     */
    public static String calendarFestival(Calendar calendar) {
        String festival = "";
        int paMonth = calendar.get(Calendar.MONTH) == 12 ? 1 : calendar.get(Calendar.MONTH) + 1;
        int paDay = calendar.get(Calendar.DAY_OF_MONTH);
        //二十四节气
        int len = solarTermsDays.length;
        for (int i = 0; i < len; i++) {
            int[] temp = solarTermsDays[i];
            if (temp[0] == paMonth && temp[1] == paDay) {
                festival = solarTerms[i];
            }
        }

        //常规节日
        int len2 = festivalsDays.length;
        for (int i = 0; i < len2; i++) {
            int[] temp = festivalsDays[i];
            if (temp[0] == paMonth && temp[1] == paDay) {
                festival = festivals[i];
            }
        }
        return festival;
    }

    /**
     * 阴历节日
     *
     * @param calendar calendar
     * @return String
     */
    public static String lunarHoliday(Calendar calendar) {
        String holiday = "";
        chineseCalendarGB.setCalendar(calendar);
        int year = chineseCalendarGB.getYear();
        int month = chineseCalendarGB.getMonth();
        int day = chineseCalendarGB.getintDay();
        if (day == 1) {
            switch (month) {
                case 1:
                    holiday = "一月";
                    break;
                case 2:
                    holiday = "二月";
                    break;
                case 3:
                    holiday = "三月";
                    break;
                case 4:
                    holiday = "四月";
                    break;
                case 5:
                    holiday = "五月";
                    break;
                case 6:
                    holiday = "六月";
                    break;
                case 7:
                    holiday = "七月";
                    break;
                case 8:
                    holiday = "八月";
                    break;
                case 9:
                    holiday = "九月";
                    break;
                case 10:
                    holiday = "十月";
                    break;
                case 11:
                    holiday = "冬月";
                    break;
                case 12:
                    holiday = "蜡月";
                    break;
            }
        }
        int len = chineseFestivalsDays.length;
        for (int i = 0; i < len; i++) {
            int[] temp = chineseFestivalsDays[i];
            if (temp[0] == month && temp[1] == day) {
                holiday = chineseFestivals[i];
            }
        }
        // 判断寒食和清明
        int paMonth = calendar.get(Calendar.MONTH) == 12 ? 1 : calendar
                .get(Calendar.MONTH) + 1;
        int paDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (paMonth == 4 && paDay == 4) {
            holiday = "寒食";
        } else if (paMonth == 4 && paDay == 5) {
            holiday = "清明";
        }
        // 12月中除夕及小年的日期
        if (month == 12) {
            if (ChineseCalendarGB.monthDays(year, month) == 30) {
                if (day == 24) {
                    holiday = "小年";
                }
                if (day == 30) {
                    holiday = "除夕";
                }
            } else if (ChineseCalendarGB.monthDays(year, month) == 29) {
                if (day == 23) {
                    holiday = "小年";
                }
                if (day == 29) {
                    holiday = "除夕";
                }
            }
        }
        return holiday;

    }


}
