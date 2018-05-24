package com.sinata.rwxchina.component_home.utils;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.CheckLimitEntity;
import com.sinata.rwxchina.component_home.entity.CityChooseEntity;
import com.sinata.rwxchina.component_home.entity.HotSearchEntity;
import com.sinata.rwxchina.component_home.entity.LimitLineEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/28
 * @describe：
 * @modifyRecord:
 */


public class CityUtils {


    /**
     * 设置城市
     * @param view
     * @param activity
     * @param city
     */
    public static void setCity(final View view,final BaseActivity activity,final String city) {
        Map<String, String> params = new HashMap<>();
        params.put("cityname", city);
        ApiManager apiManager = new ApiManager(activity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("sss_city",result);
                Gson gson = new Gson();
                CityChooseEntity cityChooseEntity = gson.fromJson(result, CityChooseEntity.class);
                 TextView location_tv = view.findViewById(R.id.home_location_tv);
                if (cityChooseEntity != null) {
                    location_tv.setText(cityChooseEntity.getName());
                }
            }
            @Override
            public void onResultError(ApiException e, String method) {
            }
        });
        apiManager.post(HttpPath.CITYNAME, params);
    }


    public static void setLimit(final View view,final BaseActivity activity,final String PinyinCity){
        Map<String, String> params = new HashMap<>();
        params.put("city",PinyinCity);
        ApiManager apiManager = new ApiManager(activity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                LimitLineEntity limitLineEntity = gson.fromJson(result,LimitLineEntity.class);
                TextView limitline_tv = view.findViewById(R.id.fragment_home_restriction);
                String week = limitLineEntity.getWeek();
                if(week.contains("星期六")|| week.contains("星期天")){
                    limitline_tv.setText("今日"+limitLineEntity.getNumber());
                }else{
                    limitline_tv.setText("今日限行"+limitLineEntity.getNumber());
                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.LIMITLINE,params);
    }

}
