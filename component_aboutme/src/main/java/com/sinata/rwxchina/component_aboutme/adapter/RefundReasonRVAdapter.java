package com.sinata.rwxchina.component_aboutme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class RefundReasonRVAdapter extends RecyclerView.Adapter<RefundReasonRVAdapter.ViewHolder> {
    private BaseActivity baseActivity;
    private List<String> list;
    private List<String> params;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(com.sinata.rwxchina.basiclib.R.layout.item_apply_refund,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public RefundReasonRVAdapter(BaseActivity baseActivity, List<String> list) {
        this.baseActivity = baseActivity;
        this.list = list;
        params = new ArrayList<>();
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
         holder.reason.setText(list.get(position));
         holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked){
                     params.add(list.get(position));
                 }else {
                     params.remove(list.get(position));
                 }
             }
         });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView reason;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            reason = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_apply_refund_account);
            checkBox = itemView.findViewById(com.sinata.rwxchina.basiclib.R.id.item_apply_refund_checkbox);
        }
    }
}
