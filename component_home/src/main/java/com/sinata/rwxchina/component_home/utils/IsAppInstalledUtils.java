package com.sinata.rwxchina.component_home.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/3/26.
 */

public class IsAppInstalledUtils {
    //判断手机内是否有此APP
    public static boolean isAppInstalled(Context context) {
        PackageManager pm;
        if ((pm = context.getApplicationContext().getPackageManager()) == null) {
            return false;
        }
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo info : packages) {
            String name = info.packageName.toLowerCase(Locale.ENGLISH);
            if ("com.qf.rwxchina.xiaochefu".equals(name)) { //应用包名 eg:com.sina.weibo
                return true;
            }
        }
        return false;
    }
}
