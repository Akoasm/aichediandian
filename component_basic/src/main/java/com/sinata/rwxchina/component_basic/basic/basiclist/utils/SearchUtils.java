package com.sinata.rwxchina.component_basic.basic.basiclist.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.EventEnity;

import org.greenrobot.eventbus.EventBus;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 搜索工具类
 * @modifyRecord
 */

public class SearchUtils {
    /**
     * 设置搜索框
     * @param context
     * @param mTopLine
     */
    public static void setSearch(Context context, View mTopLine){
        View view= LayoutInflater.from(context).inflate(R.layout.pop_search,null);
        final EditText editText=view.findViewById(R.id.pop_search_edit);
        final ImageView imageView = view.findViewById(R.id.pop_search_image);
        //设置popupwindow
        final PopupWindow searchePop=new PopupWindow(context);
        searchePop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        searchePop.setWidth(ScreenUtils.getScreenWidth(context));
        searchePop.setContentView(view);
        //将这两个属性设置为false，使点击popupwindow外面其他地方会消失
        searchePop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        searchePop.setOutsideTouchable(true);
        searchePop.setFocusable(true);
        searchePop.showAsDropDown(mTopLine, 0, 0);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    EventEnity enity=new EventEnity();
                    enity.setType("search");
                    enity.setContent(editText.getText().toString());
                    EventBus.getDefault().post(enity);
                    if (searchePop.isShowing()){
                        searchePop.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventEnity enity=new EventEnity();
                enity.setType("search");
                enity.setContent(editText.getText().toString());
                EventBus.getDefault().post(enity);
                if (searchePop.isShowing()){
                    searchePop.dismiss();
                }
            }
        });
    }
}
