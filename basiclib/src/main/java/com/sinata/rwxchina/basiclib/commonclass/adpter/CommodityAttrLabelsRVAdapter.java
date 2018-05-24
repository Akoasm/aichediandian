package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;


import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class CommodityAttrLabelsRVAdapter extends RecyclerView.Adapter<CommodityAttrLabelsRVAdapter.ViewHolder>{
    private List<String> list;
    private BaseActivity baseActivity;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.item_commodity_attr_labels,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CommodityAttrLabelsRVAdapter(List<String> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.label.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView label;
        public ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.goodsLabels_tv);
        }
    }
}
