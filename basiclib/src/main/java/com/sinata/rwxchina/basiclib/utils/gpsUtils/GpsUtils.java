package com.sinata.rwxchina.basiclib.utils.gpsUtils;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

/**
 * Created by HRR on 2017/10/11.
 * Gps工具类
 *
 */

public class GpsUtils {

    /**
     * 判断GPS是否开启
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.e("GpsUtils","gps="+gps);
        if (gps) {
            return true;
        }
        return false;
    }
}
