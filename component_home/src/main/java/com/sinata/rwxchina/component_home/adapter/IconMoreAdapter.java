package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentListActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_basic.basic.basiclist.BasicListActivity;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.ListJumpUtils;
import com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity;
import com.sinata.rwxchina.component_basic.jumputils.IconJumpUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.activity.CommodityListActivity;
import com.sinata.rwxchina.component_home.activity.InsuranceListActivity;
import com.sinata.rwxchina.component_home.activity.NearbySerivceActivity;
import com.sinata.rwxchina.component_home.entity.IconMoreEntity;
import com.sinata.rwxchina.component_home.utils.MoreJumpUitls;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：全部服务适配器
 * @modifyRecord:
 */


public class IconMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IconMoreEntity> list;
    private LayoutInflater mInflater;
    private BaseActivity mC;

    private int PERIMETER = 1;
    private int SERVICE = 2;

    public IconMoreAdapter(BaseActivity mC) {
        this.list = new ArrayList<>();
        this.mInflater = LayoutInflater.from(mC);
        this.mC = mC;
    }

    public List<IconMoreEntity> getList() {
        return list;
    }

    public void setList(List<IconMoreEntity> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PERIMETER) {
            View v = mInflater.inflate(R.layout.item_icon_more_recycler, parent, false);
            PerimeterViewHolder perimeterViewHolder = new PerimeterViewHolder(v);
            perimeterViewHolder.setIsRecyclable(true);
            return perimeterViewHolder;
        } else {
            View v1 = mInflater.inflate(R.layout.item_icon_more_recycler, parent, false);
            ServiceHolder serviceHolder = new ServiceHolder(v1);
            serviceHolder.setIsRecyclable(true);
            return serviceHolder;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 2:
                final ServiceHolder serviceViewHolder = (ServiceHolder) holder;
                serviceViewHolder.title.setText(list.get(position).getName());
                if (list != null) {
                    final List<IconMoreEntity.ListBean> listBean = list.get(position).getList();
                    IconMoreDetailsAdapter detailsAdapter = new IconMoreDetailsAdapter(mC);
                    serviceViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mC, 3));
                    serviceViewHolder.recyclerView.setAdapter(detailsAdapter);
                    detailsAdapter.setList(listBean);
                    detailsAdapter.setOnItemActionListener(new IconMoreDetailsAdapter.OnItemActionListener() {
                        @Override
                        public void onItemClickListener(View v, int pos) {
                            Bundle bundle=new Bundle();
                            if (listBean.get(pos).getId().equals("17")){
                                bundle.putString("goods_type","10040");
                                bundle.putString("title",listBean.get(pos).getTitle());
                                bundle.putBoolean("isHome",true);
                                mC.startActivity(CommodityListActivity.class,bundle);
                            }else {
                                bundle.putString("shopType", listBean.get(pos).getShop_type());
                                bundle.putString("shopTypeLabels", listBean.get(pos).getShop_type_labels());
                                bundle.putString("iconLabel", listBean.get(pos).getIcon_label());
                                bundle.putString("title", listBean.get(pos).getTitle());
                                MoreJumpUitls.jump(listBean.get(pos), mC, null, bundle);
                            }
                        }
                    });
                }
                break;
            case 1:
                final Bundle bundle= new Bundle();
                final PerimeterViewHolder perimeterViewHolder = (PerimeterViewHolder) holder;
                perimeterViewHolder.title.setText(list.get(position).getName());
                if (list != null) {
                    List<IconMoreEntity.ListBean> listBean = list.get(position).getList();
                    PerimeterAdapter perimeterAdapter = new PerimeterAdapter(mC);
                    perimeterViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mC, 4));
                    perimeterViewHolder.recyclerView.setAdapter(perimeterAdapter);
                    perimeterAdapter.setList(listBean);
                    perimeterAdapter.setmOnItemActionListener(new PerimeterAdapter.OnItemActionListener() {
                        @Override
                        public void onItemClickListener(View v, int pos, String key) {
                            bundle.putSerializable("keyWord",key);
                            mC.startActivity(NearbySerivceActivity.class,bundle);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 1) {
            return PERIMETER;
        } else {
            return SERVICE;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ServiceHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView recyclerView;

        public ServiceHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_icon_more_title);
            recyclerView = itemView.findViewById(R.id.item_icon_more_recycler);
        }
    }

    public class PerimeterViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView recyclerView;

        public PerimeterViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_icon_more_title);
            recyclerView = itemView.findViewById(R.id.item_icon_more_recycler);
        }
    }
}
