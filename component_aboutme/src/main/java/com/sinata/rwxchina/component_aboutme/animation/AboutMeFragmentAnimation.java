package com.sinata.rwxchina.component_aboutme.animation;

import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author:zy
 * @detetime:2017/11/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class AboutMeFragmentAnimation {
    private void startanmition(){
        ViewPath path = new ViewPath();
        ObjectAnimator anim;
        path.moveTo(0, 0);
//        path.lineTo(0, 500);
        path.lineTo(-300, 0);

        anim = ObjectAnimator.ofObject(this, "fabLoc", new ViewPathEvaluator(), path.getPoints().toArray());
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

}
