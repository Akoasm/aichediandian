package com.sinata.rwxchina.component_home.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.Bean.BrandBean;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.activity.CommodityListActivity;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BrandListRVAdapter extends RecyclerView.Adapter<BrandListRVAdapter.ViewHolder> {
    private List<BrandBean> list;
    private BaseActivity baseActivity;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.item_brand_area, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public BrandListRVAdapter(List<BrandBean> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getBrand_name());
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL+list.get(position).getBrand_logo(),holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("brand",list.get(position));
                baseActivity.startActivity(CommodityListActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_brand_area_image);
            textView = itemView.findViewById(R.id.item_brand_area_name);
            linearLayout = itemView.findViewById(R.id.item_brand_ll);
        }
    }
}
