package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.sinata.rwxchina.basiclib.R;

import java.util.Calendar;
import java.util.Date;

/**
 * @author HRR
 * @datetime 2018/1/8
 * @describe 订单选择到店时间工具类
 * @modifyRecord
 */

public class PayComeTimeUtils {

    public static void setComeTime(Context context,View view){
        final CallComeTime call= (CallComeTime) context;
        LinearLayout linearLayout=view.findViewById(R.id.payment_arrival_time_linear);
        final TextView time=view.findViewById(R.id.payment_arrival_time);
        //时间选择器
        final TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                time.setText((1900+date.getYear())+"年"+(1+date.getMonth())+"月"+date.getDate()+"日"+date.getHours()+"时");
                call.callComeTime(date);
            }
        }).setType(new boolean[]{true, true, true, true, false, false}).isCenterLabel(false) .build();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    public interface CallComeTime{
        void callComeTime(Date date);
    }
}
