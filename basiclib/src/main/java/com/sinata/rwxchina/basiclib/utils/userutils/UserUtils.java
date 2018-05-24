package com.sinata.rwxchina.basiclib.utils.userutils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;

/**
 * @author HRR
 * @datetime 2018/1/17
 * @describe 用户工具类
 * @modifyRecord
 */

public class UserUtils {

    /**
     * 如果用户没有登录，则跳转到登录页面
     * @param context
     */
    public static boolean isLogin(Context context){
        if (TextUtils.isEmpty(CommonParametersUtils.getUid(context))){
            ToastUtils.showShort("请登录后继续操作");
            try {
                Class cla=Class.forName("com.sinata.rwxchina.component_login.activity.LoginPhoneActivity");
                Intent intent=new Intent(context,cla);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (ClassNotFoundException e) {
                LogUtils.e("UserUtils","跳转登录异常="+e.toString());
                e.printStackTrace();
            }
            return false;
        }else {
            return true;
        }
    }
}
