package com.sinata.rwxchina.component_basic.car.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopMapActivity;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.component_basic.basic.basiccheckself.CheckSelfUtils;
import com.sinata.rwxchina.component_basic.car.entity.CarGoodsInfo;
import com.sinata.rwxchina.component_basic.car.entity.CarShopInfo;
import com.sinata.rwxchina.component_basic.car.fragment.WashcarFragment;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @detetime：2017/11/3
 * @describe：汽车服务商铺详情
 */


public class CleanCarActivity extends BaseActivity implements WashcarFragment.CallCarGoods{
    /**洗车商品分类*/
    private final String WASHCARTYPE="101";
    /**保养商品分类*/
    private final String MAINTAINTYPE="102";
    /**美容商品分类*/
    private final String COSMETOLOGYTYPE="103";
    /**维修商品分类*/
    private final String REPAIRTYPE="104";
    /**汽车服务店铺实体类*/
    private CarShopInfo carInfo;
    /**已选中商品的实体类*/
    private CarGoodsInfo goodsInfo;
    /**titleBar*/
    private View titleBarView;
    /**MyScrollview*/
    private MyScrollView scrollView;
    private ImageView washCar;//洗车
    private ImageView maintain;//保养
    private ImageView cosmetology;//美容
    private ImageView repair;//维修
    /**洗车碎片*/
    private WashcarFragment washCarFragment;
    /**保养碎片*/
    private WashcarFragment maintainFragment;
    /**美容碎片*/
    private WashcarFragment cosmetologyFragment;
    /**维修碎片*/
    private WashcarFragment repairFragment;
    /**洗车数据*/
    private List<CarGoodsInfo> washCarList;
    /**保养数据*/
    private List<CarGoodsInfo> maintainList;
    /**美容数据*/
    private List<CarGoodsInfo> cosmetologyList;
    /**维修数据*/
    private List<CarGoodsInfo> repairList;
    /**banner*/
    private View bannerView;
    /**店铺信息实例*/
    private BaseShopInfo shopInfo;
    private TextView shopName,shopAddress,shopStar,shopDistance;
    private RatingBar ratingBar;
    private LinearLayout address_linear;
    /**自助买单*/
    private View selfView;
    /**电话*/
    private ImageView callPhone;

    /**折扣*/
    private String shopId;
    private String shopname;
    /**支付金额*/
    private TextView payMoneyTv;
    /**支付*/
    private Button goPay;
    /**状态栏*/
    private View statusBar;
    private String m_shop_type_labels="1";
    @Override
    public void initParms(Bundle params) {
        shopId=params.getString("shopid");
        shopname = params.getString("shop_name");
        m_shop_type_labels=params.getString("m_shop_type_labels");
        LogUtils.e("CleanCarActivity","列表跳转:"+m_shop_type_labels);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_cleancar;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        washCarList=new ArrayList<CarGoodsInfo>();
        maintainList=new ArrayList<CarGoodsInfo>();
        cosmetologyList=new ArrayList<CarGoodsInfo>();
        repairList=new ArrayList<CarGoodsInfo>();
        titleBarView=findViewById(R.id.cleanCar_titleBar);
        scrollView=findViewById(R.id.activity_cleancar_scroll);
        selfView=findViewById(R.id.car_self);
        washCar=findViewById(R.id.washcar_btn_cleancar);
        maintain=findViewById(R.id.upkeep_btn_cleancar);
        cosmetology=findViewById(R.id.hairdressing_btn_cleancar);
        repair=findViewById(R.id.maintain_btn_cleancar);
        bannerView=findViewById(R.id.cleanCar_banner);
        shopName=findViewById(R.id.clean_titlename);
        shopAddress=findViewById(R.id.clean_address);
        address_linear = findViewById(R.id.clean_address_linear);
        shopStar=findViewById(R.id.clean_starnum);
        shopDistance=findViewById(R.id.clean_distance_num);
        ratingBar=findViewById(R.id.clean_Ratingbar);
        callPhone=findViewById(R.id.clean_phone);
        payMoneyTv=findViewById(R.id.clean_bottomprice);
        goPay=findViewById(R.id.clean_goPay);
        statusBar = findViewById(R.id.title_bar_fakeview);
    }



    @Override
    public void setListener() {
        washCar.setOnClickListener(this);
        maintain.setOnClickListener(this);
        cosmetology.setOnClickListener(this);
        repair.setOnClickListener(this);
        callPhone.setOnClickListener(this);
        goPay.setOnClickListener(this);
        address_linear.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.washcar_btn_cleancar){
            changeRadioButton(1);
        }else if(id==R.id.upkeep_btn_cleancar){
            changeRadioButton(2);
        }else if(id==R.id.hairdressing_btn_cleancar){
            changeRadioButton(3);
        }else if (id==R.id.maintain_btn_cleancar){
            changeRadioButton(4);
        }else if(id==R.id.clean_phone){
            CallPhoneUtils.call(this, shopInfo.getShop_telephone());
        }else if(id==R.id.clean_goPay){
            goPay();
        }else if(id == R.id.clean_address_linear){
            String shop=new Gson().toJson(shopInfo);
            Intent goMap=new Intent(this, BasicShopMapActivity.class);
            goMap.putExtra("shopInfo",shop);
            startActivity(goMap);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();
    }

    /**
     * 获取店铺信息
     */
    private void getData(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
//        params.put("shopid","15940954506");
        params.put("lng", LocationUtils.getLng(this));
        params.put("lat",LocationUtils.getLng(this));
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code,String msg, PageInfo pageInfo) throws Exception {
                carInfo=new Gson().fromJson(result,CarShopInfo.class);
                shopInfo=carInfo.getShopinfo();
                washCarList=carInfo.getGoods().get(0).getList();
                maintainList=carInfo.getGoods().get(1).getList();
                cosmetologyList=carInfo.getGoods().get(2).getList();
                repairList=carInfo.getGoods().get(3).getList();
                initShop();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETCAR,params);
    }


    /**
     * 初始化商铺信息
     */
    private void initShop(){
        setBanner();
        setSelfView();
        shopName.setText(shopInfo.getShop_name());
        shopStar.setText(shopInfo.getShop_starlevel());
        shopAddress.setText(shopInfo.getShop_address());
        shopDistance.setText(shopInfo.getDistance()+"km");
        ratingBar.setRating(Float.parseFloat(shopInfo.getShop_starlevel()));
//        setRadioButton();
        int init=1;
        switch (m_shop_type_labels){
            case "1":
                init=1;
                break;
            case "2":
                init=2;
                break;
            case "3":
                init=3;
                break;
            case "4":
                init=4;
                break;
            default:
                break;
        }
        changeRadioButton(init);
    }

    /**
     * 设置radioButton状态
     */
//    private void setRadioButton(){
//        if (washCarList==null||washCarList.size()==0){
//            washCar.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        }
//        if (maintainList==null||maintainList.size()==0){
//            maintain.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        }
//        if (cosmetologyList==null||cosmetologyList.size()==0){
//            cosmetology.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        }
//        if (repairList==null||repairList.size()==0){
//            repair.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        }
//
//    }
    /**
     * 改变RadioButton状态
     * @param i
     */
    public void changeRadioButton(int i) {
        switch (i){
            case 1:
                changeImg(1);
                setFragment(1);
                break;
            case 2:
                changeImg(2);
                setFragment(2);
                break;
            case 3:
                changeImg(3);
                setFragment(3);
                break;
            case 4:
                changeImg(4);
                setFragment(4);
                break;
            default:
                break;
        }
    }

    public void changeImg(int i) {
        LogUtils.e("CleanCarActivity","washCarList.size:"+washCarList.size()+"  maintainList.size():"+maintainList.size()
                +"  cosmetologyList.size():"+cosmetologyList.size()+"  repairList.size():"+repairList.size());
        if (washCarList!=null&&washCarList.size()>0) {
            washCar.setImageResource(R.mipmap.clean_washcar_normal);
        } else {
            washCar.setImageResource(R.mipmap.clean_washcar_normal_strikethrough);
        }
        if (maintainList != null&&maintainList.size()>0) {
            maintain.setImageResource(R.mipmap.clean_upkeep_normal);
        } else {
            maintain.setImageResource(R.mipmap.clean_upkeep_normal_strikethrough);
        }

        if (cosmetologyList != null&&cosmetologyList.size()>0) {
            cosmetology.setImageResource(R.mipmap.clean_hairdressing_normal);
        } else {
            cosmetology.setImageResource(R.mipmap.clean_hairdressing_normal_strikethrough);
        }
        if (repairList != null&&repairList.size()>0) {
            repair.setImageResource(R.mipmap.clean_repair_normal);
        } else {
            repair.setImageResource(R.mipmap.clean_repair_normal_strikethrough);
        }
        switch (i) {
            case 1:
                if (washCarList != null&&washCarList.size()>0) {
                    washCar.setImageResource(R.mipmap.clean_washcar_check);
                } else {
                    washCar.setImageResource(R.mipmap.clean_washcar_check_strikethrough);
                }
                break;
            case 2:
                if (maintainList != null&&maintainList.size()>0) {
                    maintain.setImageResource(R.mipmap.clean_upkeep_check);
                } else {
                    maintain.setImageResource(R.mipmap.clean_upkeep_check_strikethrough);
                }
                break;
            case 3:
                if (cosmetologyList != null&&cosmetologyList.size()>0) {
                    cosmetology.setImageResource(R.mipmap.clean_hairdressing_check);
                } else {
                    cosmetology.setImageResource(R.mipmap.clean_hairdressing_check_strikethrough);
                }
                break;
            case 4:
                if (repairList != null&&repairList.size()>0) {
                    repair.setImageResource(R.mipmap.clean_repair_check);
                } else {
                    repair.setImageResource(R.mipmap.clean_repair_check_strikethrough);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置fragment
     * @param i
     */
    public void setFragment(int i) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        hideFragment(mTransaction);
        switch (i) {
            //洗车
            case 1:
                if (washCarFragment == null) {
                    washCarFragment = new WashcarFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cleanList",new Gson().toJson(carInfo.getGoods().get(0).getList()));
                    washCarFragment.setArguments(bundle);
                    mTransaction.add(R.id.clean_context, washCarFragment);
                } else {
                    mTransaction.show(washCarFragment);
                }

                break;
            //保养
            case 2:
                if (maintainFragment == null) {
                    maintainFragment = new WashcarFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cleanList",new Gson().toJson(carInfo.getGoods().get(1).getList()));
                    maintainFragment.setArguments(bundle);
                    mTransaction.add(R.id.clean_context, maintainFragment);
                } else {
                    mTransaction.show(maintainFragment);
                }
                break;
            //美容
            case 3:
                if (cosmetologyFragment == null) {
                    cosmetologyFragment = new WashcarFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cleanList",new Gson().toJson(carInfo.getGoods().get(2).getList()));
                    cosmetologyFragment.setArguments(bundle);
                    mTransaction.add(R.id.clean_context, cosmetologyFragment);
                } else {
                    mTransaction.show(cosmetologyFragment);
                }
                break;
            //维修
            case 4:
                if (repairFragment == null) {
                    repairFragment = new WashcarFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cleanList",new Gson().toJson(carInfo.getGoods().get(3).getList()));
                    repairFragment.setArguments(bundle);
                    mTransaction.add(R.id.clean_context, repairFragment);
                } else {
                    mTransaction.show(repairFragment);
                }

                break;
            default:
                break;
        }
            mTransaction.commit();
    }

    /**
     * 隐藏fragment
     * @param mTransaction
     */
    private void hideFragment(FragmentTransaction mTransaction) {
        if (washCarFragment != null) {
            mTransaction.hide(washCarFragment);
        }
        if (maintainFragment != null) {
            mTransaction.hide(maintainFragment);
        }
        if (cosmetologyFragment != null) {
            mTransaction.hide(cosmetologyFragment);
        }
        if (repairFragment != null) {
            mTransaction.hide(repairFragment);
        }
    }

    /**
     * 设置banner轮播图
     */
    private void setBanner(){
        BannerUtils.setBanner(bannerView,this,shopInfo);
    }

    /**
     * 设置自助买单
     */
    private void setSelfView(){
        CheckSelfUtils.setCheckSelf(this,selfView,shopInfo);
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,shopname);
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }

    public void changeStyle() {
        switch (goodsInfo.getGoods_type()){
            //洗车
            case WASHCARTYPE:
                if (maintainFragment != null) {
                    maintainFragment.choosestate();
                }
                if (cosmetologyFragment != null) {
                    cosmetologyFragment.choosestate();
                }
                if (repairFragment != null) {
                    repairFragment.choosestate();
                }
                break;
            //保养
            case MAINTAINTYPE:
                if (washCarFragment != null) {
                    washCarFragment.choosestate();
                }
                if (cosmetologyFragment != null) {
                    cosmetologyFragment.choosestate();
                }
                if (repairFragment != null) {
                    repairFragment.choosestate();
                }
                break;
            //美容
            case COSMETOLOGYTYPE:
                if (maintainFragment != null) {
                    maintainFragment.choosestate();
                }
                if (washCarFragment != null) {
                    washCarFragment.choosestate();
                }
                if (repairFragment != null) {
                    repairFragment.choosestate();
                }
                break;
            //维修
            case REPAIRTYPE:
                if (maintainFragment != null) {
                    maintainFragment.choosestate();
                }
                if (cosmetologyFragment != null) {
                    cosmetologyFragment.choosestate();
                }
                if (washCarFragment != null) {
                    washCarFragment.choosestate();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到支付页面
     */
    private void goPay(){
        if ("0".equals(payMoneyTv.getText().toString())||"0.00".equals(payMoneyTv.getText().toString())){
            showToast("请选择商品");
        }else {
            SinglePayment.getSinglePayment().setIs_mall("0");
            StartPayMentUtils.startPayment(this,goodsInfo,shopInfo);
        }
    }

    @Override
    public void SendPrice(CarGoodsInfo strValue) {
        if (strValue == null) {
            payMoneyTv.setText("0");
        } else {

            goodsInfo = strValue;
            changeStyle();
            payMoneyTv.setText(strValue.getGoods_price());
        }
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(CleanCarActivity.this, null);
    }
}
