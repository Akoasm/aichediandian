package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.commonclass.Bean.AddressListBean;
import com.sinata.rwxchina.basiclib.commonclass.activity.AddressListActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.EditAddressActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2018/1/8
 * @describe：
 * @modifyRecord:
 */


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements View.OnClickListener{

    private Context mC;
    private List<AddressListBean> list;
    private LayoutInflater layoutInflater;
    private OnItemActionListener mOnItemActionListener;
    private CheckChange mCheckChange;
    private OnClickListener mOnClickListener;
    private boolean onBind;

    public AddressListAdapter(Context mC) {
        this.mC = mC;
        this.list = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(mC);

    }

    public List<AddressListBean> getList() {
        return list;
    }

    public void setList(List<AddressListBean> list) {
        this.list = list;
    }

    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_manage_address, parent, false);
        AddressListViewHolder viewHolder = new AddressListViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressListViewHolder holder, final int position) {
        holder.itemView.setTag(list.get(position));
        final AddressListViewHolder viewHolder = holder;
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.phone.setText(list.get(position).getPhone());
        viewHolder.address.setText(list.get(position).getRegion()+list.get(position).getAddress());
        onBind = true;
        if (list.get(position).getIs_mo().equals("1")) {
            viewHolder.checkBox.setChecked(true);
        } else {
            viewHolder.checkBox.setChecked(false);
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!onBind) {
                    mCheckChange.check(position, isChecked);
                }
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemActionListener.onItemClickListener(v, viewHolder.getPosition());
            }
        });
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemActionListener.onItemClickListener(v, viewHolder.getPosition());
            }
        });

        onBind = false;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener!=null){
            mOnClickListener.onItemClickListener(v, (AddressListBean) v.getTag());
        }
    }


    public interface CheckChange {
        void check(int position, boolean flag);
    }

    public CheckChange getmCheckChange() {
        return mCheckChange;
    }

    public void setmCheckChange(CheckChange mCheckChange) {
        this.mCheckChange = mCheckChange;
    }

    public interface OnClickListener{
        void onItemClickListener(View v, AddressListBean address);
    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.mOnClickListener=onClickListener;
    }

    public interface OnItemActionListener {
        void onItemClickListener(View v, int pos);
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {

        private TextView name, phone, address;
        private CheckBox checkBox;
        private LinearLayout edit, delete;

        public AddressListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shipping_address_name);
            phone = itemView.findViewById(R.id.shipping_address_phone);
            address = itemView.findViewById(R.id.shipping_address_address);
            checkBox = itemView.findViewById(R.id.shipping_address_checkbox);
            edit = itemView.findViewById(R.id.shipping_address_edit_linear);
            delete = itemView.findViewById(R.id.shipping_address_delete_linear);
        }
    }

}
