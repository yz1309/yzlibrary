package top.slantech.yzlibrary.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import top.slantech.yzlibrary.R;

/**
 * Created by slantech on 2017/03/22 15:11
 */
public class ToastUtils {
    private static Activity mContext;
    private static ToastUtils mInstance;
    private Toast mToast;

    public static ToastUtils getInstance() {
        return mInstance;
    }

    public static void init(Activity ctx) {
        mInstance = new ToastUtils(ctx);
    }

    private ToastUtils(Activity ctx) {
        mContext = ctx;
    }

    public void showLToast(String mes) {
        if (mToast == null) {
            //获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象
            LayoutInflater inflater = mContext.getLayoutInflater();
            //根据指定的布局文件创建一个具有层级关系的View对象
            //第二个参数为View对象的根节点，即LinearLayout的ID
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) mContext.findViewById(R.id.llToast));
            //查找ImageView控件
            TextView text = (TextView) layout.findViewById(R.id.tvMes);
            text.setText(mes);
            mToast = new Toast(mContext);
            //设置Toast的位置
            // Y -100 剧中的上面100
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_LONG);
            //让Toast显示为我们自定义的样子
            mToast.setView(layout);
        } else {
            mToast.setText(mes);
        }
        mToast.show();
    }

    public void showSToast(String mes) {
        if (mToast == null) {
            //获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象
            LayoutInflater inflater = mContext.getLayoutInflater();
            //根据指定的布局文件创建一个具有层级关系的View对象
            //第二个参数为View对象的根节点，即LinearLayout的ID
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) mContext.findViewById(R.id.llToast));
            //查找ImageView控件
            TextView text = (TextView) layout.findViewById(R.id.tvMes);
            text.setText(mes);
            mToast = new Toast(mContext);
            //设置Toast的位置
            // Y -100 剧中的上面100
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            //让Toast显示为我们自定义的样子
            mToast.setView(layout);
        } else {
            mToast.setText(mes);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
