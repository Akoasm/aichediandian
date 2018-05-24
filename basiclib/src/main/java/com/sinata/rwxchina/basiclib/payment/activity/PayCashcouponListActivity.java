package com.sinata.rwxchina.basiclib.payment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.CouponRuleActivity;
import com.sinata.rwxchina.basiclib.payment.adapter.PayCashcouponListAdapter;
import com.sinata.rwxchina.basiclib.payment.entity.CouponEntity;
import com.sinata.rwxchina.basiclib.payment.utils.PayCashcouponUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/1/8
 * @describe 支付时选择代金券列表
 * @modifyRecord
 */

public class PayCashcouponListActivity extends BaseActivity {
    private SmartRefreshLayout pay_refresh;
    private RecyclerView pay_recycler;
    private List<CouponEntity> couponEntities;
    private PayCashcouponListAdapter adapter;
    /**城市id*/
    private String cityId;
    /**支付价格*/
    private String price;
    /**店铺分类*/
    private String shop_type;
    /**店铺id*/
    private String shopid;
    /**是否在商城中使用（0：不是，1：是）*/
    private String is_mall;
    private TextView title,rightIcon;
    private ImageView back;
    @Override
    public void initParms(Bundle params) {
        cityId=params.getString("cityid");
        price=params.getString("price");
        shop_type=params.getString("shop_type");
        shopid=params.getString("shopid");
        is_mall=params.getString("is_mall");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_paycashcouponlist;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        pay_refresh=findViewById(R.id.paycashcouponlist_refresh);
        pay_recycler=findViewById(R.id.paycashcouponlist_recycler);
        pay_recycler.setLayoutManager(new LinearLayoutManager(this));
        title = findViewById(R.id.titleLayout_title_tv);
        back = findViewById(R.id.back);
        rightIcon = findViewById(R.id.rightIcon_tv);
        couponEntities=new ArrayList<CouponEntity>();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        getCoupon();
    }

    private void getCoupon(){
        LogUtils.e("Pay","cityid="+cityId+"  price="+price+"  shopType="+shop_type+"  shopid="+shopid+"  ismall="+is_mall);
        Map<String,String> params=new HashMap<>();
        params.put("cityid",cityId);
        params.put("price",price);
        params.put("shop_type",shop_type);
        params.put("shopid",shopid);
        params.put("Is_mall",is_mall);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                couponEntities=new Gson().fromJson(result,new TypeToken<List<CouponEntity>>(){}.getType());
                adapter=new PayCashcouponListAdapter(R.layout.item_mycoupon,couponEntities);
                pay_recycler.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        PayCashcouponUtils.setCoupon(couponEntities.get(position));
                        finish();
                    }
                });
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GET_COUPON,params);
    }

    private void setTitle() {
        title.setText("代金券");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        rightIcon.setText("使用规则");
        rightIcon.setTextColor(ContextCompat.getColor(this,R.color.colorOrange));
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CouponRuleActivity.class);
            }
        });
    }
}
