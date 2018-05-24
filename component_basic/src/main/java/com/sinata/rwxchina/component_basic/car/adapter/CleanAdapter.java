package com.sinata.rwxchina.component_basic.car.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.car.entity.CarGoodsInfo;
import com.sinata.rwxchina.component_basic.car.entity.Clean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author HRR
 * @datetime 2017/11/14
 * @describe
 * @modifyRecord
 */

public class CleanAdapter extends RecyclerView.Adapter<CleanAdapter.SimpleHolder> {
    private Context context;
    private LayoutInflater mInflater;
    private OnItemActionListener mOnItemActionListener;
    private List<CarGoodsInfo> list;
    private boolean showdescription;
    public CleanAdapter(Context context,boolean showdescription) {
        this.context = context;
        this.list = new ArrayList<CarGoodsInfo>();
        this.mInflater = LayoutInflater.from(context);
        this.showdescription=showdescription;
    }

    public void setList(List<CarGoodsInfo> list) {
        this.list = list;
    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_clean,null,false);
        SimpleHolder holder = new SimpleHolder(v);
        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SimpleHolder holder, final int position) {
        holder.goods_name.setText(list.get(position).getGoods_name());
        holder.price.setText(list.get(position).getGoods_price());
        holder.tog.setChecked(list.get(position).isCheck());
        holder.market_price.setText("￥"+list.get(position).getGoods_market_price());
        holder.market_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG| Paint.ANTI_ALIAS_FLAG);
        if(position%2!=0){
            holder.view1.setVisibility(View.GONE);
            holder.view2.setVisibility(View.VISIBLE);
        }else {
            holder.view1.setVisibility(View.VISIBLE);
            holder.view2.setVisibility(View.GONE);
        }


        if(showdescription){
            if(list.get(position).isCheck()){
                String description=list.get(position).getGoods_description();
                if (!TextUtils.isEmpty(description)){
                    holder.clean_description.setVisibility(View.VISIBLE);
                    holder.clean_description.setText(list.get(position).getGoods_description());
                }
            }else {
                holder.clean_description.setVisibility(View.GONE);
            }
        }
        if (mOnItemActionListener != null){
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemActionListener.onItemClickListener(v,holder.getPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemActionListener{
        void onItemClickListener(View v, int pos);
    }
    public void setOnItemActionListener(OnItemActionListener onItemActionListener){
        this.mOnItemActionListener = onItemActionListener;
    }

    public class SimpleHolder extends RecyclerView.ViewHolder {
        public TextView goods_name;
        public TextView market_price;
        public TextView price;
        public ToggleButton tog;
        public LinearLayout ll;
        public TextView clean_description;
        public View view1;
        public View view2;

        public SimpleHolder(View itemView) {
            super(itemView);
            goods_name=itemView.findViewById(R.id.clean_goods_name);
            price=itemView.findViewById(R.id.clean_price);
            market_price=itemView.findViewById(R.id.clean_market_price);
            tog=itemView.findViewById(R.id.clean_tog);
            ll=itemView.findViewById(R.id.clean_item_ll);
            clean_description= itemView.findViewById(R.id.clean_description);
            view1=itemView.findViewById(R.id.clean_view1);
            view2=itemView.findViewById(R.id.clean_view2);
        }
    }
}
