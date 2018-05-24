package com.sinata.rwxchina.component_basic.hotel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.dateutils.DateUtils;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.component_basic.basic.basiccheckself.CheckSelfUtils;
import com.sinata.rwxchina.component_basic.basic.basiccomment.CommentUtils;
import com.sinata.rwxchina.component_basic.basic.basicinformation.InformationUtils;
import com.sinata.rwxchina.component_basic.basic.basicname.NameUtils;
import com.sinata.rwxchina.component_basic.basic.basicnearby.NearByUtils;
import com.sinata.rwxchina.component_basic.basic.basicservice.ServiceUtils;
import com.sinata.rwxchina.component_basic.hotel.adapter.HotelReserveAdapter;
import com.sinata.rwxchina.component_basic.hotel.adapter.HotelTypeAdapter;
import com.sinata.rwxchina.component_basic.hotel.entity.HotelEntity;
import com.sinata.rwxchina.component_basic.hotel.entity.HotelReserveEntity;
import com.sinata.rwxchina.component_basic.hotel.utils.DatePickerUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @detetime：2017/10/25
 * @describe：酒店首页
 */


public class HotelActivity extends BaseActivity implements DatePickerUtils.CallDate{
    private HotelEntity hotelInfo;
    private BaseShopInfo shopInfo;
    /**店铺基本信息*/
    private View bannerView,nameView,informationView;
    /**自助买单*/
    private View discount;
    /**评价*/
    private View commentView;
    /**更多服务*/
    private View serviceView;
    /**附近商家*/
    private View nearByView;
    /**预定*/
    private View reserveView;
    private TextView shopSale;
    private TextView checkDateTv,checkWeek,leaveDateTv,leaveWeek,nightNumber;
    private Date todayDate,checkDate,leaveDate;
    private RecyclerView typeRecycler,reserveRecycler;
    private HotelTypeAdapter typeAdapter;
    private List<HotelReserveEntity> hotels;
    private HotelReserveAdapter reserveAdapter;
    private List<BaseGoodsInfo> goodsInfos;
    private int selectedPos=0;
    private String shopid;
    private String shopname;
    /**titleBar*/
    private View titleBarView;
    private View statusBar;
    private NestedScrollView scrollView;
    /**预定酒店天数*/
    private int number;
    @Override
    public void initParms(Bundle parms) {
        shopid = parms.getString("shopid");
        shopname = parms.getString("shop_name");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_hotel;
    }

    @Override
    public void initView(View view, final Bundle bundle) {
        goodsInfos=new ArrayList<>();
        reserveView=findViewById(R.id.hotel_reserve);
        titleBarView = findViewById(R.id.hotel_title);
        scrollView = findViewById(R.id.hotel_scrollview);
        statusBar = findViewById(R.id.title_bar_fakeview);
        hotels=new ArrayList<>();
        bannerView=findViewById(R.id.hotel_banner);
        nameView=findViewById(R.id.hotel_name);
        informationView=findViewById(R.id.hotel_information);
        discount=findViewById(R.id.hotel_checkSelf);
        commentView=findViewById(R.id.hotel_comment);
        serviceView=findViewById(R.id.hotel_service);
        nearByView=findViewById(R.id.hotel_nearby);
        shopSale=findViewById(R.id.hotel_shop_sale);
        checkDateTv=findViewById(R.id.hotel_check_date);
        checkWeek=findViewById(R.id.hotel_check_week);
        leaveDateTv=findViewById(R.id.hotel_leave_date);
        leaveWeek=findViewById(R.id.hotel_leave_week);
        nightNumber=findViewById(R.id.hotel_night_number);
        todayDate=new Date();
        typeRecycler=findViewById(R.id.hotel_type_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeRecycler.setLayoutManager(manager);
        reserveRecycler=findViewById(R.id.hotel_reserve_recycler);
        reserveRecycler.setLayoutManager(new LinearLayoutManager(this));
        typeAdapter=new HotelTypeAdapter(HotelActivity.this,R.layout.item_ktvtype,hotels);
        typeRecycler.setAdapter(typeAdapter);
        reserveAdapter=new HotelReserveAdapter(HotelActivity.this,R.layout.item_hotel_reserve,goodsInfos);
        reserveRecycler.setAdapter(reserveAdapter);
        reserveRecycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,2,getResources().getColor(R.color.background)));
        reserveAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.item_hotel_reserve_btn){
                    String goods=new Gson().toJson(goodsInfos.get(position));
                    LogUtils.e("Hotel","goods="+goods);
                    String shop=new Gson().toJson(shopInfo);
                    Intent intent=new Intent(HotelActivity.this,HotelReserveActivity.class);
                    Bundle bundle1=new Bundle();
                    bundle1.putString("goods",goods);
                    bundle1.putString("shop",shop);
                    bundle1.putInt("num",number);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void setListener() {
        checkDateTv.setOnClickListener(this);
        leaveDateTv.setOnClickListener(this);
        nightNumber.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.hotel_check_date||id==R.id.hotel_leave_date||id==R.id.hotel_night_number){
            DatePickerUtils.setDatePicker(HotelActivity.this,bannerView);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getHotel();
        initDate();
        setComment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();
    }

    private void getHotel(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopid);
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("HotelActivity","result="+result);
                hotelInfo=new Gson().fromJson(result,HotelEntity.class);
                LogUtils.e("HotelActivity","hotelInfo="+hotelInfo.toString());
                shopInfo=hotelInfo.getShopInfo();
                setShop();
                setCheckSelf();
                setReserve();
                setService();
                setNearBy();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETHOTEL,params);
    }

    /***
     * 设置店铺基本信息
     */
    private void setShop(){
        BannerUtils.setBanner(bannerView,this,shopInfo);
        NameUtils.setName(nameView,shopInfo);
        InformationUtils.setInformation(informationView,this,shopInfo);
    }

    /**
     * 设置自助买单
     */
    private void setCheckSelf(){
        CheckSelfUtils.setCheckSelf(this,discount,shopInfo);
    }
    /**
     * 设置用户评价
     */
    private void setComment(){
        CommentUtils.getComment(commentView,this,shopid);
    }

    /**
     * 设置更多服务
     */
    private void setService(){
        ServiceUtils.setService(serviceView,shopInfo.getShop_service());
    }

    /**
     * 设置附近店铺
     */
    public void setNearBy(){
        NearByUtils.getNearBy(nearByView,this,shopInfo);
    }

    /**
     * 设置预定
     */
    private void setReserve(){

        shopSale.setText(shopInfo.getShop_sale()+"人订过");
        typeAdapter.addData(hotelInfo.getGoods());
        if (hotelInfo.getGoods()==null||hotelInfo.getGoods().size()==0){
            reserveView.setVisibility(View.GONE);
            return;
        }
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goodsInfos=hotelInfo.getGoods().get(position).getList();

                reserveAdapter.setNewData(goodsInfos);
                reserveAdapter.notifyDataSetChanged();
                unClick(adapter,typeRecycler,selectedPos);
                selectedPos=position;
                click(adapter,typeRecycler,selectedPos);
            }
        });
        checkOne();
    }

    /**
     * 默认选中第一个
     */
    private void checkOne(){
        goodsInfos=hotelInfo.getGoods().get(0).getList();
        reserveAdapter.setNewData(goodsInfos);
        reserveAdapter.notifyDataSetChanged();
//        click(typeAdapter,typeRecycler,0);
    }

    /**
     * 设置item被点击高亮
     * @param adapter
     * @param position
     */
    private void click(BaseQuickAdapter adapter,RecyclerView recyclerView,int position){
        adapter.getViewByPosition(recyclerView,position,R.id.item_ktvtype).setBackgroundDrawable(HotelActivity.this.getResources().getDrawable(R.drawable.right_angle_border_orange));
    }

    /**
     * 取消item高亮效果
     * @param adapter
     * @param position
     */
    private void unClick(BaseQuickAdapter adapter,RecyclerView recyclerView,int position){
        adapter.getViewByPosition(recyclerView,position,R.id.item_ktvtype).setBackgroundDrawable(HotelActivity.this.getResources().getDrawable(R.drawable.right_angle_border));
    }

    /**
     * 初始化预定信息
     */
    private void initDate(){
        checkDateTv.setText((todayDate.getMonth()+1)+"月"+todayDate.getDate()+"日");
        Date nextDay=DateUtils.getNextDay();
        leaveDateTv.setText((nextDay.getMonth()+1)+"月"+nextDay.getDate()+"日");
        //设置商品数量
        number=1;
        //初始化入住和离开时间
        SinglePayment.getSinglePayment().setBespeak_date((1900+todayDate.getYear())+"-"+(todayDate.getMonth()+1)+"-"+todayDate.getDate());
        SinglePayment.getSinglePayment().setBespeak_leave_date((1900+nextDay.getYear())+"-"+(nextDay.getMonth()+1)+"-"+nextDay.getDate());
    }

    /**
     * 预定信息发生改变
     */
    private void changeDate(){
        checkDateTv.setText((checkDate.getMonth()+1)+"月"+checkDate.getDate()+"日");
        leaveDateTv.setText((leaveDate.getMonth()+1)+"月"+leaveDate.getDate()+"日");
        checkWeek.setText(DateUtils.getWeekday(checkDate));
        leaveWeek.setText(DateUtils.getWeekday(leaveDate));
        int nightNum= DateUtils.differentDaysByMillisecond(checkDate,leaveDate);
        nightNumber.setText("共"+nightNum+"晚");
        //设置商品数量
        number=nightNum;
        //设置入住和离开时间
        SinglePayment.getSinglePayment().setBespeak_date((1900+checkDate.getYear())+"-"+(checkDate.getMonth()+1)+"-"+checkDate.getDate());
        SinglePayment.getSinglePayment().setBespeak_leave_date((1900+leaveDate.getYear())+"-"+(leaveDate.getMonth()+1)+"-"+leaveDate.getDate());
    }
    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTransparentForImageViewInFragment(HotelActivity.this, null);
    }

    @Override
    public void getCallDate(Date check, Date leave) {
        checkDate=check;
        leaveDate=leave;
        changeDate();

    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,shopname);
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }
}
