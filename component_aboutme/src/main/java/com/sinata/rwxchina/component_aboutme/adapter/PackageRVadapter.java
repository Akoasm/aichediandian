package com.sinata.rwxchina.component_aboutme.adapter;

import android.support.v7.widget.LinearLayoutManager;
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

public class PackageRVadapter extends RecyclerView.Adapter<PackageRVadapter.ViewHolder> {
    private BaseActivity baseActivity;
    private List<OrderDetailBean.GoodsGroupDataBean> list;

    public PackageRVadapter(BaseActivity baseActivity,  List<OrderDetailBean.GoodsGroupDataBean> map) {
        this.baseActivity = baseActivity;
        this.list = map;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(com.sinata.rwxchina.basiclib.R.layout.item_basic_package_title, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          holder.packageName.setText(list.get(position).getM_meun_name());
          PackageItemRVAdapter packageItemRVAdapter  =new PackageItemRVAdapter(baseActivity,list.get(position).getMeun_list());
          holder.recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
          holder.recyclerView.setAdapter(packageItemRVAdapter);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView packageName;
        private RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            packageName = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_package_title);
            recyclerView = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_package_recycle);
        }
    }
}
