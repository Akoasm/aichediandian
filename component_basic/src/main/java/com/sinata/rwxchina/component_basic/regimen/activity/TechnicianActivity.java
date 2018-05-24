package com.sinata.rwxchina.component_basic.regimen.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiccomment.CommentUtils;
import com.sinata.rwxchina.component_basic.basic.basicinformation.InformationUtils;
import com.sinata.rwxchina.component_basic.basic.basicname.NameUtils;
import com.sinata.rwxchina.component_basic.finefood.activity.SpecialitiesActivity;
import com.sinata.rwxchina.component_basic.regimen.entity.TechnicianEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 技师介绍页面
 * @modifyRecord
 */

public class TechnicianActivity extends BaseActivity {
    private TechnicianEntity technician;
    private BaseShopInfo shopInfo;
    private String teamsonid;
    /**标题*/
    private ImageView back;
    private TextView title;
    private View statusBar;
    /**技师信息*/
    private ImageView logo;
    private TextView name,ranks,genius;
    /**店铺信息*/
    private View nameView,informationView;
    /**用户评价*/
    private View commentView;
    @Override
    public void initParms(Bundle params) {
        String shop=params.getString("shop");
        teamsonid=params.getString("teamsonid");
        if (!TextUtils.isEmpty(shop)){
            shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
            LogUtils.e("shopinfo"+shopInfo);
        }
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_health_technician_introduce;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        title.setText("技师介绍");
        logo=findViewById(R.id.health_technician_introduce_headpor);
        name=findViewById(R.id.health_technician_introduce_name);
        ranks=findViewById(R.id.health_technician_introduce_ranks);
        genius=findViewById(R.id.health_technician_introduce_genius);
        nameView=findViewById(R.id.technician_name);
        informationView=findViewById(R.id.technician_information);
        commentView=findViewById(R.id.technician_comment);
        statusBar = findViewById(R.id.normal_fakeview);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==v.getId()){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getTechnician();
        setShop();
        setComment();
        setTitleBarView();
    }

    private void getTechnician(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("teamsonid",teamsonid);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                technician=new Gson().fromJson(result,TechnicianEntity.class);
                setTechnician();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETMEMBERINFO,params);
    }

    /**
     * 设置技师基本信息
     */
    private void setTechnician(){
        //设置头像
        ImageUtils.showCircleImage(TechnicianActivity.this,HttpPath.IMAGEURL+technician.getHead(),logo);
        name.setText(technician.getName());
        String rank=technician.getNickname();
        ranks.setText(rank);
        genius.setText("擅长："+technician.getSpecialty());
    }

    /**
     * 设置店铺信息
     */
    private void setShop(){
        //设置店铺基本信息
        NameUtils.setName(nameView,shopInfo);
        InformationUtils.setInformation(informationView,this,shopInfo);
    }

    /**
     * 设置用户评价
     */
    private void setComment(){
        CommentUtils.getComment(commentView,this,shopInfo.getShopid());
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
        StatusBarUtil.setTranslucentForImageViewInFragment(TechnicianActivity.this, null);
    }
}
