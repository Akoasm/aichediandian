package com.sinata.rwxchina.component_basic.hotel.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.hotel.activity.HotelActivity;
import com.sinata.rwxchina.component_basic.hotel.entity.HotelReserveEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 酒店房间类型适配器
 * @modifyRecord
 */

public class HotelTypeAdapter extends BaseQuickAdapter<HotelReserveEntity,BaseViewHolder> {
    private Context mC;
    private TextView textView;
    public HotelTypeAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<HotelReserveEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotelReserveEntity item) {
        helper.setText(R.id.item_ktvtype,item.getTitle());
        if (helper.getPosition()==0){
            textView =helper.getView(R.id.item_ktvtype);
            textView.setBackgroundDrawable(mC.getResources().getDrawable(R.drawable.right_angle_border_orange));
        }
    }

    public TextView getView(){
        return textView;
    }


}
