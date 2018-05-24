package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.IconMoreEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/29
 * @describe：周边查询适配器
 * @modifyRecord:
 */


public class PerimeterAdapter extends RecyclerView.Adapter<PerimeterAdapter.PerimeterViewHolder> {

    private Context mC;
    private List<IconMoreEntity.ListBean> list;
    private LayoutInflater layoutInflater;
    private OnItemActionListener mOnItemActionListener;
    public interface OnItemActionListener{
        void onItemClickListener(View v, int pos,String key);
    }

    public PerimeterAdapter(Context mC) {
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
    public PerimeterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewOne = layoutInflater.inflate(R.layout.item_icon_more_perimeter, parent, false);
        PerimeterViewHolder perimeterViewHolder = new PerimeterViewHolder(viewOne);
        return perimeterViewHolder;

    }

    @Override
    public void onBindViewHolder(PerimeterViewHolder holder, final int position) {
        final PerimeterViewHolder perimeterViewHolder = (PerimeterViewHolder) holder;
        perimeterViewHolder.name.setText(list.get(position).getTitle());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+list.get(position).getImg(),perimeterViewHolder.image);
        perimeterViewHolder.perimeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemActionListener.onItemClickListener(v,perimeterViewHolder.getPosition(),list.get(position).getTitle());
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public OnItemActionListener getmOnItemActionListener() {
        return mOnItemActionListener;
    }

    public void setmOnItemActionListener(OnItemActionListener mOnItemActionListener) {
        this.mOnItemActionListener = mOnItemActionListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PerimeterViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;
        private LinearLayout perimeter;

        public PerimeterViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_icon_more_perimeter_tv);
            image = itemView.findViewById(R.id.item_icon_more_perimeter_img);
            perimeter = itemView.findViewById(R.id.item_icon_more_perimeter_linear);
        }
    }
}
