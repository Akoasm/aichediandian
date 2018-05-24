package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;

import java.util.List;

/**
 * @author:zy
 * @detetime:2018/1/3
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ShopDetailCommodityAdapter extends RecyclerView.Adapter<ShopDetailCommodityAdapter.ViewHolder> {
    private List<ShopInfoBean.GoodsBean> list;
    private BaseActivity baseActivity;
    private OnItemCilckListener onItemCilckListener;

    public List<ShopInfoBean.GoodsBean> getList() {
        return list;
    }

    public void setList(List<ShopInfoBean.GoodsBean> list) {
        this.list = list;
    }

    public ShopDetailCommodityAdapter(List<ShopInfoBean.GoodsBean> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;

    }

    public OnItemCilckListener getOnItemCilckListener() {
        return onItemCilckListener;
    }

    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }

    public interface OnItemCilckListener {
        void onCilckListener(ViewHolder viewHolder, int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.item_brand_self_shop_commodity, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItemBrandSelfShopCommodityName.setText(list.get(position).getGoods_name());
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL + list.get(position).getDefault_image(), holder.mItemBrandSelfShopCommodityImage);
        holder.mItemBrandTypeTv.setText(list.get(position).getBrand_zh() + list.get(position).getGoods_type_zh());
        holder.mItemBrandSelfShopCommodityPricePre.setText(list.get(position).getGoods_price());
        holder.mItemBrandSelfShopCommodityPriceOri.setText(list.get(position).getGoods_market_price());
        holder.mItemBrandSelfShopCommodityPriceOri.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mItemBrandSelfShopCommodityPriceOri.getPaint().setAntiAlias(true);
        holder.mItemShopCommodityRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemCilckListener!=null)
                    onItemCilckListener.onCilckListener(holder,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemBrandSelfShopCommodityImage;
        private TextView mItemBrandTypeTv;
        private TextView mItemBrandSelfShopCommodityName;
        private TextView mItemBrandSelfShopCommodityPricePre;
        private TextView mItemBrandSelfShopCommodityPriceOri;
        private RelativeLayout mItemShopCommodityRl;




        public ViewHolder(View itemView) {
            super(itemView);
            mItemBrandSelfShopCommodityImage = itemView.findViewById(R.id.item_brand_self_shop_commodity_image);
            mItemBrandTypeTv = itemView.findViewById(R.id.item_brand_type_tv);
            mItemBrandSelfShopCommodityName = itemView.findViewById(R.id.item_brand_self_shop_commodity_name);
            mItemBrandSelfShopCommodityPricePre = itemView.findViewById(R.id.item_brand_self_shop_commodity_price_pre);
            mItemBrandSelfShopCommodityPriceOri = itemView.findViewById(R.id.item_brand_self_shop_commodity_price_ori);
            mItemShopCommodityRl = itemView.findViewById(R.id.item_shopCommodity_rl);
        }
    }
}
