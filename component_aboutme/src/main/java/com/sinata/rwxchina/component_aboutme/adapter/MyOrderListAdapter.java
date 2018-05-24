package com.sinata.rwxchina.component_aboutme.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityOrderCancelOrCompleteActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.EntertainmentOrderBuySuccessActivity;
import com.sinata.rwxchina.basiclib.payment.mallpayment.MallPayMentActivity;
import com.sinata.rwxchina.basiclib.payment.ordinarypayment.PayMentActivity;
import com.sinata.rwxchina.basiclib.payment.utils.PayTypeUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.activity.ApplyRefundActivity;
import com.sinata.rwxchina.component_aboutme.activity.ApplyRefund_MerhcantActivity;
import com.sinata.rwxchina.component_aboutme.activity.ConfirmReceiveActivity;
import com.sinata.rwxchina.component_aboutme.activity.EntertainmentOrderDetailActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundDetailActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundFailedActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundSuccessActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundingActivity;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;
import com.sinata.rwxchina.component_aboutme.contract.OrderCancelDeleteContract;
import com.sinata.rwxchina.component_aboutme.presenter.OrderCanaelDeletePresenter;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/15
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class MyOrderListAdapter extends BaseAdapter implements OrderCancelDeleteContract.View {
    private BaseActivity baseActivity;
    private List<OrderBean> list;
    private OrderCanaelDeletePresenter orderCanaelPresenter;
    private PayTypeUtils payTypeUtils;

    public List<OrderBean> getList() {
        return list;
    }

    public void setList(List<OrderBean> list) {
        this.list = list;
    }

    public MyOrderListAdapter(BaseActivity baseActivity, List<OrderBean> list) {
        this.baseActivity = baseActivity;
        this.list = list;
        orderCanaelPresenter = new OrderCanaelDeletePresenter(baseActivity);
        orderCanaelPresenter.attachView(this);
        payTypeUtils = new PayTypeUtils(baseActivity);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_order, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        showOrderView(viewHolder, position);
        jumpActivity(viewHolder, position);
        return convertView;
    }

    private void startActivity(View view, final Class claz,@Nullable final Bundle bundle) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle!=null)
                baseActivity.startActivity(claz,bundle);
                else
                    baseActivity.startActivity(claz);
            }
        });

    }

    @Override
    public void showView(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }


    private void jumpActivity(final ViewHolder viewHolder, final int position) {
        final Bundle bundle = new Bundle();
        bundle.putString("orderson",list.get(position).getOrderson());
        if (list.get(position).getIs_mall().equals("0")) {//非商城订单跳转
            switch (list.get(position).getIndent_state()) {
                case "10"://待付款
                    bundle.putString("type","10");
                    startActivity(viewHolder.linearLayout,EntertainmentOrderDetailActivity.class,bundle);
                    viewHolder.buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payTypeUtils.againPay(list.get(position).getPay_money(),viewHolder.buy,list.get(position).getOrderson());
                        }
                    });
                    break;
                case "4101"://待验证
                    bundle.putString("type","4101");
                    startActivity(viewHolder.buy, EntertainmentOrderBuySuccessActivity.class,bundle);
//                    if (!list.get(position).getGoods_id().equals("0"))
                    startActivity(viewHolder.linearLayout,EntertainmentOrderDetailActivity.class,bundle);
                    break;
                case "90"://已完成
                    bundle.putString("type","90");
                    startActivity(viewHolder.linearLayout,EntertainmentOrderDetailActivity.class,bundle);

                    break;
                case "20"://已取消
                    bundle.putString("type","20");
                    startActivity(viewHolder.linearLayout,EntertainmentOrderDetailActivity.class,bundle);
                    break;
                case "51"://申请退款
                    bundle.putString("goodsName",list.get(position).getGoods_name());
                    bundle.putString("payMoney",list.get(position).getPay_money());
                    startActivity(viewHolder.linearLayout, RefundDetailActivity.class,bundle);
                    break;
                case "91"://退款成功
                    bundle.putString("type","91");
                    startActivity(viewHolder.linearLayout, EntertainmentOrderDetailActivity.class,bundle);
                    break;
                case "60"://退款失败
                    bundle.putString("type",list.get(position).getIs_mall());
                    bundle.putSerializable("refundOrder",list.get(position));
                    startActivity(viewHolder.linearLayout, RefundFailedActivity.class,bundle);
                    break;
            }

        } else if (list.get(position).getIs_mall().equals("1")) {//商城订单跳转
            switch (list.get(position).getIndent_state()) {
                case "10"://待付款
                    viewHolder.buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payTypeUtils.againPay(list.get(position).getPay_money(),viewHolder.buy,list.get(position).getOrderson());
                        }
                    });
                    break;
                case "4102"://待发货目前和待收货一样处理
                case "4103"://待收货
                    bundle.putSerializable("orderInfo",list.get(position));
                    bundle.putString("orderson",list.get(position).getOrderson());
                    startActivity(viewHolder.requestRefund, ApplyRefundActivity.class,bundle);
                    startActivity(viewHolder.buy, ConfirmReceiveActivity.class,bundle);
                    startActivity(viewHolder.linearLayout, CommodityOrderCancelOrCompleteActivity.class,bundle);
                    break;
                case "90"://已完成
                    bundle.putString("orderson",list.get(position).getOrderson());
                    startActivity(viewHolder.linearLayout, CommodityOrderCancelOrCompleteActivity.class,bundle);
                    break;
                case "20"://已取消
                    bundle.putString("orderson",list.get(position).getOrderson());
                    startActivity(viewHolder.linearLayout, CommodityOrderCancelOrCompleteActivity.class,bundle);
                    break;
                case "52"://申请退货
                case "53"://申请退款
                    startActivity(viewHolder.linearLayout, RefundingActivity.class,null);
                    break;
                case "91"://退款完成
                    startActivity(viewHolder.linearLayout, RefundSuccessActivity.class,bundle);
                    break;
                case "60"://退款失败
                    bundle.putString("type",list.get(position).getIs_mall());
                    bundle.putSerializable("refundOrder",list.get(position));
                    bundle.putBoolean("isOrder",true);
                    startActivity(viewHolder.linearLayout, RefundFailedActivity.class,bundle);
                    break;
            }
        }
    }

    private void showOrderView(final ViewHolder viewHolder, final int position) {
        switch (list.get(position).getIndent_state()) {
            case "10"://待付款
                viewHolder.requestRefund.setVisibility(View.GONE);
                viewHolder.buy.setText("去支付");
                viewHolder.orderStatus.setText("待付款");
                viewHolder.commodityStatus.setVisibility(View.GONE);
                viewHolder.orderDelete.setVisibility(View.GONE);
                viewHolder.orderCancel.setVisibility(View.VISIBLE);
                viewHolder.buy.setVisibility(View.VISIBLE);
                break;
            case "4101"://待验证
                viewHolder.orderDelete.setVisibility(View.GONE);
                viewHolder.buy.setVisibility(View.VISIBLE);
                viewHolder.requestRefund.setVisibility(View.GONE);
                viewHolder.orderCancel.setVisibility(View.GONE);
                viewHolder.buy.setText("查看券码");
                viewHolder.orderStatus.setText("待验证");
                break;
            case "4102"://待发货目前和待收货一样处理
            case "4103"://待收货
                viewHolder.buy.setText("确认收货");
                viewHolder.orderCancel.setVisibility(View.GONE);
                viewHolder.orderDelete.setVisibility(View.GONE);
                viewHolder.buy.setVisibility(View.VISIBLE);
                viewHolder.requestRefund.setVisibility(View.VISIBLE);
                viewHolder.orderStatus.setText("待收货");
                viewHolder.commodityStatus.setVisibility(View.GONE);
                break;
            case "90"://已完成
                viewHolder.orderDelete.setVisibility(View.VISIBLE);
                viewHolder.orderCancel.setVisibility(View.GONE);
                viewHolder.requestRefund.setVisibility(View.GONE);
                viewHolder.buy.setVisibility(View.VISIBLE);
                viewHolder.buy.setText("再次购买");
                viewHolder.orderStatus.setText("已完成");
                viewHolder.commodityStatus.setVisibility(View.GONE);
                viewHolder.buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case "20":
                viewHolder.orderDelete.setVisibility(View.VISIBLE);
                viewHolder.orderCancel.setVisibility(View.GONE);
                viewHolder.requestRefund.setVisibility(View.GONE);
                viewHolder.buy.setVisibility(View.VISIBLE);
                viewHolder.buy.setText("再次购买");
                viewHolder.orderStatus.setText("已取消");
                viewHolder.orderType.setText("价格：￥");
                viewHolder.commodityStatus.setVisibility(View.GONE);
                break;
            case "51":
                viewHolder.orderStatus.setText("退款中");
                showRefundOrder(viewHolder);
                break;
            case "52":
                viewHolder.orderStatus.setText("申请换货");
                showRefundOrder(viewHolder);
                break;
            case "53":
                viewHolder.orderStatus.setText("申请退货");
                showRefundOrder(viewHolder);
                break;
            case "60":
                viewHolder.orderStatus.setText("退款失败");
                showRefundOrder(viewHolder);
                break;
            case "91":
                viewHolder.orderStatus.setText("退款成功");
                showRefundOrder(viewHolder);
                break;
        }
        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderBean orderBean=list.get(position);
                PayTypeUtils payUtils=new PayTypeUtils(baseActivity);
                payUtils.againPay(orderBean.getPay_money(),viewHolder.buy,orderBean.getOrderson());
            }
        });
        viewHolder.orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCanaelPresenter.cancelOrder(list.get(position).getOrderson(),position);
            }
        });
        viewHolder.orderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCanaelPresenter.deleteOrder(list.get(position).getOrderson(),position);
            }
        });
        viewHolder.commodityMoney.setText(list.get(position).getPay_money());
        viewHolder.orderNumber.setText("订单号：" + list.get(position).getOrderson());
        viewHolder.commodityName.setText(list.get(position).getGoods_name());
        viewHolder.commodityCount.setText("x" + list.get(position).getGoods_number());
        ImageUtils.showImage(baseActivity, HttpPath.IMAGEURL + list.get(position).getGoods_img(), viewHolder.commedityImage);
    }
    private void showRefundOrder(ViewHolder viewHolder){
        viewHolder.orderCancel.setVisibility(View.GONE);
        viewHolder.requestRefund.setVisibility(View.GONE);
        viewHolder.orderDelete.setVisibility(View.VISIBLE);
        viewHolder.buy.setVisibility(View.GONE);
        viewHolder.commodityStatus.setVisibility(View.GONE);
    }

    class ViewHolder {
        private TextView orderNumber, orderStatus, commodityName, commodityCount, orderCancel, orderDelete,
                commodityMoney, commodityStatus, orderType;
        private LinearLayout linearLayout;
        private ImageView commedityImage;
        private Button requestRefund, buy;

        public ViewHolder(View view) {
            orderNumber = view.findViewById(R.id.allOrder_orderNumber_tv);
            orderStatus = view.findViewById(R.id.allOrder_orderStatus_tv);
            commodityName = view.findViewById(R.id.allOrder_commodityName_tv);
            commodityCount = view.findViewById(R.id.allOrder_orderCount_tv);
            orderCancel = view.findViewById(R.id.waitPayOrder_cancel_tv);
            orderDelete = view.findViewById(R.id.allOrder_delete_tv);
            commedityImage = view.findViewById(R.id.allOrder_commodityImage_iv);
            requestRefund = view.findViewById(R.id.order_refunds_btn);
            buy = view.findViewById(R.id.order_pay_btn);
            commodityMoney = view.findViewById(R.id.order_realPayMoney_tv);
            commodityStatus = view.findViewById(R.id.allOrder_commodityStatus_tv);
            orderType = view.findViewById(R.id.order_payMoney_tv);
            linearLayout = view.findViewById(R.id.orderDetail_ll);
        }
    }
}
