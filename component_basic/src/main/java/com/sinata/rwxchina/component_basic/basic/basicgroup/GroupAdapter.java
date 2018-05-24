package com.sinata.rwxchina.component_basic.basic.basicgroup;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 套餐适配器
 * @modifyRecord
 */

public class GroupAdapter extends BaseQuickAdapter<BaseGoodsInfo,BaseViewHolder> {
    private Context mC;
    public GroupAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<BaseGoodsInfo> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseGoodsInfo item) {
        //设置中划线
        TextviewUtils.setStrikethrough((TextView) helper.getView(R.id.group_item_goods_market_price));
        //设置套餐图片
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getDefault_image(), (ImageView) helper.getView(R.id.food_item_goods_img));
        //暂时隐藏已售份数
        helper.setVisible(R.id.group_item_sold,false);
        helper.setText(R.id.food_item_goods_name,item.getGoods_name())
                .setText(R.id.group_item_goods_price,"￥"+item.getGoods_price()+"/"+item.getGoods_unit())
                .setText(R.id.group_item_goods_market_price,"￥"+item.getGoods_market_price()+"/"+item.getGoods_unit())
                .setText(R.id.group_item_sold,"已售"+item.getGoods_sale()+"份");
    }
}
