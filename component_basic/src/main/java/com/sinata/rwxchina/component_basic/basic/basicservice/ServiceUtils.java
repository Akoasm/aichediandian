package com.sinata.rwxchina.component_basic.basic.basicservice;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sinata.rwxchina.component_basic.R;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类服务工具类
 * @modifyRecord
 */

public class ServiceUtils {

    /**
     * 设置更多服务
     * @param view
     * @param content
     */
    public static void setService(View view,String content){
        TextView textView=view.findViewById(R.id.health_detail);
        if (TextUtils.isEmpty(content)){
            view.setVisibility(View.GONE);
            return;
        }
        textView.setText(content);
    }
}
