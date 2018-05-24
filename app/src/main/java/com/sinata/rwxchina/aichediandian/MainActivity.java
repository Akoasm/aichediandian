package com.sinata.rwxchina.aichediandian;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.amaputils.AMapLocationUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.appupdateutils.AppUpdateUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.fragment.AboutMeFragment;
import com.sinata.rwxchina.component_entertainment.fragment.EntertainmentFragment;
import com.sinata.rwxchina.component_find.Fragment.FindFragment;
import com.sinata.rwxchina.component_home.fragment.HomeFragment;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import com.tbruyelle.rxpermissions.Permission;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by HRR on 2017/10/17.
 * 主activity
 */
public class MainActivity extends BaseActivity {
    // 获取手机制造厂商
    private String carrier = android.os.Build.MANUFACTURER;
    private Fragment[] mFragments;
    private int mIndex;
    private RadioButton home, entertainment, find, aboutme;
    private long exitTime = 0;
    /**首页模块*/
    private HomeFragment homeFragment = new HomeFragment();
    /**娱乐模块*/
    private EntertainmentFragment entertainmentFragment = new EntertainmentFragment();
    /**发现模块*/
    private FindFragment findFragment = new FindFragment();
    /**我的模块*/
    private AboutMeFragment aboutMeFragment = new AboutMeFragment();
    @Override
    public void initParms(Bundle parms) {
//        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        home = findViewById(R.id.activity_main_home);
        entertainment = findViewById(R.id.activity_main_entertainment);
        find = findViewById(R.id.activity_main_find);
        aboutme = findViewById(R.id.activity_main_aboutme);
        LogUtils.e("主页");
    }

    @Override
    public void setListener() {
        home.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        find.setOnClickListener(this);
        aboutme.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_home:
                setIndexSelected(0);
                break;
            case R.id.activity_main_entertainment:
                setIndexSelected(1);
                break;
            case R.id.activity_main_find:
                setIndexSelected(2);
                break;
            case R.id.activity_main_aboutme:
                setIndexSelected(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        mFragments = new Fragment[]{homeFragment, entertainmentFragment, findFragment, aboutMeFragment};
        //开启事务
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.activity_main_content, homeFragment).commit();
        //默认设置为第0个
        setIndexSelected(0);
        home.setChecked(true);
//        location();
        applyPermisson();
        update();
//        test();
//        ApiManager  retrofit=new ApiManager(this,stringCallBack);
//        Map<String ,String> params=new HashMap<>();
//        params.put("phone","18758888888");
//        params.put("verify","666888");
//        retrofit.get(HttpPath.LOGINPHONE,params);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.activity_main_content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 连按两次退出
     */
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this, null);
    }

    /**
     * 权限申请
     */
    private void applyPermisson(){
        PermissionUtils.permissonMoreAll(this,Manifest.permission.CAMERA);
    }

    private void update(){
        AppUpdateUtils appUpdateUtils=new AppUpdateUtils(this);
        appUpdateUtils.ifUpdate(HttpPath.APP_UPDATE);
    }

    private void location(){
        PermissionUtils.permissonMoreAll(this, new PermissionCall() {
            @Override
            public void success() {
                AMapLocationUtils.startLocation(getApplicationContext(), new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation amapLocation) {
                        if (amapLocation != null) {
                            if (amapLocation.getErrorCode() == 0) {
                                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                                //保存纬度
                                LocationUtils.saveLat(getApplicationContext(), String.valueOf(amapLocation.getLatitude()));
                                //保存经度
                                LocationUtils.saveLng(getApplicationContext(), String.valueOf(amapLocation.getLongitude()));
                                //保存城市信息
                                LocationUtils.saveCity(getApplicationContext(),amapLocation.getCity());
                                amapLocation.getAccuracy();//获取精度信息
                                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                                amapLocation.getCountry();//国家信息
                                amapLocation.getProvince();//省信息
                                amapLocation.getDistrict();//城区信息
                                amapLocation.getStreet();//街道信息
                                amapLocation.getStreetNum();//街道门牌号信息
                                amapLocation.getCityCode();//城市编码
                                amapLocation.getAdCode();//地区编码
                                amapLocation.getAoiName();//获取当前定位点的AOI信息
                                amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                                amapLocation.getFloor();//获取当前室内定位的楼层
                                amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                                LocationUtils.saveAddress(getApplicationContext(),amapLocation.getAddress());
                                //获取定位时间
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(amapLocation.getTime());
                                df.format(date);
                                //可在其中解析amapLocation获取相应内容。
                            } else {
                                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                                showLog("location Error, ErrCode:"
                                        + amapLocation.getErrorCode() + ", errInfo:"
                                        + amapLocation.getErrorInfo());
                            }
                        }
                    }
                });
            }

            @Override
            public void fail() {
                ToastUtils.showShort("缺少定位权限");
            }

            @Override
            public void next(Permission permission) {

            }
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.REQUEST_INSTALL_PACKAGES);
    }



}
