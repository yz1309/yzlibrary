package top.slantech.yzlibrary.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 这样子处理之后 ，给EditText 添加changed事件时不需每个都去重写了
 * Created by admin on 2016/7/26 0026.
 */
public class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
