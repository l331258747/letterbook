package com.csxs.core.utils;

import android.util.Log;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间类
 *
 * @author longqiwei
 * @since 2014-12-15
 */
public class TimeStampUtils {
    /**
     * 获取系统当前时间
     * 返回需要时间格式的字符串
     *
     * @param template yyyy-MM-dd HH:mm:ss:SSS
     */
    public static String getCurrentTimeS(String template) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, Locale.getDefault());

        return simpleDateFormat.format(System.currentTimeMillis());

    }//getCurrentTimeS()


    /**
     * 获取系统当前时间
     * 返回毫秒值
     */
    public static long getCurrentTimeL() {
        return System.currentTimeMillis();

    }//getCurrentTimeL()


    /**
     * 将String时间格式转为毫秒值
     *
     * @param template yyyy-MM-dd HH:mm:ss
     */
    public static long StringToLong(String template, String time) {
        long timeL = 0;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, Locale.getDefault());
            timeL = simpleDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeL;

    }


    public static long stringTolong(String strTime, String formatType) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatType,Locale.getDefault());
            date = formatter.parse(strTime);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return date.getTime();
    }


    /**
     * 将毫秒时间格式转为String
     *
     * @param template yyyy-MM-dd HH:mm:ss:SSS
     */
    public static String LongToString(String template, long milliseconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, Locale.getDefault());

        return simpleDateFormat.format(milliseconds);

    }//StringToLong()


    public static String ago(String time) {
        if (time == null || time.equals("") || time.equals("null")) {
            return "";
        }

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd HH:mm");
            SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sf4.parse(time);
            Long sec = 1000l;
            Long min = sec * 60;
            Long hor = min * 60;
            int courrentYear = cal.get(Calendar.YEAR);
            int courrentDay = cal.get(Calendar.DAY_OF_YEAR);
            Long currentTime = cal.getTime().getTime();
            Long dateTime = date.getTime();
            Long bettwen = currentTime - dateTime;
            ////////
            cal.setTime(date);
            int dateYear = cal.get(Calendar.YEAR);
            int dateDay = cal.get(Calendar.DAY_OF_YEAR);
            if (bettwen < sec) {
                return "刚刚";
            } else if (bettwen < min) {
                return (bettwen / sec) + "秒前";
            } else if (bettwen < hor) {
                return (bettwen / min) + "分钟前";
            } else if (bettwen < (hor * 3)) {
                return (bettwen / hor) + "小时前";
            } else if (courrentYear == dateYear) {
                if (dateDay == courrentDay) {
                    return "今天 " + sf1.format(date);
                } else if ((courrentDay - dateDay) == 1) {
                    return "昨天 " + sf1.format(date);
                } else {
                    return sf2.format(date);
                }
            }
            return sf3.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringYMDHMS_SSS(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss:SSS");
            date = sdf.format(new Date(Long.parseLong(time)));
        }

        return date;
    }

    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringYMDHM(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
            date = sdf.format(new Date(Long.parseLong(time)));

        }

        return date;
    }

    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringYMDHMS(String time) {
        String date = null; //初始化date
        if (StringUtil.isNotNull(time)) {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            date = sdf.format(new Date(Long.parseLong(time)));
        } else {
            date = "";
        }

        return date;
    }


    public static String dateChangeToStringYMD(long time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date(time));
        return date;
    }


    public static String dateChangeToStringYMD(Date time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(time);
        return date;
    }

    public static String dateChangeToStringMD(long time) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String date = sdf.format(new Date(time));
        return date;
    }

    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringMYD(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(new Date(Long.parseLong(time)));

        }

        return date;
    }


    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringMD(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            date = sdf.format(new Date(Long.parseLong(time)));

        }

        return date;
    }

    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringMY(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            date = sdf.format(new Date(Long.parseLong(time)));

        }

        return date;
    }

    /**
     * date转换为String
     *
     * @param time
     * @return
     */
    public static String dateChangeToStringYear(String time) {
        String date = null; //初始化date
        if (time == null) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年");
            date = sdf.format(new Date(Long.parseLong(time)));

        }

        return date;
    }

    /**
     * String转换为Date
     *
     * @param time
     * @return
     */
    public static Date stringChangeToDate(String time) {
        Date date = null; //初始化date
        if ("".equals(time) || time == null || "null".equals(time)) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                date = sdf.parse(time); //Mon Jan 14 00:00:00 CST 2013
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }



    public static Date stringChangeToDate(String time,String formatType) {
        Date date = null; //初始化date
        if ("".equals(time) || time == null || "null".equals(time)) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat(formatType);

            try {
                date = sdf.parse(time); //Mon Jan 14 00:00:00 CST 2013
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }


    /**
     * String转换为Date
     *
     * @param time
     * @return
     */
    public static Date stringChangeToDateYMD(String time) {
        Date date = null; //初始化date
        if ("".equals(time) || time == null || "null".equals(time)) {

        } else {
            //String转换为java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                date = sdf.parse(time); //Mon Jan 14 00:00:00 CST 2013
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    /**
     * 计算停车时间
     *
     * @return
     */
    public static String calculateTimes(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            long l = endTime.getTime() - startTime.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            String time = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";

            return time;
        }
        return "";

    }

    /**
     * String转换为Date
     *
     * @param timeStamp
     * @return
     */
    public static Date timeStampChangeToDate(String timeStamp) {
        Date date = null; //初始化date
        if ("".equals(timeStamp) || timeStamp == null || "null".equals(timeStamp)) {

        } else {
            date = new Date(Long.parseLong(timeStamp));
        }

        return date;
    }


    public static String minChangeDayHourMinS(String time) {
      //  Logger.d("minChangeDayHourMinS time:" + time);
        long mss;
        if (!"".equals(time) && time != null) {
            mss = Integer.parseInt(time) * 60;
        } else {
            return "";
        }
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
//        Logger.d("minChangeDayHourMinS days:" + days);
//        Logger.d("minChangeDayHourMinS hours:" + hours);
//        Logger.d("minChangeDayHourMinS minutes:" + minutes);
//        Logger.d("minChangeDayHourMinS seconds:" + seconds);

        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟"
                    + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }
      //  Log.d("minChangeDayHourMinS DateTimes:" + DateTimes);

        return DateTimes;
    }


    /**
     * 转换为小时
     */
    public static String getStart(int getBase_start) {
        Double st = getBase_start / (double) 60;
        String start = st + "小时";
        return start;
    }

    /**
     * 转换为小时
     */
    public static String getEnd(int getBase_start, int getBase_end) {
        Double en = 0.0;
        String end = en + "小时以上";
        if (getBase_end != -1) {
            en = getBase_end / (double) 60;
            end = en + "小时";
        } else {
//			en = getBase_start/(double)60;
//			end = en+"小时以上";
            end = 24 + "小时";
        }
        return end;
    }


    public static String getNeedMonth(int last) {
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -last);
        Date m = c.getTime();

        String mon = c.get(Calendar.MONTH) + 1 + "";
        return mon;
//		System.out.println("过去一个月："+mon);
    }

    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    public static String getCurrentMonth() {
        Calendar date = Calendar.getInstance();
        String month = String.valueOf(date.get(Calendar.MONTH) + 1);
        return month;
    }

    public static String dateChangeToYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int CountdifferentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static String getShortTime(long time) {
        String shortstring = null;
        long now = Calendar.getInstance().getTimeInMillis();
       // Date date = getDateByString(time);
        //if(date == null) return shortstring;
        long deltime = (now - time)/1000;
        if(deltime > 365*24*60*60) {
            shortstring = (int)(deltime/(365*24*60*60)) + "年前";
        } else if(deltime > 24*60*60) {
            if(deltime/(24*60*60)>30){
                SimpleDateFormat sdf = new SimpleDateFormat("M月d日 HH:mm");
                shortstring = sdf.format(new Date(time));
            }else if(deltime/(24*60*60)<2&&deltime/(24*60*60)>1){
                shortstring = "昨天";
            }else{
                shortstring = (int)(deltime/(24*60*60)) + "天前";
            }
        } else if(deltime > 60*60) {
            shortstring = (int)(deltime/(60*60)) + "小时前";
        } else if(deltime > 60) {
            shortstring = (int)(deltime/(60)) + "分前";
        } else if(deltime > 1) {
            shortstring = deltime + "秒前";
        } else {
            shortstring = "刚刚";
        }
        return shortstring;
    }



    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }



    public static void main(String[] args) {

//        Calendar cal = Calendar.getInstance();
//        //当前年
//        int year = cal.get(Calendar.YEAR);
//        //当前月
//        int month = (cal.get(Calendar.MONTH))+5;
//        //当前月的第几天：即当前日
//        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
//        //当前时：HOUR_OF_DAY-24小时制；HOUR-12小时制
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        //当前分
//        int minute = cal.get(Calendar.MINUTE);
//        //当前秒
//        int second = cal.get(Calendar.SECOND);
//        //0-上午；1-下午
//        int ampm = cal.get(Calendar.AM_PM);
//        //当前年的第几周
//        int week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
//        //当前月的第几周
//        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
//        //当前年的第几天
//        int day_of_year = cal.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
           Date data= sdf.parse("2019-5-8 12:12:56");
            String str=  getShortTime(data.getTime());
            Log.e("SimpleDateFormat",str);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
