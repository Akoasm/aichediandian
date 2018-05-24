package com.sinata.rwxchina.basiclib.basic.basicmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.entity.ScenicEntity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.NativeUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.ActionSheetDialog;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 商铺列表地图页面
 * @modifyRecord
 */

public class BasicShopListMapActivity extends BaseActivity {
    /**
     * 地图
     */
    private MapView mMapView;
    /**
     * 初始化地图控制器对象
     */
    private AMap aMap;
    /**
     * 标题
     */
    private ImageView back;
    private TextView title;
    private View statusBar;
    /**
     * 店铺信息
     */
    private ImageView logo;
    private TextView name, address, makerName;
    private LinearLayout goMap, shopname, mapMarker;
    private boolean isNearbyService, isScenic;
    private Map<String, String> param;
    private List<PoiItem> list;
    private String shopaddress, lat, lng, shoptype;
    private List<Marker> markerList;
    private boolean iszoom;
    private int zoom;


    @Override
    public void initParms(Bundle params) {
        param = new HashMap<>();
        markerList = new ArrayList<>();
        isNearbyService = params.getBoolean("isNearbyService", false);
        isScenic = params.getBoolean("isScenic", false);
        if (isNearbyService) {
            list = params.getParcelableArrayList("nearbyService");
        } else {
            if (!isScenic) {
                param.put("shop_type", params.getString("shopType"));
                shoptype = params.getString("shopType");
                if (shoptype.equals("1"))
                    param.put("shop_type_labels", params.getString("shop_type_labels"));
            }
            param.put("distance_max", "15");
        }
        iszoom = false;
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
        mMapView = findViewById(R.id.shop_map);
        back = findViewById(R.id.food_comment_back);
        title = findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("地图");
        logo = findViewById(R.id.shopMap_logo);
        name = findViewById(R.id.shopMap_shopName);
        address = findViewById(R.id.shopMap_shopAddress);
        goMap = findViewById(R.id.shopMap_go);
        shopname = findViewById(R.id.shopMap_shopName_ll);
        mMapView.onCreate(savedInstanceState);
        mapMarker = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.map_marker, null);
        makerName = mapMarker.findViewById(R.id.marker_shopName_tv);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        zoom = 14;
    }

    @Override
    public void setListener() {
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                initMaker();
                if (marker.getObject() instanceof PoiItem) {
                    PoiItem p = (PoiItem) marker.getObject();
                    if (p.getPhotos().size() > 0)
                        setShop(p.getPhotos().get(0).getUrl(), p.getTitle(), p.getSnippet(), p.getLatLonPoint().getLatitude() + "", p.getLatLonPoint().getLongitude() + "");
                    else
                        setShop("", p.getTitle(), p.getSnippet(), p.getLatLonPoint().getLatitude() + "", p.getLatLonPoint().getLongitude() + "");
                    changeMaker(marker,p.getTitle());
                } else if (marker.getObject() instanceof BaseShopInfo) {
                    BaseShopInfo b = (BaseShopInfo) marker.getObject();
                    setShop(b.getShop_logo(), b.getShop_name(), b.getShop_address(), b.getShop_lat(), b.getShop_lng());
                    changeMaker(marker,b.getShop_name());
                    if (shoptype.equals("1")) {
                        jumpActivity(b.getShopid(), b.getShop_name(), b.getM_shop_type_labels(), null);
                    } else {
                        jumpActivity(b.getShopid(), b.getShop_name(), null, null);
                    }
                } else if (marker.getObject() instanceof ScenicEntity) {
                    ScenicEntity s = (ScenicEntity) marker.getObject();
                    setShop(s.getDefault_image(), s.getGoods_name(), s.getGoods_address(), s.getGoods_lat(), s.getGoods_lng());
                    jumpActivity(s.getGoods_id(), s.getGoods_name(), null, s);
                    changeMaker(marker,s.getGoods_name());
                }
                return true;
            }
        });
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                zoom = (int) cameraPosition.zoom;
                if (zoom >15) {
                    if (!iszoom) {
                       changeZoomMaker();
                        iszoom = true;
                    }
                } else {
                    if (iszoom) {
                        for (int i = 0; i < markerList.size(); i++) {
                            markerList.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_unselect)));
                        }
                        iszoom = false;
                    }
                }

            }
        });
        back.setOnClickListener(this);
        goMap.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id = v.getId();
        if (id == R.id.food_comment_back) {
            finish();
        } else if (id == R.id.shopMap_go) {
            goMap();
        }
    }

    public Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;

    }

    private void initMaker() {
        if (zoom > 14) {
            changeZoomMaker();
        } else {
            for (int i = 0; i < markerList.size(); i++) {
                markerList.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_unselect)));
            }
        }
    }

    private void changeMaker(Marker marker,String name) {
        if (zoom > 14) {
            makerName.setBackgroundResource(R.drawable.bubble);
            makerName.setText(name);
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(makerName)));
        } else {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_select)));
        }
    }

    private void changeZoomMaker(){
        makerName.setBackgroundResource(R.drawable.bubble_gray);
        for (int i = 0; i < markerList.size(); i++) {
            if (markerList.get(i).getObject() instanceof PoiItem) {
                PoiItem poiItem = (PoiItem) markerList.get(i).getObject();
                makerName.setText(poiItem.getTitle());
            } else if (markerList.get(i).getObject() instanceof BaseShopInfo) {
                BaseShopInfo baseShopInfo = (BaseShopInfo) markerList.get(i).getObject();
                makerName.setText(baseShopInfo.getShop_name());
            } else if (markerList.get(i).getObject() instanceof ScenicEntity) {
                ScenicEntity scenicEntity = (ScenicEntity) markerList.get(i).getObject();
                makerName.setText(scenicEntity.getGoods_name());
            }
            markerList.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(makerName)));
        }
    }
    /**
     * 跳转店铺详情
     *
     * @param shopID
     * @param shopnames
     * @param type_labels
     * @param scenicEntity
     */
    private void jumpActivity(String shopID, String shopnames, @Nullable final String type_labels, @Nullable final ScenicEntity scenicEntity) {
        final Bundle bundle = new Bundle();
        if (!isNearbyService) {
            bundle.putString("shopid", shopID);
            bundle.putString("shop_name", shopnames);
            if (type_labels != null)
                bundle.putString("m_shop_type_labels", type_labels);
            shopname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isScenic) {
                        switch (shoptype) {
                            case "2"://酒店
                                startActivityRuntime("com.sinata.rwxchina.component_basic.hotel.activity.HotelActivity", bundle);
                                break;
                            case "3"://美食
                                startActivityRuntime("com.sinata.rwxchina.component_basic.finefood.activity.FoodActivity", bundle);
                                break;
                            case "4"://KTV
                                startActivityRuntime("com.sinata.rwxchina.component_basic.ktv.activity.KTVActivity", bundle);
                                break;
                            case "1"://汽车
                                startActivityRuntime("com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity", bundle);
                                break;
                            case "6"://养生
                                startActivityRuntime("com.sinata.rwxchina.component_basic.regimen.activity.HealthActivity", bundle);
                                break;
                        }
                    } else {
                        bundle.putSerializable("scenic", scenicEntity);
                        startActivityRuntime("com.sinata.rwxchina.component_basic.scenic.activity.ScenicActivity", bundle);
                    }
                }
            });

        }
    }

    /**
     * 设置店铺信息
     */
    private void setShop(String url, String shopName, String shopaddress, String lat, String lng) {
        if (isNearbyService) {
            ImageUtils.showImage(this, url, logo);
        } else if (!isNearbyService) {
            ImageUtils.showImage(this, HttpPath.IMAGEURL + url, logo);
        }
        name.setText(shopName);
        address.setText(shopaddress);
        this.shopaddress = shopaddress;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitleBarView();
        setBlueLocation();
        if (isNearbyService) {
            for (int i = 0; i < list.size(); i++) {
                showMarker(list.get(i).getLatLonPoint().getLatitude() + "", list.get(i).getLatLonPoint().getLongitude() + "", list.get(i).getTitle(), list.get(i).getSnippet(), list.get(i));
            }
            if (list.get(0).getPhotos().size() > 0)
                setShop(list.get(0).getPhotos().get(0).getUrl(), list.get(0).getTitle(), list.get(0).getSnippet(), list.get(0).getLatLonPoint().getLatitude() + "", list.get(0).getLatLonPoint().getLongitude() + "");
            else
                setShop("", list.get(0).getTitle(), list.get(0).getSnippet(), list.get(0).getLatLonPoint().getLatitude() + "", list.get(0).getLatLonPoint().getLongitude() + "");
        } else {
            getShoplist();
        }

    }
    /**
     * 设置店铺Marker
     */
    private void showMarker(String lat, String lng, String shopName, String address, Object o) {
        //绘制marker
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_unselect)))
                .draggable(false));
        marker.setObject(o);
        markerList.add(marker);
    }

    /**
     * 获取店铺信息
     */
    private void getShoplist() {
        ApiManager api = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (isScenic) {//获取景区
                    List<ScenicEntity> list = new Gson().fromJson(result, new TypeToken<List<ScenicEntity>>() {
                    }.getType());
                    for (int i = 0; i < list.size(); i++) {
                        showMarker(list.get(i).getGoods_lat(), list.get(i).getGoods_lng(), list.get(i).getGoods_name(), list.get(i).getGoods_address(), list.get(i));
                    }
                    setShop(list.get(0).getDefault_image(), list.get(0).getGoods_name(), list.get(0).getGoods_address(), list.get(0).getGoods_lat(), list.get(0).getGoods_lng());
                    jumpActivity(list.get(0).getGoods_id(), list.get(0).getGoods_name(), null, list.get(0));
                } else {//获取非景区店铺
                    List<BaseShopInfo> list = new Gson().fromJson(result, new TypeToken<List<BaseShopInfo>>() {
                    }.getType());
                    for (int i = 0; i < list.size(); i++) {
                        showMarker(list.get(i).getShop_lat(), list.get(i).getShop_lng(), list.get(i).getShop_name(), list.get(i).getShop_address(), list.get(i));
                    }
                    setShop(list.get(0).getShop_logo(), list.get(0).getShop_name(), list.get(0).getShop_address(), list.get(0).getShop_lat(), list.get(0).getShop_lng());
                    if (shoptype.equals("1")) {
                        jumpActivity(list.get(0).getShopid(), list.get(0).getShop_name(), list.get(0).getM_shop_type_labels(), null);
                    } else {
                        jumpActivity(list.get(0).getShopid(), list.get(0).getShop_name(), null, null);
                    }
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        if (isScenic)
            api.get(HttpPath.GETSCENIC, param);
        else
            api.get(HttpPath.GETSHOPLIST, param);
    }


    /**
     * 设置定位小蓝点
     */
    private void setBlueLocation() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

    }

    private void goMap() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("百度地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                NativeUtils.invokingBD(BasicShopListMapActivity.this, shopaddress, LocationUtils.getCity(BasicShopListMapActivity.this));
                            }
                        })
                .addSheetItem("高德地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                NativeUtils.invokingGD(BasicShopListMapActivity.this, lat, lng);
                            }
                        }).show();
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(BasicShopListMapActivity.this, null);
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
}
