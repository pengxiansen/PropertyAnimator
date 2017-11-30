package com.messoft.gzmy.propertyanimator;

import android.animation.TimeInterpolator;

/**
 * Created by Administrator on 2017/11/30 0030.
 * 先减速后加速
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {

    /**
     * input 0-1变化
     *
     * @param input
     * @return
     */
    @Override
    public float getInterpolation(float input) {

        float result;
        if (input < 0.5) {
            //减速 正弦 0 -- 2分之一派
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            //加速 正弦 2分之派 -- 派
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;


    }
}
