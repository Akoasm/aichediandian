package com.sinata.rwxchina.component_basic.hotel.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.view.datepicker.DatePickerController;
import com.sinata.rwxchina.basiclib.view.datepicker.DayPickerView;
import com.sinata.rwxchina.basiclib.view.datepicker.SimpleMonthAdapter;
import com.sinata.rwxchina.component_basic.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 酒店入住离开日历选择工具类
 * @modifyRecord
 */

public class DatePickerUtils {
    private static int days, year, month, day;
    /**入住日期和离开日期*/
    private static Date checkDate,leaveDate;
    static CallDate callDate;

    public static void setDatePicker(final Context context,View showView){
        callDate= (CallDate) context;
        //布局
        View pickerView= LayoutInflater.from(context).inflate(R.layout.pop_datepicker,null);
        //设置弹出框
        final PopupWindow popDatePicker=new PopupWindow();
        popDatePicker.setHeight(ScreenUtils.getScreenWidth(context));
        popDatePicker.setWidth(ScreenUtils.getScreenWidth(context));
        popDatePicker.setContentView(pickerView);
        //将这两个属性设置为false，使点击popupwindow外面其他地方会消失
        popDatePicker.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popDatePicker.setOutsideTouchable(true);
        popDatePicker.setFocusable(true);
        DayPickerView dayPickerView = pickerView.findViewById(R.id.dpv_calendar);
        ImageView rili_quexiao = pickerView.findViewById(R.id.rili_quexiao);
        TextView pop_time_sure =  pickerView.findViewById(R.id.pop_time_sure);
        //当前时间
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        //初始化日历
        DayPickerView.DataModel dataModel = new DayPickerView.DataModel();
        dataModel.yearStart = year;
        dataModel.monthStart = month;
        dataModel.monthCount = 12;
        dataModel.defTag = "";
        dataModel.leastDaysNum = 1;
        dataModel.mostDaysNum = 50;
        dayPickerView.setParameter(dataModel, new DatePickerController() {
            @Override
            public void onDayOfMonthSelected(SimpleMonthAdapter.CalendarDay calendarDay) {
            }

            @Override
            public void onDateRangeSelected(List<SimpleMonthAdapter.CalendarDay> selectedDays) {
                days = selectedDays.size() - 1;

                checkDate=selectedDays.get(0).getDate();
                leaveDate=selectedDays.get(selectedDays.size()-1).getDate();
                callDate.getCallDate(checkDate,leaveDate);
            }

            @Override
            public void alertSelectedFail(FailEven even) {
                Toast.makeText(context, "最长订房时间为50天", Toast.LENGTH_SHORT).show();
            }
        });
        rili_quexiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popDatePicker.isShowing()){
                    popDatePicker.dismiss();
                }
            }
        });
        pop_time_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popDatePicker.isShowing()){
                    popDatePicker.dismiss();
                }
            }
        });
        popDatePicker.showAtLocation(showView, Gravity.BOTTOM, 0, 0);
    }
    public interface CallDate{
        void getCallDate(Date checkDate,Date leaveDate);
    }
}
