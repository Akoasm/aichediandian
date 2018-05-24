package com.sinata.rwxchina.basiclib.utils.sharedutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author HRR
 * @datetime 2017/11/13
 * @describe 用户共享参数工具类
 * @modifyRecord
 */

public class SharedUserUtils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    /**用户共享参数文件*/
    private static final String SHARENAME="sharedUser";

    /**
     * 获取登录手机号
     * @param context
     * @return
     */
    public static String getLoginPhone(Context context){
        preferences =context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("loginPhone","");
    }

    /**
     * 保存登录手机号
     * @param context
     */
    public static void saveLoginPhone(Context context,String loginPhone){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("loginPhone",loginPhone);
        editor.commit();
    }
}
