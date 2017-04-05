package top.slantech.yzlibrary.view.progressdialog.spotsdialog;

import android.animation.*;
import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 05.05.15 at 14:45
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class AnimatorPlayer extends AnimatorListenerAdapter {

    private boolean interrupted = false;
    private Animator[] animators;

    public AnimatorPlayer(Animator[] animators) {
        this.animators = animators;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!interrupted) animate();
    }

    public void play() {
        animate();
    }

    public void stop() {
        interrupted = true;
    }

    private void animate() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        set.addListener(this);
        set.start();
    }
}
