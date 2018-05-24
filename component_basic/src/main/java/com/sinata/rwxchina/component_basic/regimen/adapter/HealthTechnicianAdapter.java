package com.sinata.rwxchina.component_basic.regimen.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.regimen.entity.TechnicianEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 养生技师列表适配器
 * @modifyRecord
 */

public class HealthTechnicianAdapter extends BaseQuickAdapter<TechnicianEntity,BaseViewHolder> {
    private Context mC;
    public HealthTechnicianAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<TechnicianEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TechnicianEntity item) {
        //设置技师头像
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getHead(), (ImageView) helper.getView(R.id.yangsheng_recycle_item_img));
        helper.setText(R.id.yangsheng_recycle_item_name,item.getName())
                .setText(R.id.yangsheng_recycle_item_type,item.getNickname());
    }
}
