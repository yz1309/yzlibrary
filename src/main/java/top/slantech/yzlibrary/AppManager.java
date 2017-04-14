package top.slantech.yzlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * 功能描述：
 * 1、添加Activity到堆栈 addActivity(Activity activity);
 * 2、获取当前Activity（堆栈中最后一个压入的） currentActivity();
 * 3、结束当前Activity（堆栈中最后一个压入的） finishActivity();
 * 4、结束指定的Activity finishActivity(Activity activity);
 * 5、结束指定类名的Activity finishActivity(cls);
 * 6、结束所有Activity finishAllActivity();
 * 7、退出应用程序 AppExit(Context context);
 * 8、移除指定的Activity removeActivity(Activity activity);
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                Activity activity = activityStack.get(i);
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     * @param context context
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 移除指定的Activity
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 在浏览器中打开指定的URL
     *
     * @param context context
     * @param url url
     */
    public static void goUrl(Activity context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        // path 包含 http:
        Uri content_uri_browsers = Uri.parse(url);
        intent.setData(content_uri_browsers);
        context.startActivity(intent);
    }

    /**
     * Activity 跳转
     *
     * @param context context
     * @param tClass tClass
     */
    public static void goActivity(Activity context, Class<?> tClass) {
        Intent intent = new Intent(context, tClass);
        context.startActivity(intent);
    }

    /**
     * Activity 跳转
     *
     * @param context context
     * @param intent intent
     */
    public static void goActivity(Activity context, Intent intent) {
        context.startActivity(intent);
    }
}