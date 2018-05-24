package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class CommodityIamgeRVAdapter  extends RecyclerView.Adapter<CommodityIamgeRVAdapter.ViewHolder>{
    private BaseActivity baseActivity;
    private List<String> list;

    public CommodityIamgeRVAdapter(BaseActivity baseActivity, List<String> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(baseActivity).inflate(R.layout.item_comomditydetail_img,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL+list.get(position),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_commodityImg);
        }
    }
}
