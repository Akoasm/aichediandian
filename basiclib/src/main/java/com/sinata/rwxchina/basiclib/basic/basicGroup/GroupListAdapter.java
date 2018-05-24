package com.sinata.rwxchina.basiclib.basic.basicGroup;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 店铺套餐列表适配器
 * @modifyRecord
 */

public class GroupListAdapter extends BaseQuickAdapter<GroupEntity,BaseViewHolder> {
    private Context mC;
    public GroupListAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<GroupEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupEntity item) {
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
