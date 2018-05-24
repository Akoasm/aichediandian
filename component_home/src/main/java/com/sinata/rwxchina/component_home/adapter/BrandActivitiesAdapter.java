package com.sinata.rwxchina.component_home.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.InsuranceWebViewActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.ShareWebActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.webViewUtils.activity.DefaultWebViewActivity;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.HotEntity;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BrandActivitiesAdapter extends RecyclerView.Adapter<BrandActivitiesAdapter.ViewHolder> {
    private List<HotEntity> list;
    private BaseActivity baseActivity;
    private String uid;

    public BrandActivitiesAdapter(List<HotEntity> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
        uid = CommonParametersUtils.getUid(baseActivity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(baseActivity).inflate(R.layout.item_brandactivities,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Bundle bundle = new Bundle();
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL+list.get(position).getUrlsmall(),holder.Image);
        holder.textView.setText(list.get(position).getTitle());
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否需要登录 1：需要 0：不需要
                if ("1".equals(list.get(position).getIs_must_login())) {
                    if (!UserUtils.isLogin(baseActivity)) {
                        return;
                    }
                }
                if ("预约审车".equals(list.get(position).getTitle())){
                    Intent intent=new Intent(baseActivity,InsuranceWebViewActivity.class);
                    Bundle shenche=new Bundle();
                    shenche.putString("url", list.get(0).getUrl());
                    if (AppUtils.isLogin(baseActivity)){
                        shenche.putString("uid", CommonParametersUtils.getUid(baseActivity));
                    }
                    shenche.putString("token", CommonParametersUtils.getToken(baseActivity));
                    intent.putExtras(shenche);
                    baseActivity.startActivity(intent);
                    return;
                }


                bundle.putString("url",list.get(position).getUrl());
                if (list.get(position).getIs_share().equals("1")){
                    bundle.putString("share_title",list.get(position).getShare_name());
                    bundle.putString("content",list.get(position).getShare_content());
                    bundle.putString("url_share",list.get(position).getUrl_share());
                    bundle.putString("uid",CommonParametersUtils.getUid(baseActivity));
                    bundle.putBoolean("isTitleShare",true);
                    baseActivity.startActivity(ShareWebActivity.class,bundle);
                }else {
                    if (list.get(position).getIs_head().equals("1"))
                        bundle.putBoolean("isTitle",true);
                    baseActivity.startActivity(DefaultWebViewActivity.class,bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<HotEntity> getList() {
        return list;
    }

    public void setList(List<HotEntity> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Image;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.item_brand_iv);
            textView = itemView.findViewById(R.id.item_brand_tv);

        }
    }
}
