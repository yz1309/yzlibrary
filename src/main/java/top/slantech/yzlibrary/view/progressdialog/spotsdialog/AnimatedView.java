package top.slantech.yzlibrary.view.progressdialog.spotsdialog;

import android.content.Context;
import android.view.View;


class AnimatedView extends View {

    private int target;

    public AnimatedView(Context context) {
        super(context);
    }

    public float getXFactor() {
        return getX() / target;
    }

    public void setXFactor(float xFactor) {
        setX(target * xFactor);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
