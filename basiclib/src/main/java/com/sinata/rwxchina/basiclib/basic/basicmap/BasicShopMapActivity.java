package com.sinata.rwxchina.basiclib.basic.basicmap;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.NativeUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.view.ActionSheetDialog;

import java.io.File;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 商铺地图页面
 * @modifyRecord
 */

public class BasicShopMapActivity extends BaseActivity {
    private BaseShopInfo shopInfo;
    /**地图*/
    private MapView mMapView;
    /**初始化地图控制器对象*/
    private AMap aMap;
    /**标题*/
    private ImageView back;
    private TextView title;
    private View statusBar;
    /**店铺信息*/
    private ImageView logo_img;
    private TextView name_tv,address_tv;
    private LinearLayout goMap;

    private Boolean isInsurance;
    private String lat;
    private String lng;
    private String address;
    private String name;
    private String logo;
    @Override
    public void initParms(Bundle params) {
        isInsurance = params.getBoolean("isInsurance",false);
        LogUtils.e("isInsurance"+isInsurance);
        if(isInsurance){
            lat = params.getString("lat");
            lng = params.getString("lng");
            address = params.getString("address");
            name = params.getString("name");
            logo = params.getString("logo");
        }else {
            String shop=params.getString("shopInfo");
            if (!TextUtils.isEmpty(shop)){
                shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
                lat = shopInfo.getShop_lat();
                lng = shopInfo.getShop_lng();
                address = shopInfo.getShop_address();
                name = shopInfo.getShop_name();
                logo = shopInfo.getShop_logo();
            }
        }

        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_shopmap;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mMapView=findViewById(R.id.shop_map);
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("地图");
        logo_img=findViewById(R.id.shopMap_logo);
        name_tv=findViewById(R.id.shopMap_shopName);
        address_tv=findViewById(R.id.shopMap_shopAddress);
        goMap=findViewById(R.id.shopMap_go);
        mMapView.onCreate(savedInstanceState);
        if (aMap==null){
            aMap=mMapView.getMap();
        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        goMap.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.food_comment_back){
            finish();
        }else if (id==R.id.shopMap_go){
            goMap();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setShop();
        setBlueLocation();
        setShopMap();
        setTitleBarView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 设置定位小蓝点
     */
    private void setBlueLocation(){
        //初始化定位蓝点样式类
        MyLocationStyle myLocationStyle=new MyLocationStyle();
        //定位一次，且将视角移到地图中心点
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

    }

    /**
     * 设置店铺定位
     */
    private void setShopMap(){
        //绘制marker
         aMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(lat),Double.parseDouble(lng)))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),R.mipmap.marker_unselect)))
                .draggable(true));

    }

    /**
     * 设置店铺信息
     */
    private void setShop(){
        ImageUtils.showImage(this, HttpPath.IMAGEURL+logo,logo_img);
        name_tv.setText(name);
        address_tv.setText(address);
    }

    /**
     * 打开地图
     */
    private void goMap(){
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("百度地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                openBaiduMap();
                            }
                        })
                .addSheetItem("高德地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                openGaodeMap();
                            }
                        }).show();
    }

    /**
     * 跳转到百度地图
     */
    private void openBaiduMap(){
        NativeUtils.invokingBD(this, address, LocationUtils.getCity(this));
//        // 将google地图、soso地图、aliyun地图、mapabc地图和amap地图// 所用坐标转换成百度坐标
//        CoordinateConverter converter  = new CoordinateConverter();
//        converter.from(CoordinateConverter.CoordType.COMMON);
//        // sourceLatLng待转换坐标
//        converter.coord(sourceLatLng);
//        com.baidu.mapapi.model.LatLng desLatLng = converter.convert();
//        // 构建 导航参数
//        NaviParaOption para = new NaviParaOption()
//                .startPoint(pt1).endPoint(pt2)
//                .startName("天安门").endName("百度大厦");
//        BaiduMapNavigation.openBaiduMapNavi(para, this);
    }

    /**
     * 跳转到高德地图
     */
    private void openGaodeMap(){
        NativeUtils.invokingGD(BasicShopMapActivity.this,lat,lng);
    }

    /**
     * 判断是否安装目标应用
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setListImmersion(getWindow(),this,statusBar);
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(BasicShopMapActivity.this, null);
    }
}
