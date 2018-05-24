package com.sinata.rwxchina.component_find.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sinata.rwxchina.component_find.Entity.InformationBean;
import com.sinata.rwxchina.component_find.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2017/11/15
 * @describe：
 * @modifyRecord:
 */


public class InformationsAdapter extends RecyclerView.Adapter<InformationsAdapter.MyViewHolder> {
    private List<InformationBean> list;
    private Context mC;
    private OnItemActionListener mOnItemActionListener;

    public InformationsAdapter( Context mC) {
        this.list = new ArrayList<>();
        this.mC = mC;
    }

    public List<InformationBean> getList() {
        return list;
    }

    public void setList(List<InformationBean> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mC).inflate(R.layout.information_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.item_title.setText(list.get(position).getTitle());
        holder.item_context.setText(list.get(position).getContent());
        holder.item_source.setText(list.get(position).getSrc());
        holder.item_time.setText(list.get(position).getTime());
        Glide.with(mC).load(list.get(position).getPic()).into(holder.item_img);
        if (mOnItemActionListener != null){
            holder.item_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemActionListener.onItemClickListener(v,holder.getPosition());
                }
            });
        }
    }


    public interface OnItemActionListener{
        void onItemClickListener(View v, int pos);
    }

    public void setmOnItemActionListener(OnItemActionListener mOnItemActionListener) {
        this.mOnItemActionListener = mOnItemActionListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_title, item_context, item_time, item_source;
        ImageView item_img;
        LinearLayout item_linear;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_source = itemView.findViewById(R.id.information_item_source);
            item_context = itemView.findViewById(R.id.information_item_context);
            item_img = itemView.findViewById(R.id.information_item_img);
            item_time = itemView.findViewById(R.id.information_item_time);
            item_title = itemView.findViewById(R.id.information_item_title);
            item_linear = itemView.findViewById(R.id.information_item_linear);
        }
    }
}
