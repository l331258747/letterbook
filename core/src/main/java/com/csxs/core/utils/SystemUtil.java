package com.csxs.core.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.csxs.core.BaseCoreApp;


import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

/**
 * Created by dugang on 2016/11/21. 系统工具类
 */
@SuppressWarnings("unused")
public class SystemUtil {

    /**
     * 关闭软键盘
     */
    public static void closeInputMethod(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) BaseCoreApp.appConext.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取应用的版本名称
     */
    public static String getVersionName() {
        try {
            PackageManager manager = BaseCoreApp.appConext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseCoreApp.appConext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
           // Logger.e(e.getMessage());
            return "1.0.0";
        }
    }

    /**
     * 获取应用的内部版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = BaseCoreApp.appConext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseCoreApp.appConext.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
           // Logger.e(e.getMessage());
            return 1;
        }
    }

    /**
     * md5加密
     */
    public static String md5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuilder strBuf = new StringBuilder();
            for (byte anEncryption : encryption) {
                if (Integer.toHexString(0xff & anEncryption).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & anEncryption));
                } else {
                    strBuf.append(Integer.toHexString(0xff & anEncryption));
                }
            }
            return strBuf.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取临时文件的文件名称
     */
    public static String getTempFileName() {
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() <= 32) {
            sb.append(str[random.nextInt(str.length)]);
        }
        return sb.toString();
    }

    public static String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/dandan/image/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}
