package com.csxs.core.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isNotNull(String str) {
        if (str != null && !"".equals(str.trim()) && !"null".equalsIgnoreCase(str.trim())&&!"[]".equals(str.trim()))
            return true;
        return false;
    }

    public static String changeNulltoString(String str) {
        if (str != null && !"".equals(str.trim()) && !"null".equalsIgnoreCase(str.trim())&&!"[]".equals(str.trim()) && !"0%".equals(str.trim()))
            return str;
        return "0";
    }


    public static String getDayFormat(String year, String month, String day){
        return year+"-"+month+"-"+day;
    }

    public static String getMonthFormat(String year, String month){
        return year+"-"+month;
    }

    public static String getYesterDayDate(String date, int flag){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parseDate = simpleDateFormat.parse(date);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(parseDate);

            int day = calendar.get(Calendar.DATE);
            if (flag == 0){
                calendar.set(Calendar.DATE, day - 1);
            }else if(flag == 1){
                calendar.set(Calendar.DATE, day + 1);
            }else{
                calendar.set(Calendar.DATE, day - 7);
            }

            String format = simpleDateFormat.format(calendar.getTime());

            return format;
        }catch (Exception e){
            return date;
        }

    }

    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compareDateNull(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public static int compareDateTime(String oldData, String newOldData) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(oldData);
            Date dt2 = df.parse(newOldData);
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String handleGasNum(String str) {
       if (str.contains("#")){
           return str.replace("#","");
       }
        return str;
    }

    public static String formatPrice(Double price){
        return String.format("%.2f", price).toString();
    }

    public static Map<String,String> jsonToMap(String jsonStr){
        Map<String,String> map = new HashMap<>();

        String[] arr = jsonStr.substring(1,jsonStr.length()-1).split(",");
        int size = arr.length;
        for (int i = 0;i < size;i++){
            String str = arr[i];
            map.put(str.substring(1,str.indexOf(":")-1),str.substring(str.indexOf(":")+1));
        }

        return map;
    }


    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }


    //判断字符串是不是以数字开头
    public static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     * @param s
     * @return
     */
    public static String trimToRelaceLeftAndRight(String s){
        String result = "";
        if(null!=s && !"".equals(s)){
            result = s.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
        }
        return result;
    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
