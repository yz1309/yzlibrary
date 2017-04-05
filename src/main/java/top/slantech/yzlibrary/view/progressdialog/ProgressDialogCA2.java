package top.slantech.yzlibrary.view.progressdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import top.slantech.yzlibrary.R;


/**
 * 带多个圆点旋转动画进度条和文本[彩色]
 * Created by Administrator on 2014/9/18.
 */
public class ProgressDialogCA2 extends ProgressDialog {

    private Context mContext;
    private String msg;
    private TextView content;
    private ImageView iv;
    private AnimationDrawable animationDrawable;

    public ProgressDialogCA2(Context context, String message) {
        super(context, R.style.Theme_dialog);
        this.mContext = context;
        this.msg = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.ca2_loading, null);
        content = (TextView) contentView.findViewById(R.id.content);
        content.setText(msg);

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0.5f;
        onWindowAttributesChanged(lp);
        setCanceledOnTouchOutside(false);
        iv = (ImageView) contentView.findViewById(R.id.iv_anim);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.start();
        setContentView(contentView);

    }

    public void setMessage(CharSequence c) {
        content.setText(c);
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        animationDrawable.stop();
    }
}

