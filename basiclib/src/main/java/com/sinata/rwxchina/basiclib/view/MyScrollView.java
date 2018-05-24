package com.sinata.rwxchina.basiclib.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by longkeyou on 2016/12/16.
 */
public class MyScrollView extends ScrollView {
    private OnScrollChangeListener scrollViewListener = null;
    private int handlerWhatId = 65984;
    private int timeInterval = 20;
    private int lastY = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == handlerWhatId) {
                if (lastY == getScrollY()) {
                    if (scrollViewListener != null) {
                        scrollViewListener.onScrollStop(true);
                    }
                } else {
                    if (scrollViewListener != null) {
                        scrollViewListener.onScrollStop(false);
                    }
                    handler.sendMessageDelayed(handler.obtainMessage(handlerWhatId, this), timeInterval);
                    lastY = getScrollY();
                }
            }
        }
    };
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollViewListener(OnScrollChangeListener onScrollChangeListener) {
        this.scrollViewListener = onScrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface OnScrollChangeListener {

        void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);
        void onScrollStop(boolean isScrollStop);
    }

}
