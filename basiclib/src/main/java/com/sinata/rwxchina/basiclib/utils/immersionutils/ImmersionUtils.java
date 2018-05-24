package com.sinata.rwxchina.basiclib.utils.immersionutils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.view.MyScrollView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 沉浸式工具类
 * @modifyRecord
 */

public class ImmersionUtils {
    private static int height = 0;

    /**
     * 返回键
     */
    private static ImageView backCircle, back;
    /**
     * 标题
     */
    private static TextView title;
    /**
     * 标题栏
     */
    private static LinearLayout titleLL;


    /**
     * 设置沉浸式状态栏随布局滑动改变颜色
     *
     * @param scrollView 滑动布局
     * @param changeView 被改变view
     * @param fakeView   假状态栏
     */
    public static void setImmersion(final Window window, final Context context, MyScrollView scrollView, final View changeView, final View fakeView) {
        if (changeView.getVisibility() == View.VISIBLE) {
            changeView.setVisibility(View.GONE);
        }
        setFakeView(fakeView, context);
        fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        changeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        scrollView.setScrollViewListener(new MyScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    changeView.setVisibility(View.GONE);
                    changeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    darkMode(window, false);
                } else if (y > 0 && y <= 100) {
                    float scale = (float) y / 100;
                    float alpha = (255 * scale);
                    changeView.setVisibility(View.VISIBLE);
                    fakeView.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    changeView.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    darkMode(window, false);
                } else {
                    fakeView.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    changeView.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    darkMode(window, true);
                }
            }

            @Override
            public void onScrollStop(boolean isScrollStop) {

            }
        });
    }

    /**
     * 设置标题栏
     *
     * @param activity
     * @param view
     * @param titleName
     */
    public static void setTitleBar(final Activity activity, View view, String titleName) {
        backCircle = view.findViewById(R.id.title_bar_back_circle);
        back = view.findViewById(R.id.title_bar_back);
        title = view.findViewById(R.id.title_bar_title_tv);
        titleLL = view.findViewById(R.id.title_bar_title_ll);
        title.setText(titleName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        backCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * 设置沉浸式状态栏随布局滑动改变颜色
     *
     * @param scrollView 滑动布局
     * @param changeView 被改变view
     * @param fakeView   假状态栏
     */
    public static void setImmersionCanBack(final Window window, final Context context, MyScrollView scrollView, final View changeView, final View fakeView) {
        if (titleLL.getVisibility() == View.VISIBLE) {
            titleLL.setVisibility(View.GONE);
        }
        setFakeView(fakeView, context);
        fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        changeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        scrollView.setScrollViewListener(new MyScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    titleLL.setVisibility(View.GONE);
                    backCircle.setVisibility(View.VISIBLE);
                    titleLL.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    darkMode(window, false);
                } else if (y > 0 && y <= 100) {
                    float scale = (float) y / 100;
                    float alpha = (255 * scale);
                    titleLL.setVisibility(View.VISIBLE);
                    backCircle.setVisibility(View.GONE);
                    back.setEnabled(true);
                    fakeView.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    titleLL.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    darkMode(window, false);
                } else {
                    fakeView.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    titleLL.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    darkMode(window, true);
                }
            }

            @Override
            public void onScrollStop(boolean isScrollStop) {

            }
        });
    }

    /**
     * 设置沉浸式状态栏随布局滑动改变颜色
     *
     * @param scrollView 滑动布局
     * @param changeView 被改变view
     * @param fakeView   假状态栏
     */
    public static void setImmersionCanBack(final Window window, final Context context, NestedScrollView scrollView, final View changeView, final View fakeView) {
        if (titleLL.getVisibility() == View.VISIBLE) {
            titleLL.setVisibility(View.GONE);
        }
        setFakeView(fakeView, context);
        fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        changeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {
                    LogUtils.e("ImmersionUtils","<=0");
                    fakeView.setBackgroundColor(Color.argb(0, 255, 255, 255));
//                    titleLL.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    if (backCircle.getVisibility()==View.GONE){
                        LogUtils.e("ImmersionUtils","backCircle.GONE");
                        backCircle.setVisibility(View.VISIBLE);
                    }
                    if (titleLL.getVisibility()==View.VISIBLE){
                        LogUtils.e("ImmersionUtils","titleLL.VISIBLE");
                        titleLL.setVisibility(View.GONE);
                    }
                    darkMode(window, false);
                } else if (scrollY > 0 && scrollY <= 100) {
                    LogUtils.e("ImmersionUtils",">0");
                    float scale = (float) scrollY / 100;
                    float alpha = (255 * scale);
                    titleLL.setVisibility(View.VISIBLE);
                    backCircle.setVisibility(View.GONE);
                    back.setEnabled(true);
                    fakeView.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    titleLL.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    darkMode(window, false);
                } else {
                    LogUtils.e("ImmersionUtils","else");
                    if (backCircle.getVisibility()==View.VISIBLE){
                        backCircle.setVisibility(View.GONE);
                    }
                    if (titleLL.getVisibility()==View.GONE){
                        titleLL.setVisibility(View.VISIBLE);
                    }
                    fakeView.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    titleLL.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    darkMode(window, true);
                }
            }
        });
    }


    /**
     * 设置沉浸式状态栏
     *
     * @param fakeView 假状态栏
     */
    public static void setListImmersion(final Window window, final Context context, final View fakeView) {
        setFakeView(fakeView, context);
        fakeView.setBackgroundColor(Color.argb(255, 255, 255, 255));
        darkMode(window, true);
    }


    /**
     * 设置假状态栏高度
     *
     * @param view
     * @param context
     */
    public static void setFakeView(View view, Context context) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = ScreenUtils.getStatusHeight(context);
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    public static void darkMode(Window window, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            darkModeForM(window, dark);
        } else if (isFlyme4Later()) {
            darkModeForFlyme4(window, dark);
        } else if (isMIUI6Later()) {
            darkModeForMIUI6(window, dark);
        }
    }

    /**
     * android 6.0设置字体颜色
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private static void darkModeForM(Window window, boolean dark) {

        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (dark) {
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /**
     * 设置Flyme4+的darkMode,darkMode时候字体颜色及icon变黑
     * http://open-wiki.flyme.cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
     */
    public static boolean darkModeForFlyme4(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams e = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(e);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }

                meizuFlags.setInt(e, value);
                window.setAttributes(e);
                result = true;
            } catch (Exception var8) {
                Log.e("StatusBar", "darkIcon: failed");
            }
        }

        return result;
    }

    /**
     * 设置MIUI6+的状态栏是否为darkMode,darkMode时候字体颜色及icon变黑
     * http://dev.xiaomi.com/doc/p=4769/
     */
    public static boolean darkModeForMIUI6(Window window, boolean darkmode) {
        Class<? extends Window> clazz = window.getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否Flyme4以上
     */
    public static boolean isFlyme4Later() {
        return Build.FINGERPRINT.contains("Flyme_OS_4")
                || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find();
    }

    /**
     * 判断是否为MIUI6以上
     */
    public static boolean isMIUI6Later() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            return false;
        }
    }


}
