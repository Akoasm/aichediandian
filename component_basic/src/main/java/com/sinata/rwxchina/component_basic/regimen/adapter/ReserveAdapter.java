package com.sinata.rwxchina.component_basic.regimen.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.regimen.entity.ReserveEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 养生预定列表适配器
 * @modifyRecord
 */

public class ReserveAdapter extends BaseQuickAdapter<BaseGoodsInfo,BaseViewHolder>{
    public ReserveAdapter(@LayoutRes int layoutResId, @Nullable List<BaseGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseGoodsInfo item) {
        //优惠价
        String price=item.getGoods_price();
        //原价
        String market_price=item.getGoods_market_price();
        String subTitle=item.getGoods_subtitle();
//        if (TextUtils.isEmpty(subTitle)){
//            helper.setVisible(R.id.item_health_reserve_firstTime,false);
//        }else {
//            helper.setVisible(R.id.item_health_reserve_firstTime,true);
//        }
        TextView textView=helper.getView(R.id.item_health_reserve_originalPrice);
        //设置中划线
        TextviewUtils.setStrikethrough(textView);
        helper.setText(R.id.item_health_reserve_name,item.getGoods_name())
                .setText(R.id.item_health_reserve_time,item.getGoods_service_time()+"分钟")
                .setText(R.id.item_health_reserve_firstTime,item.getGoods_subtitle())
                .setText(R.id.item_health_reserve_presentPrice,"￥"+price)
                .setText(R.id.item_health_reserve_originalPrice,"￥"+market_price);
        Button button=helper.getView(R.id.item_health_reserve_btn);
        button.setClickable(false);
    }
}
