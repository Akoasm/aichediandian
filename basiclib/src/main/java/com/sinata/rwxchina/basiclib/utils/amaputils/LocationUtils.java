package com.sinata.rwxchina.basiclib.utils.amaputils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author HRR
 * @datetime 2017/12/11
 * @describe 定位工具类
 * @modifyRecord
 */

public class LocationUtils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    /**文件名*/
    public static final String SHARENAME="locationUtils";

    /**
     * 保存当前位置的经度
     */
    public static void saveLng(Context context,String lng){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("lng",lng);
        editor.commit();
    }

    /**
     * 获取当前位置的经度
     * @return
     */
    public static String getLng(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("lng","");
    }

    /**
     * 保存当前位置的纬度
     * @param context
     * @param lat
     */
    public static void saveLat(Context context,String lat){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("lat",lat);
        editor.commit();
    }

    /**
     * 获取当前位置的纬度
     * @param context
     * @return
     */
    public static String getLat(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("lat","");
    }
    /**
     * 保存当前位置的城市名
     * @param context
     * @param city
     */
    public static void saveCity(Context context,String city){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("city",city);
        editor.commit();
    }
    /**
     * 获取当前位置的城市名
     * @param context
     * @return
     */
    public static String getCity(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("city","");
    }

    public static void saveAddress(Context context,String address){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("address",address);
        editor.commit();
    }

    public static String getAddress(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("address","");
    }

    public static void saveLocationType(Context context,String locationType){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("locationType",locationType);
        editor.commit();
    }

    public static String getLocationType(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("locationType","");
    }


}
