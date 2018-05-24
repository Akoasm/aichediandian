package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2018/1/3
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ShopInfoTypeRVAdapter extends RecyclerView.Adapter<ShopInfoTypeRVAdapter.ViewHolder> {
    private List<ShopInfoBean.GoodsTypeListBean> list;
    private BaseActivity baseActivity;
    private OnItemCilckListener onItemCilckListener;
    private List<Boolean> checkedLsit;
    private List<ViewHolder> viewHolderList;


    public interface OnItemCilckListener {
        void onCilckListener(ViewHolder viewHolder, int position, boolean isChecked);
    }

    public ShopInfoTypeRVAdapter(List<ShopInfoBean.GoodsTypeListBean> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
        checkedLsit = new ArrayList<>();
        viewHolderList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.item_shopdetail_leftlist, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolderList.add(viewHolder);
        return viewHolder;
    }

    public List<ShopInfoBean.GoodsTypeListBean> getList() {
        return list;
    }

    public OnItemCilckListener getOnItemCilckListener() {
        return onItemCilckListener;
    }

    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }

    public void setList(List<ShopInfoBean.GoodsTypeListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            holder.textView.setText("全部类型");
        } else {
            holder.textView.setText(list.get(position - 1).getGoods_type_zh());
        }
        holder.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (checkedLsit.get(i) && i!= position) {
                        viewHolderList.get(i).textView.setBackgroundResource(R.drawable.right_angle_border);
                        viewHolderList.get(i).textView.setTextColor(ContextCompat.getColor(baseActivity, R.color.colorGray));
                        checkedLsit.set(i, false);
                    }
                }
                if (checkedLsit.get(position)) {
                    holder.textView.setBackgroundResource(R.drawable.right_angle_border);
                    holder.textView.setTextColor(ContextCompat.getColor(baseActivity, R.color.colorGray));
                    checkedLsit.set(position, false);
                } else {
                    holder.textView.setBackgroundResource(R.color.background);
                    holder.textView.setTextColor(ContextCompat.getColor(baseActivity, R.color.text_hint));
                    checkedLsit.set(position, true);
                }
                if (onItemCilckListener != null)
                    onItemCilckListener.onCilckListener(holder, position, checkedLsit.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        for (int i = 0; i < list.size(); i++) {
            checkedLsit.add(false);
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_typeName_tv);
        }
    }
}
