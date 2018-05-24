package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.IconMoreEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：精品服务和娱乐消费适配器
 * @modifyRecord:
 */


public class IconMoreDetailsAdapter extends RecyclerView.Adapter<IconMoreDetailsAdapter.ServiceViewHolder> {

    private Context mC;
    private List<IconMoreEntity.ListBean> list;
    private LayoutInflater layoutInflater;
    private OnItemActionListener mOnItemActionListener;

    public IconMoreDetailsAdapter(Context mC) {
        this.mC = mC;
        this.list = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(mC);
    }

    public List<IconMoreEntity.ListBean> getList() {
        return list;
    }

    public void setList(List<IconMoreEntity.ListBean> list) {
        this.list = list;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View viewtwo = layoutInflater.inflate(R.layout.item_label, parent, false);
            ServiceViewHolder serviceViewHolder = new ServiceViewHolder(viewtwo);
            return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        final ServiceViewHolder serviceViewHolder = (ServiceViewHolder) holder;
        serviceViewHolder.servicename.setText(list.get(position).getTitle());
        serviceViewHolder.servicename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemActionListener.onItemClickListener(v,serviceViewHolder.getPosition());
            }
        });
    }

    public interface OnItemActionListener{
        void onItemClickListener(View v, int pos);
    }
    public void setOnItemActionListener(OnItemActionListener onItemActionListener){
        this.mOnItemActionListener = onItemActionListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        private TextView servicename;


        public ServiceViewHolder(View itemView) {
            super(itemView);
            servicename = itemView.findViewById(R.id.classify);
        }
    }


}
