package com.sinata.rwxchina.basiclib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author HRR
 * @datetime 2017/12/6
 * @describe BaseGridView,结局滑动冲突
 * @modifyRecord
 */

public class BaseGridView extends GridView {
    public BaseGridView(Context context) {
        super(context);
    }

    public BaseGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO 自动生成的方法存根
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
