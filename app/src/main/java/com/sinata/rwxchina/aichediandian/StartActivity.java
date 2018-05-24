package com.sinata.rwxchina.aichediandian;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.AMapLocationUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import com.tbruyelle.rxpermissions.Permission;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends BaseActivity {
    private TimerTask task;
    private String imgPath;
    private ImageView background;
    private TextView pass;
    private String images;
    private TimeCount time;
    private Thread thread;
    private List<StartImgEntity> list;


    private void IsFirst() {
        SharedPreferences sp = getSharedPreferences("Isfirst",MODE_PRIVATE);
        Boolean isOpen = sp.getBoolean("IsFirstGon",false);
        if (isOpen){
//            Timer timer = new Timer();
//            task = new TimerTask() {
//                @Override
//                public void run() {
//
//                }
//            };
//            // 延迟2秒自动执行
//            timer.schedule(task, 1000);
            setimg();
        }else {
            SharedPreferences.Editor editor = sp.edit();
            //如果第一次打开app就保存
            editor.putBoolean("IsFirstGon",true);
            editor.commit();
            gotoGuidepage();
        }
    }


    //跳转到主页面
    private void gotoMain() {
        final Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        LogUtils.e("StartActivity","跳转到主页");
        time.cancel();
        finish();

    }

    //跳转到引导页面
    private void gotoGuidepage(){
        final Intent intent = new Intent(getApplicationContext(),GuidepageActivity.class);
//        Timer timer = new Timer();
        startActivity(intent);
        if (time!=null){
            time.cancel();
        }
        finish();
//        task = new TimerTask() {
//
//            @Override
//            public void run() {
//
//            }
//        };
//        // 延迟1秒自动执行
//        timer.schedule(task, 1000);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if(thread!=null){
            thread.interrupt();
            thread=null;
        }

    }

    @Override
    public void initParms(Bundle params) {
//        applyPermisson();
        downImg();
        location();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

        background =  findViewById(R.id.activity_start_img);
        pass =  findViewById(R.id.pass_tv);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMain();
            }
        });

        time = new TimeCount(5000,1000);
        pass.setVisibility(View.VISIBLE);

        //下载广告页图片
//        IsFirst();
    }

    @Override
    public void setListener() {
//        pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoMain();
//            }
//        });
    }

    @Override
    public void widgetClick(View v) {
    }
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 100:
//                    if (imgPath != null){
//                        Glide.with(getApplicationContext()).load(HttpPath.IMAGE_URL+imgPath).error(R.drawable.advert).into(background);
//                    }else {
//                        background.setBackgroundResource(R.drawable.advert);
//                    }
//                    pass.setVisibility(View.VISIBLE);
//                    time.start();
//                    break;
//            }
//        }
//    };


    @Override
    public void doBusiness(Context mContext) {
        time.start();
    }
    private void setimg(){
        ImageUtils.showImage(this,HttpPath.IMAGEURL+list.get(0).getUrlsmall(),background);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        // 计时过程
        @Override
        public void onTick(long millisUntilFinished) {
            pass.setText("跳过"+millisUntilFinished / 1000 + "s");

        }


        //计时完毕
        @Override
        public void onFinish() {
            pass.setText("跳过");
            gotoMain();
        }
    }

    public void location(){
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
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private void downImg(){
        Map<String, String> params = new HashMap<String, String>();
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                list = gson.fromJson(result,new TypeToken<List<StartImgEntity>>() {}.getType());
//                setimg();
                IsFirst();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.START_IMG_PATH,params,false);
    }

    /**
     * 权限申请
     */
    private void applyPermisson(){
        PermissionUtils.permissonMoreAll(this,Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }
}
