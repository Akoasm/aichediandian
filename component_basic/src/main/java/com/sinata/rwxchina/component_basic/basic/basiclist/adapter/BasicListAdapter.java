package com.sinata.rwxchina.component_basic.basic.basiclist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/22
 * @describe 娱乐商铺列表适配器
 * @modifyRecord
 */

public class BasicListAdapter extends BaseQuickAdapter<BaseShopInfo, BaseViewHolder> {
    private Context mC;

    public BasicListAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<BaseShopInfo> data) {
        super(layoutResId, data);
        this.mC = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseShopInfo item) {
        String star = item.getShop_starlevel();
        ImageUtils.showImage(mC, HttpPath.IMAGEURL + item.getShop_logo(), (ImageView) helper.getView(R.id.item_entertainment_list_image));
        helper.setText(R.id.item_entertainment_list_name, item.getShop_name())
                .setText(R.id.item_entertainment_list_ratingnum, "（" + star + "分）")
                .setText(R.id.item_entertainment_list_address, item.getShop_address())
                .setText(R.id.item_entertainment_list_miles, item.getDistance() + "km");
        //美食显示人均价格，其他显示最低价
        if ("3".equals(item.getShop_type())) {
            helper.setText(R.id.item_entertainment_list_RMB, item.getShop_people_avgmoney());
            helper.getView(R.id.item_entertainment_list_avg).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_entertainment_list_price, "元");
            helper.setTextColor(R.id.item_entertainment_list_price, Color.parseColor("#ff720a"));
        } else {
            helper.getView(R.id.item_entertainment_list_avg).setVisibility(View.GONE);
            helper.setText(R.id.item_entertainment_list_RMB, "￥" + item.getShop_goodsmoney_min());
            helper.getView(R.id.item_entertainment_list_price).setVisibility(View.VISIBLE);
        }
        //设置评论星
        RatingBar bar = helper.getView(R.id.item_entertainment_list_rating);
        if (TextUtils.isEmpty(star)) {
            star = "0";
        }
        bar.setRating(Float.parseFloat(star));
        String tuan = item.getShop_tuan().replace(",", "  ");
        String quan = item.getShop_juan().replace(",", "  ");
        LinearLayout quanll = helper.getView(R.id.item_entertianment_list_quan);
        LinearLayout groupll = helper.getView(R.id.item_entertainment_list_group);
        //判断团购是否为空
        if (TextUtils.isEmpty(tuan)) {
            groupll.setVisibility(View.GONE);
        } else {
            groupll.setVisibility(View.VISIBLE);
            helper.setText(R.id.item_entertainment_list_group_tv, tuan);
        }
        //判断代金券是否为空
        if (TextUtils.isEmpty(quan)) {
            quanll.setVisibility(View.GONE);
        } else {
            quanll.setVisibility(View.VISIBLE);
            helper.setText(R.id.item_entertainment_list_voucher, quan);
        }
        //没有团购和代金券
        if (TextUtils.isEmpty(tuan) && TextUtils.isEmpty(quan)) {
            helper.getView(R.id.item_entertianment_list_line).setVisibility(View.GONE);
            helper.getView(R.id.item_entertianment_service).setVisibility(View.GONE);
        }
    }
}
