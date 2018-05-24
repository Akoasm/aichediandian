package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopMapActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.basiclib.commonclass.activity.InsuranceWebViewActivity;
import com.sinata.rwxchina.basiclib.view.alertdialog.AlertDialog;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.InsuranceAdapter;
import com.sinata.rwxchina.component_home.entity.InsuranceDetailsEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：
 * @modifyRecord:
 */


public class InsuranceDetailsActivity extends BaseActivity {
    //实体类
    private InsuranceDetailsEntity detailsEntity;
    private TextView title, name, address, phone, reminder;
    private ImageView logo, back;
    private RecyclerView serviceRecycler;
    private Button insurance_car, insurance_life;
    private String shopId;
    private InsuranceAdapter insuranceAdapter;
    private View statusBar;
    private String isReady;
    private Map<String, String> param = new HashMap<>();

    @Override
    public void initParms(Bundle params) {
        shopId = params.getString("id");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_aotu_insurance_details;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        title = view.findViewById(R.id.food_comment_title_tv);
        name = view.findViewById(R.id.insurance_details_name);
        address = view.findViewById(R.id.insurance_details_address);
        phone = view.findViewById(R.id.insurance_details_tel);
        reminder = view.findViewById(R.id.aotu_insurance_reminder_tv);
        back = view.findViewById(R.id.food_comment_back);
        logo = view.findViewById(R.id.insurance_details_photo);
        serviceRecycler = view.findViewById(R.id.aotu_insurance_details_recycler);
        serviceRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        insurance_car = view.findViewById(R.id.aotu_insurance_details_car);
        insurance_life = view.findViewById(R.id.aotu_insurance_details_life);
        statusBar = findViewById(R.id.normal_fakeview);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        insurance_car.setOnClickListener(this);
        insurance_life.setOnClickListener(this);
        phone.setOnClickListener(this);
        address.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if (v.getId() == R.id.food_comment_back) {
            finish();
        } else if (v.getId() == R.id.aotu_insurance_details_car) {
            ApiManager apiManager = new ApiManager(this, new StringCallBack() {
                @Override
                public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    final JSONObject jsonObject = new JSONObject(result);
                    isReady = jsonObject.optString("is_ready");
                    String isAgent = jsonObject.optString("is_agent");
                    if (isAgent.equals("1")) {
                        if (isReady.equals("1")) {
                            goWeb(jsonObject.optString("url"), true);
                        } else {
                            goWeb(HttpPath.WEB_COMPLETEAGENTINFO, false);
                        }
                    } else {
                        AlertDialog alertDialog = new AlertDialog(InsuranceDetailsActivity.this);
                        alertDialog.builder()
                                .setTitle("是否成为保险代理")
                                .setMsg("成为代理将有资格提取手续费佣金")
                                .setNegativeButton("不了,继续购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        goWeb(jsonObject.optString("url"), true);
                                    }
                                })
                                .setPositiveButton("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        goWeb(HttpPath.WEB_INSURANCE, false);
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onResultError(ApiException e, String method) {

                }
            });
            if (UserUtils.isLogin(InsuranceDetailsActivity.this)) {
                apiManager.get(HttpPath.BUYINSURANCE, param);
            }
        } else if (v.getId() == R.id.aotu_insurance_details_life) {
            ApiManager apiManager = new ApiManager(this, new StringCallBack() {
                @Override
                public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    final JSONObject jsonObject = new JSONObject(result);
                    isReady = jsonObject.optString("is_ready");
                    String isAgent = jsonObject.optString("is_agent");
                    if (isAgent.equals("1")) {
                        if (isReady.equals("1")) {
                            goWeb(jsonObject.optString("url"), true);
                        } else {
                            goWeb(HttpPath.WEB_COMPLETEAGENTINFO, false);
                        }
                    } else {
                        AlertDialog alertDialog = new AlertDialog(InsuranceDetailsActivity.this);
                        alertDialog.builder()
                                .setTitle("是否成为保险代理")
                                .setMsg("成为代理将有资格提取手续费佣金")
                                .setNegativeButton("不了,继续购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        goWeb(jsonObject.optString("url"), true);
                                    }
                                })
                                .setPositiveButton("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        goWeb(HttpPath.WEB_INSURANCE, false);
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onResultError(ApiException e, String method) {

                }
            });
            if (UserUtils.isLogin(InsuranceDetailsActivity.this)) {
                apiManager.get(HttpPath.BUYLIFEINSURANCE, param);
            }
        } else if (v.getId() == R.id.insurance_details_tel) {
            CallPhoneUtils.call(this, detailsEntity.getTel());
        }else if(v.getId() == R.id.insurance_details_address){
            Bundle bundle = new Bundle();
            bundle.putString("lat",detailsEntity.getMap_lat());
            bundle.putString("lng",detailsEntity.getMap_lng());
            bundle.putString("address",detailsEntity.getAddr());
            bundle.putString("name",detailsEntity.getFullname());
            bundle.putString("logo",detailsEntity.getLogo());
            bundle.putBoolean("isInsurance",true);
            startActivity(BasicShopMapActivity.class,bundle);
        }
    }


    private void goWeb(String url, boolean isBuy) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isBuy", isBuy);
        bundle.putString("url", url);
        bundle.putString("uid", CommonParametersUtils.getUid(InsuranceDetailsActivity.this));
        bundle.putString("token", CommonParametersUtils.getToken(InsuranceDetailsActivity.this));
        startActivity(InsuranceWebViewActivity.class, bundle);
    }

    @Override
    public void doBusiness(Context mContext) {
        getData();
        setTitleBarView();
    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("id", shopId);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                detailsEntity = gson.fromJson(result, InsuranceDetailsEntity.class);
                setName();
                setList();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.INSURANCE, params);

    }

    /**
     * 设置顶部基础信息
     */
    private void setName() {
        name.setText(detailsEntity.getFullname());
        address.setText(detailsEntity.getAddr());
        phone.setText(detailsEntity.getTel());
        reminder.setText(detailsEntity.getInformation());
        title.setText(detailsEntity.getName());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + detailsEntity.getLogo(), logo);
    }

    /**
     * 设置列表
     */
    private void setList() {
        List<InsuranceDetailsEntity.ServiceListBean> serviceListBean = detailsEntity.getService_list();
        insuranceAdapter = new InsuranceAdapter(R.layout.item_aotu_insurance_details_recycler, serviceListBean);
        serviceRecycler.setAdapter(insuranceAdapter);
        serviceRecycler.addItemDecoration(new DividerRecyclerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.background)));

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
        StatusBarUtil.setTranslucentForImageViewInFragment(InsuranceDetailsActivity.this, null);
    }
}
