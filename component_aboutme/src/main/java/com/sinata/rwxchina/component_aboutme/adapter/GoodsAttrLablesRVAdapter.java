package com.sinata.rwxchina.component_aboutme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class GoodsAttrLablesRVAdapter extends RecyclerView.Adapter<GoodsAttrLablesRVAdapter.ViewHolder>{
    private BaseActivity baseActivity;
    private List<String> list;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(com.sinata.rwxchina.basiclib.R.layout.item_goodsattrlabels,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public GoodsAttrLablesRVAdapter(BaseActivity baseActivity, List<String> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.goodsLabels_tv);

        }
    }
}
