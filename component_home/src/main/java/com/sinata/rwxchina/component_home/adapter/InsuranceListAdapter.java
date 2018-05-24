package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.InsuanceListEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：
 * @modifyRecord:
 */


public class InsuranceListAdapter extends BaseQuickAdapter<InsuanceListEntity.ListBean,BaseViewHolder> {

    private Context mC;

    public InsuranceListAdapter(@LayoutRes int layoutResId, @Nullable List<InsuanceListEntity.ListBean> data,Context context) {
        super(layoutResId, data);
        this.mC = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuanceListEntity.ListBean item) {
        helper.setText(R.id.item_aotu_insurance_list_name,item.getName())
                .setText(R.id.item_aotu_insurance_list_description,item.getIntro());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getLogo(), (ImageView) helper.getView(R.id.item_aotu_insurance_list_logo));
    }
}
