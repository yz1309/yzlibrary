package top.slantech.yzlibrary.utils;

import android.util.Log;

/**
 * 自定义Log输出
 * 可以配合Application 控制 tag 以及 isOpen 的值
 * Created by admin on 2015年12月10日 000010.
 */
public class ULog {

    public static String tag = "ULog";

    public static Boolean isOpen = true;

    public static void e(String msg) {
        if (isOpen)
            Log.e(tag, msg);
    }

    public static void i(String msg) {
        if (isOpen)
            Log.i(tag, msg);
    }

    public static void d(String msg) {
        if (isOpen)
            Log.d(tag, msg);
    }

    public static void w(String msg) {
        if (isOpen)
            Log.w(tag, msg);
    }

    public static void v(String msg) {
        if (isOpen)
            Log.v(tag, msg);
    }
}
