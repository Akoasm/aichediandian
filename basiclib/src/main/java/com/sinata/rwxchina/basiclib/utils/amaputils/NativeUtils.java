package com.sinata.rwxchina.basiclib.utils.amaputils;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.sinata.rwxchina.basiclib.base.BaseActivity;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author:zy
 * @detetime:2018/1/9
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class NativeUtils {

    public static void invokingBD(BaseActivity baseActivity,String address,String city){

        //  com.baidu.BaiduMap这是高德地图的包名
        //调起百度地图客户端try {
        Intent intent = null;
        try {
            String uri = "intent://map/direction?origin=latlng:0,0|name:我的位置&destination=" + address + "&mode=drivingion=" + city + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
            intent = Intent.getIntent(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(isInstallByread("com.baidu.BaiduMap")){
            baseActivity.startActivity(intent); //启动调用
            Log.e("GasStation", "百度地图客户端已经安装") ;
        }else{
            Toast.makeText(baseActivity, "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    public static void invokingGD(BaseActivity baseActivity,String address){

        //  com.autonavi.minimap这是高德地图的包名
        Intent intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://navi?sourceApplication=aichediandian&lat=&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse("androidamap://poi?sourceApplication=aichediandian&keywords="+address));
        intent.addCategory("android.intent.category.DEFAULT");
        if(isInstallByread("com.autonavi.minimap")){
            baseActivity.startActivity(intent);
        }else{
            Toast.makeText(baseActivity, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
        }
    }
    public static void invokingGD(BaseActivity baseActivity,String lat,String lng){
        Intent intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://viewReGeo?sourceApplication=aichediandian&lat="+lat+"&lon="+lng+"&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        intent.addCategory("android.intent.category.DEFAULT");
        if(isInstallByread("com.autonavi.minimap")){
            baseActivity.startActivity(intent);
        }else{
            Toast.makeText(baseActivity, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 判断是否安装目标应用
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private static boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
