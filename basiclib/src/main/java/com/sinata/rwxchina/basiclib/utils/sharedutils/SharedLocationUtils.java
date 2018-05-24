package com.sinata.rwxchina.basiclib.utils.sharedutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author HRR
 * @datetime 2017/11/13
 * @describe 定位共享参数工具类
 * @modifyRecord
 */

public class SharedLocationUtils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    /**定位共享参数文件*/
    private static final String SHARENAME="sharedLocation";


    /**
     * 获取定位纬度
     * @param context
     * @return
     */
    public static String getLatitude(Context context){
        preferences =context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("latitude","");
    }

    /**
     * 保存定位纬度
     * @param context
     * @param latitude 纬度
     */
    public static void saveUid(Context context,String latitude){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("latitude",latitude);
        editor.commit();
    }

    /**
     * 获取定位经度
     * @param context
     * @return
     */
    public static String getLongitude(Context context){
        preferences =context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("longitude","");
    }

    /**
     * 保存定位经度
     * @param context
     * @param longitude 经度
     */
    public static void saveLongitude(Context context,String longitude){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("longitude",longitude);
        editor.commit();
    }

    /**
     * 获取定位城市名称
     * @param context
     * @return
     */
    public static String getCityName(Context context){
        preferences =context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("cityName","");
    }

    /**
     * 保存定位城市名称
     * @param context
     * @param cityName 城市名称
     */
    public static void saveCityName(Context context,String cityName){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("cityName",cityName);
        editor.commit();
    }
}
