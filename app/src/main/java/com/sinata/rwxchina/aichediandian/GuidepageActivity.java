package com.sinata.rwxchina.aichediandian;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityOrderCancelOrCompleteActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.AMapLocationUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.tbruyelle.rxpermissions.Permission;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * app引导页
 */
public class GuidepageActivity extends AppCompatActivity implements View.OnClickListener {
    //就是我们装载图片的容器viewpager
    private ViewPager mViewPager;
    //用来装载右下角的小点
    private LinearLayout mPoint;
    //跳过引导页
    private Button mSkip;
    //进入主页
    private ImageView mIntoMain;

    //图片地址
//    private ArrayList<String> mImgPath = new ArrayList<>();
    //图片资源ID
    private int[] imgIdArray;
    //用于暂停“自动滑动”
    private boolean isContinue;
    //就是我们需要的图片数组
    private ImageView imageviews[];
    //右下角的几个点
    private View dots[];
    // 当前图片所在的位置
    private int currentImgIndex;
    Thread thread;
    Thread thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
       steepStatusBar();
        init();
    }

    /**
     * handler是用来处理自动滑动的关键
     */
    private Handler adHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(msg.arg1);
        }
    };

    //初始化
    private void init() {
        location();
        findView();
        getInfo();
        viewpager_go();
    }

    //获取图片资源数据
    private void getInfo() {
        imgIdArray = new int[]{R.mipmap.guidepageone, R.mipmap.guidepagetwo, R.mipmap.guidepagethree};
    }

    //设置监听
    private void setOnClick() {

    }

    //找到控件
    private void findView() {
        mViewPager = (ViewPager) findViewById(R.id.activity_guidepage_viewPager);
        mPoint = (LinearLayout) findViewById(R.id.activity_guidepage_point);
        mSkip = (Button) findViewById(R.id.activity_guidepage_skip);
        mIntoMain = (ImageView) findViewById(R.id.activity_guidepage_intomain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_guidepage_skip:
                gotoMain();
                break;
            case R.id.activity_guidepage_intomain:
                gotoMain();
                break;
        }
    }

    //进入主页面
    private void gotoMain() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void viewpager_go() {
        // 最开始的时候自动滑动默认开始
        isContinue = false;
        // 图片数组，根据图片ID数量确定
        imageviews = new ImageView[imgIdArray.length];

        thread = new Thread(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                for (int i = 0; i < imageviews.length; i++) {
                    ImageView tempImg = new ImageView(getApplicationContext());
                    imageviews[i] = tempImg;
                    tempImg.setBackgroundResource(imgIdArray[i]);
                }
                handler.sendEmptyMessage(100);
                Message message = Message.obtain();
                message.arg1 = currentImgIndex;
                adHandler.sendMessage(message);
            }
        });
        thread.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    setOther();
                    break;
            }
        }
    };

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
                                mSkip.setOnClickListener(GuidepageActivity.this);
                                mIntoMain.setOnClickListener(GuidepageActivity.this);
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
                                LogUtils.e("location Error, ErrCode:"
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
    private void setOther() {
        // 右下角的点，同样是根据图片数量确定
        dots = new View[imageviews.length];
        // 将右下角的点加入右下角的布局中
        for (int i = 0; i < imageviews.length; i++) {
            View dot = new View(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(20, 5, 5, 5);

            dot.setLayoutParams(params);

            if (i == 0) {
                dot.setBackgroundResource(R.drawable.dot_focused);
            } else {
                dot.setBackgroundResource(R.drawable.dot_normal);
            }
            dots[i] = dot;
            mPoint.addView(dot);
        }

        mViewPager.setAdapter(new MyPagerAdapter());
        // 设置当前图片位置在长度的100倍
        mViewPager.setCurrentItem(100 * imageviews.length);
        currentImgIndex = 100 * imageviews.length;
        // 这个监听器主要用于改变下角点的切换
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentImgIndex = position;
                for (int i = 0; i < imageviews.length; i++) {
                    if (i == currentImgIndex % imageviews.length) {
                        dots[i].setBackgroundResource(R.drawable.dot_focused);
                    } else {
                        dots[i].setBackgroundResource(R.drawable.dot_normal);
                    }
                }
                if (position == (imgIdArray.length - 1)) {
                    mIntoMain.setVisibility(View.VISIBLE);
                } else {
                    mIntoMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        // 这个监听器用于监听手指触摸，是为了暂停“自动滑动”
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = false;
                        break;
                    default:
                        isContinue = false;
                        break;
                }
                return false;
            }
        });

        // 使用message不停的发送消息实现自动滑动
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        Message message = Message.obtain();
                        message.arg1 = currentImgIndex;
                        adHandler.sendMessage(message);
                        sleep();
                    }
                }
            }
        });
        thread2.start();
    }

    /**
     * 每隔一秒发送一次消息
     */
    private void sleep() {
        currentImgIndex = currentImgIndex + 1;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgIdArray.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(imageviews[position % imageviews.length]);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager) container).addView(imageviews[position % imageviews.length], 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return imageviews[position % imageviews.length];
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        if (thread2 != null) {
            thread2.interrupt();
            thread2 = null;
        }
    }
    public void steepStatusBar() {
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(GuidepageActivity.this, null);
    }
}
