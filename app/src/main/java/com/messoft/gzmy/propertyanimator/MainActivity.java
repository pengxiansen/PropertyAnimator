package com.messoft.gzmy.propertyanimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @作者 Administrator
 * 属性动画的高级使用
 * 参考锅婶的 http://blog.csdn.net/guolin_blog/article/details/43816093
 * @创建日期 2017/11/30 0030 9:26
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * ValueAnimator的高级用法
     *
     * @param view
     */
    public void ValueAnimator1(View view) {
        startActivity(new Intent(MainActivity.this, ValueAnimator1Activity.class));
    }

    /**
     * ObjectAnimator的高级用法
     * @param view
     */
    public void ObjectAnimator1(View view) {
        startActivity(new Intent(MainActivity.this, ObjectAnimator1Activity.class));
    }
}
