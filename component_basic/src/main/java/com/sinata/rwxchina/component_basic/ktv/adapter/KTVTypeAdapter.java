package com.sinata.rwxchina.component_basic.ktv.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.ktv.entity.KTVReserveEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe KTV预约包间适配器
 * @modifyRecord
 */

public class KTVTypeAdapter extends BaseQuickAdapter<KTVReserveEntity,BaseViewHolder> {
    private Context mC;
    public KTVTypeAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<KTVReserveEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, KTVReserveEntity item) {
        helper.setText(R.id.item_ktvtype,item.getTitle());
    }
}
