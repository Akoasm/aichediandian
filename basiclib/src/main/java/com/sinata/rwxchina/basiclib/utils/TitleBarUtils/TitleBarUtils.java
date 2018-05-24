package com.sinata.rwxchina.basiclib.utils.TitleBarUtils;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sinata.rwxchina.basiclib.view.MyScrollView;

/**
 * @author:wj
 * @datetime：2017/11/7
 * @describe：scrollview滑动过程中标题栏的变化
 * @modifyRecord:
 */


public class TitleBarUtils {
    private MyScrollView myScrollView;
    private LinearLayout title_linear;
    private ImageView back_circle,back_icon;
    private ViewPager viewPager;
    private ImageView default_img;
    private int height,width;

    public TitleBarUtils(MyScrollView myScrollView, LinearLayout title_linear, ImageView back_circle, ImageView back_icon, ViewPager viewPager) {
        this.myScrollView = myScrollView;
        this.title_linear = title_linear;
        this.back_circle = back_circle;
        this.back_icon = back_icon;
        this.viewPager = viewPager;
        isViewPager();
    }

    public TitleBarUtils(MyScrollView myScrollView, LinearLayout title_linear, ImageView back_circle, ImageView back_icon, ImageView default_img) {
        this.myScrollView = myScrollView;
        this.title_linear = title_linear;
        this.back_circle = back_circle;
        this.back_icon = back_icon;
        this.default_img = default_img;
        isImage();
    }

    private void isViewPager(){
        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                height = viewPager.getMeasuredHeight();
                width = viewPager.getMeasuredWidth();
                return true;
            }
        });
    }
    private void isImage(){
        ViewTreeObserver vto = default_img.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                height = default_img.getMeasuredHeight();
                width = default_img.getMeasuredWidth();
                return true;
            }
        });
    }
    public void isSlide() {
        title_linear.setBackgroundColor(Color.argb(255, 255, 255, 255));
        myScrollView.setScrollViewListener(new MyScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    title_linear.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    title_linear.setVisibility(View.GONE);
                    back_circle.setVisibility(View.VISIBLE);
                } else if (y > 0 && y <= height) {
                    float scale = (float) y / height;
                    float alpha = (255 * scale);
                    title_linear.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    title_linear.setVisibility(View.VISIBLE);
                    back_circle.setVisibility(View.GONE);
                    back_icon.setEnabled(true);
                } else {
                    title_linear.setBackgroundColor(Color.argb(255, 255, 255, 255));
                }
            }

            @Override
            public void onScrollStop(boolean isScrollStop) {

            }
        });
    }
    }
