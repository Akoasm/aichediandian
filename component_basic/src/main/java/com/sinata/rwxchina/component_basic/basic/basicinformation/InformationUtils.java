package com.sinata.rwxchina.component_basic.basic.basicinformation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopMapActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.component_basic.R;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe 商铺定位及拨打电话工具类
 * @modifyRecord
 */

public class InformationUtils {
    /**
     * 设置店铺定位及拨打电话
     * @param view
     * @param context
     * @param shopInfo
     */
    public static void setInformation(View view, final Context context, final BaseShopInfo shopInfo){
        TextView address=view.findViewById(R.id.health_address);
        ImageView phone=view.findViewById(R.id.health_phone);
        LinearLayout address_linear = view.findViewById(R.id.shopInfo_address_ll);
        final String phoneNumber=shopInfo.getShop_telephone();
        address.setText(shopInfo.getShop_address());
        address_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shop=new Gson().toJson(shopInfo);
                Intent goMap=new Intent(context, BasicShopMapActivity.class);
                goMap.putExtra("shopInfo",shop);
                context.startActivity(goMap);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPhoneUtils.call(context,phoneNumber);
            }
        });
    }
}
