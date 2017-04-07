package top.slantech.yzlibrary.view.agreeanim;

import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.os.Build;


/**
 * Created by slantech on 2016/12/17 15:07
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class  EasingInterpolator implements TimeInterpolator {

    private final Ease ease;

    public EasingInterpolator(Ease ease) {
        this.ease = ease;
    }

    @Override
    public float getInterpolation(float input) {
        return EasingProvider.get(this.ease, input);
    }

    public Ease getEase() {
        return ease;
    }
}
