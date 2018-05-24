package com.sinata.rwxchina.component_aboutme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class PackageItemRVAdapter extends RecyclerView.Adapter<PackageItemRVAdapter.ViewHolder> {
    private BaseActivity baseActivity;
    private List<OrderDetailBean.GoodsGroupDataBean.MeunListBean> list;

    public PackageItemRVAdapter(BaseActivity baseActivity, List<OrderDetailBean.GoodsGroupDataBean.MeunListBean> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(com.sinata.rwxchina.basiclib.R.layout.item_basic_package_content, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getM_goods_name());
        holder.number.setText(list.get(position).getM_goods_number());
        holder.price.setText(list.get(position).getM_goods_money());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, number, price;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_package_name);
            number = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_package_num);
            price = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_package_money);
        }
    }
}
