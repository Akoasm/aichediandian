package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.AddressListBean;
import com.sinata.rwxchina.basiclib.commonclass.activity.AddressListActivity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/1/9
 * @describe 商场收货地址工具类
 * @modifyRecord
 */

public class MallPayAddressUtils {
    private Context mC;
    private View view;
    private LinearLayout noAddress;
    private LinearLayout addressLL;
    private TextView name,phone,address;
    public MallPayAddressUtils(Context mC,View view) {
        this.mC = mC;
        this.view=view;
        initView();
    }

    private void initView(){
        noAddress=view.findViewById(R.id.payment_address_linear);
        addressLL=view.findViewById(R.id.mallPayment_address_ll);
        name=view.findViewById(R.id.payment_address_name);
        phone=view.findViewById(R.id.payment_address_phonenumber);
        address=view.findViewById(R.id.payment_address_address);
        addressLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mC, AddressListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type","mall");
                intent.putExtras(bundle);
                mC.startActivity(intent);
            }
        });
        noAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mC, AddressListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type","mall");
                intent.putExtras(bundle);
                mC.startActivity(intent);
            }
        });
    }

    public void getAddress(){
        Map<String,String> params=new HashMap<>();
        ApiManager manager=new ApiManager((BaseActivity) mC, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<AddressListBean> addressListBeen=new Gson().fromJson(result,new TypeToken<List<AddressListBean>>(){}.getType());
                if (addressListBeen!=null&&addressListBeen.size()>0){
                    AddressListBean bean=addressListBeen.get(0);
                    noAddress.setVisibility(View.GONE);
                    addressLL.setVisibility(View.VISIBLE);
                    name.setText(bean.getName());
                    phone.setText(bean.getPhone());
                    address.setText(bean.getAddress());
                    SinglePayment.getSinglePayment().setReceipt_address(bean.getAddress());
                    SinglePayment.getSinglePayment().setReceipt_name(bean.getName());
                    SinglePayment.getSinglePayment().setReceipt_phone(bean.getPhone());
                }else {
                    noAddress.setVisibility(View.VISIBLE);
                    addressLL.setVisibility(View.GONE);
                }
                LogUtils.e("address",result);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.ADDRESSLIST,params);
    }

    /**
     * 设置收货地址
     * @param addressBean
     */
    public void setAddress(AddressListBean addressBean){
        if (addressBean!=null){
            AddressListBean bean=addressBean;
            noAddress.setVisibility(View.GONE);
            addressLL.setVisibility(View.VISIBLE);
            name.setText(bean.getName());
            phone.setText(bean.getPhone());
            address.setText(bean.getAddress());
            SinglePayment.getSinglePayment().setReceipt_address(bean.getAddress());
            SinglePayment.getSinglePayment().setReceipt_name(bean.getName());
            SinglePayment.getSinglePayment().setReceipt_phone(bean.getPhone());
        }
    }
}
