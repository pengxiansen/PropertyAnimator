package com.messoft.gzmy.propertyanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class ObjectAnimator1View extends View {

    private static final float RADIUS = 50f;

    private Point currentPoint;

    private Paint mPaint;

    private ValueAnimator mAnim;

    private String color;
    private ObjectAnimator mAnima2;
    private AnimatorSet mAnimatorSet;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    public ObjectAnimator1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth() / 2, RADIUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIUS);
        mAnim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        //value
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        //object
        mAnima2 = ObjectAnimator.ofObject(this, "color", new ObjectAnimatorEvaluator(), "#0000FF", "#FF0000");
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(mAnim).with(mAnima2);
        //增加插值器--
        //加速度
//        mAnimatorSet.setInterpolator(new AccelerateInterpolator(2f));
        //掉落反弹
//        mAnimatorSet.setInterpolator(new BounceInterpolator());
        //匀速
//        mAnimatorSet.setInterpolator(new LinearInterpolator());
        //系统默认 加速-再加速-减速
//        mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        //自定义 加速-减速
        mAnimatorSet.setInterpolator(new DecelerateAccelerateInterpolator());
        mAnimatorSet.setDuration(5000);
        mAnimatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAnim != null) {
            mAnim.cancel();
        }
        if (mAnima2 != null) {
            mAnima2.cancel();
        }
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
    }
}
