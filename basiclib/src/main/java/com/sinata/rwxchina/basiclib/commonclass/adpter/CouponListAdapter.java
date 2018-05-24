package com.sinata.rwxchina.basiclib.commonclass.adpter;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.Couponbean;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:
 *  修改构造方法，避免list传入为空时造成空指针错误   2018/01/08  HRR
 */

public class CouponListAdapter extends BaseAdapter {
    private BaseActivity baseActivity;
    private List<Couponbean> list;

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

    public List<Couponbean> getList() {
        return list;
    }

    public void setList(List<Couponbean> list) {
        this.list = list;
    }

    public CouponListAdapter(BaseActivity baseActivity, List<Couponbean> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_mycoupon, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (list.get(position).getCoupon_state()) {
            case "1":
                setCoupon(viewHolder, R.mipmap.icon_used);
                break;
            case "2":
                setCoupon(viewHolder, R.mipmap.icon_expired);
                break;
        }
        viewHolder.couponName.setText(list.get(position).getCoupon_name());
        viewHolder.couponMoney.setText(list.get(position).getMoney());
        viewHolder.validDate.setText(list.get(position).getCoupon_overtime());
        viewHolder.couponDescription.setText(list.get(position).getCoupon_describe());
        return convertView;
    }

    private void setCoupon(ViewHolder viewHolder, int icon) {
        viewHolder.immediatelyUse.setVisibility(View.GONE);
        viewHolder.couponBackground.setBackgroundResource(R.mipmap.expired_coupon_bg);
        viewHolder.icon_rmb.setBackgroundResource(R.mipmap.icon_rmb_gray);
        viewHolder.couponStatus.setBackgroundResource(icon);
        viewHolder.couponMoney.setTextColor(ContextCompat.getColor(baseActivity,R.color.colorGray3));
        viewHolder.couponName.setTextColor(ContextCompat.getColor(baseActivity,R.color.colorGray3));
        viewHolder.couponDescription.setTextColor(ContextCompat.getColor(baseActivity,R.color.colorGray3));
        viewHolder.validDate.setTextColor(ContextCompat.getColor(baseActivity,R.color.colorGray3));
    }

    class ViewHolder {
        private ImageView couponStatus, icon_rmb;
        private TextView couponMoney, couponName, validDate, couponDescription, immediatelyUse;
        private RelativeLayout couponBackground;

        public ViewHolder(View view) {
            couponStatus = view.findViewById(R.id.item_couponStatus_iv);
            couponMoney = view.findViewById(R.id.item_couponMoney_tv);
            couponName = view.findViewById(R.id.item_couponName_tv);
            validDate = view.findViewById(R.id.item_validity_tv);
            couponDescription = view.findViewById(R.id.item_couponRule_tv);
            immediatelyUse = view.findViewById(R.id.immediatelyUse_tv);
            couponBackground = view.findViewById(R.id.item_coupon_rl);
            icon_rmb = view.findViewById(R.id.icon_rmb);
        }
    }
}
