package com.sinata.rwxchina.component_aboutme.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.activity.EntertainmentOrderDetailActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundDetailActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundFailedActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundSuccessActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundingActivity;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;
import com.sinata.rwxchina.component_aboutme.contract.OrderCancelDeleteContract;
import com.sinata.rwxchina.component_aboutme.presenter.OrderCanaelDeletePresenter;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/19
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class RefundListAdapter extends BaseAdapter implements OrderCancelDeleteContract.View{
    private BaseActivity baseActivity;
    private List<RefundOrderBean> list;
    private OrderCanaelDeletePresenter orderCanaelPresenter;

    public RefundListAdapter(BaseActivity baseActivity, List<RefundOrderBean> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        orderCanaelPresenter = new OrderCanaelDeletePresenter(baseActivity);
        orderCanaelPresenter.attachView(this);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_refundorder, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        showRefundOrder(viewHolder,position);
        jumpActivity(viewHolder,position);
        return convertView;
    }

    private void startActivity(View view, final Class claz, @Nullable final Bundle bundle) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle != null)
                    baseActivity.startActivity(claz, bundle);
                else {
                    baseActivity.startActivity(claz);
                }

            }
        });

    }
    private void jumpActivity(ViewHolder viewHolder, final int position){
        Bundle bundle = new Bundle();
        bundle.putString("orderson",list.get(position).getOrderson());
        if (list.get(position).getIs_mall().equals("0")){
            switch (list.get(position).getIndent_state()){
                case "51":
                    bundle.putString("orderson",list.get(position).getOrderson());
                    bundle.putString("goodsName",list.get(position).getGoods_name());
                    bundle.putString("payMoney",list.get(position).getPay_money());
                    startActivity(viewHolder.relativeLayout, RefundDetailActivity.class,bundle);
                    break;
                case "91":
                    bundle.putString("type","91");
                    startActivity(viewHolder.relativeLayout, EntertainmentOrderDetailActivity.class,bundle);
                    break;
            }
        }else if (list.get(position).getIs_mall().equals("1")){
             switch (list.get(position).getIndent_state()){
                 case "52":
                 case "53":
                     startActivity(viewHolder.relativeLayout, RefundingActivity.class,null);
                     break;
                 case "91":
                     startActivity(viewHolder.relativeLayout, RefundSuccessActivity.class,bundle);
                     break;
             }
        }
        switch (list.get(position).getIndent_state()){
            case "60":
                bundle.putString("type",list.get(position).getIs_mall());
                bundle.putSerializable("refundOrder",list.get(position));
                startActivity(viewHolder.relativeLayout, RefundFailedActivity.class,bundle);
                break;
        }
    }
    private void showRefundOrder(ViewHolder viewHolder, final int position){
        switch (list.get(position).getIndent_state()) {
            case "51":
                viewHolder.orderStatus.setText("退款中");
                break;
            case "52":
                viewHolder.orderStatus.setText("申请换货");
                break;
            case "53":
                viewHolder.orderStatus.setText("申请退货");
                break;
            case "60":
                viewHolder.orderStatus.setText("退款失败");
                break;
            case "91":
                viewHolder.orderStatus.setText("退款成功");
                break;
        }
        viewHolder.orderNumber.setText("订单号：" + list.get(position).getOrderson());
        viewHolder.commodityName.setText(list.get(position).getGoods_name());
        viewHolder.commodityCount.setText("x" + list.get(position).getGoods_number());
        viewHolder.commodityMoney.setText(list.get(position).getPay_money());
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL + list.get(position).getGoods_img(), viewHolder.commedityImage);
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCanaelPresenter.deleteOrder(list.get(position).getOrderson(),position);
            }
        });
    }
    public List<RefundOrderBean> getList() {
        return list;
    }

    public void setList(List<RefundOrderBean> list) {

        this.list = list;
    }

    @Override
    public void showView(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    class ViewHolder {
        private TextView orderNumber, orderStatus, commodityName, commodityCount,
                commodityMoney,delete;
        private ImageView commedityImage;
        private RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            orderNumber = view.findViewById(R.id.allOrder_orderNumber_tv);
            orderStatus = view.findViewById(R.id.allOrder_orderStatus_tv);
            commodityName = view.findViewById(R.id.allOrder_commodityName_tv);
            commodityCount = view.findViewById(R.id.allOrder_orderCount_tv);
            commedityImage = view.findViewById(R.id.allOrder_commodityImage_iv);
            delete = view.findViewById(R.id.order_pay_btn);
            commodityMoney = view.findViewById(R.id.order_realPayMoney_tv);
            relativeLayout = view.findViewById(R.id.refundOrder_rl);
        }
    }
}
