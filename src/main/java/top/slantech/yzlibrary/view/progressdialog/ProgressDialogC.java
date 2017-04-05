package top.slantech.yzlibrary.view.progressdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import top.slantech.yzlibrary.R;


/**
 * 带单线旋转进度条
 */
public class ProgressDialogC extends Dialog {
    private Context context;

    public ProgressDialogC(Context context) {
        super(context, R.style.dialog_progress);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.c_loading, null);
        setContentView(view);
    }
}
