package com.sinata.rwxchina.component_aboutme.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.InsuranceWebViewActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.ShareWebActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.webViewUtils.activity.DefaultWebViewActivity;
import com.sinata.rwxchina.component_aboutme.bean.AboutMeFragmentFunctionBean;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/11/21
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class FunctionRVAdapter extends RecyclerView.Adapter<FunctionRVAdapter.ViewHolder> {
    private BaseActivity context;
    private List<AboutMeFragmentFunctionBean> list;
    private OnItemCilckListener onItemCilckListener;

    public OnItemCilckListener getOnItemCilckListener() {
        return onItemCilckListener;
    }

    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }

    public interface OnItemCilckListener {
        void onItemCilckListener(View view, int postion);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fagment_aboutme_gv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public FunctionRVAdapter(BaseActivity context, List<AboutMeFragmentFunctionBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ImageUtils.showImage(context, HttpPath.IMAGEURL + list.get(position).getImg(), holder.icon);
        holder.textView.setText(list.get(position).getTitle());
        final Bundle bundle = new Bundle();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIs_url().equals("1")) {
                    //判断是否需要登录 1：需要 0：不需要
                    if ("1".equals(list.get(position).getIs_must_login())){
                        if (!UserUtils.isLogin(context)){
                            return;
                        }
                    }
                    bundle.putString("url", list.get(position).getUrl());
                    switch (list.get(position).getTitle()){
                        case "我的爱车":
                        case "我的保单":
                        case "我的分享":
                            if (AppUtils.isLogin(context)){
                                    LogUtils.e("FunctionRVAdapter","isShare="+list.get(position).getIs_share());
                                    if ("1".equals(list.get(position).getIs_share())){
                                            bundle.putString("content", list.get(position).getShare_content());
                                            bundle.putString("share_title", list.get(position).getShare_title());
                                            bundle.putString("url_share", list.get(position).getShare_url());
                                            bundle.putString("uid", CommonParametersUtils.getUid(context));
                                            context.startActivity(ShareWebActivity.class,bundle);
                                    }else {
                                            bundle.putBoolean("isTitle",false);
                                            context.startActivity(DefaultWebViewActivity.class, bundle);
                                    }
                            }
                            break;
                        case "审车记录":
                            Intent intent=new Intent(context,InsuranceWebViewActivity.class);
                            Bundle shenche=new Bundle();
                            shenche.putString("url", list.get(position).getUrl());
                            if (AppUtils.isLogin( context)){
                                shenche.putString("uid", CommonParametersUtils.getUid(context));
                            }
                            shenche.putString("token", CommonParametersUtils.getToken(context));
                            intent.putExtras(shenche);
                            context.startActivity(intent);
                            break;
                        default:
                            bundle.putBoolean("isTitle",true);
                            context.startActivity(DefaultWebViewActivity.class, bundle);
                            break;
                    }


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView textView;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_function_iv);
            textView = itemView.findViewById(R.id.item_function_tv);
            relativeLayout = itemView.findViewById(R.id.item_function_rl);
        }
    }
}
