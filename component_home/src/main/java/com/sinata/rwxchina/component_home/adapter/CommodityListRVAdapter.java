package com.sinata.rwxchina.component_home.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityDetailActivity;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class CommodityListRVAdapter extends RecyclerView.Adapter<CommodityListRVAdapter.ViewHolder> {
    private List<CommodityBean> listBeen;
    private BaseActivity baseActivity;

    public CommodityListRVAdapter(List<CommodityBean> listBeen, BaseActivity baseActivity) {
        this.listBeen = listBeen;
        this.baseActivity = baseActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.item_brand_list, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.CommodityName.setText(listBeen.get(position).getGoods_name());
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL+listBeen.get(position).getDefault_image(),holder.CommodityImage);
        holder.CommoditySubTitle.setText(listBeen.get(position).getGoods_subtitle());
        holder.CommodityDescription.setText(listBeen.get(position).getGoods_description());
        holder.CommodityPrice.setText("￥"+listBeen.get(position).getGoods_price());
//        CommodidtyLabelsRVAdapter adapter = new CommodidtyLabelsRVAdapter(listBeen.get(position).getGoods_type_labels(),baseActivity);
//        holder.labels.setLayoutManager(new GridLayoutManager(baseActivity,4));
//        holder.labels.setAdapter(adapter);
        final Bundle bundle = new Bundle();
        bundle.putSerializable("commodity",listBeen.get(position));
        holder.Commodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivity.startActivity(CommodityDetailActivity.class,bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public List<CommodityBean> getListBeen() {
        return listBeen;
    }

    public void setListBeen(List<CommodityBean> listBeen) {
        this.listBeen = listBeen;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CommodityName, CommoditySubTitle, CommodityPrice, CommodityDescription;
        private ImageView CommodityImage;
//        private RecyclerView labels;
        private RelativeLayout Commodity;

        public ViewHolder(View itemView) {
            super(itemView);
            CommodityName = itemView.findViewById(R.id.item_brand_area_list_name);
            CommoditySubTitle = itemView.findViewById(R.id.item_brand_area_list_description);
            CommodityDescription = itemView.findViewById(R.id.item_brand_area_list_remarks);
            CommodityPrice = itemView.findViewById(R.id.item_brand_area_list_price);
//            labels = itemView.findViewById(R.id.item_brand_area_list_label);
            Commodity = itemView.findViewById(R.id.item_commodity_ll);
            CommodityImage = itemView.findViewById(R.id.item_brand_area_list_image);
        }
    }
}
