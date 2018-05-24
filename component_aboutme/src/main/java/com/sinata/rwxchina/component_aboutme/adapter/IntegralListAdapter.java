package com.sinata.rwxchina.component_aboutme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.IntegralBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class IntegralListAdapter extends BaseAdapter {
    private BaseActivity baseActivity;
    private List<IntegralBean.IntegralListBean> list;

    public List<IntegralBean.IntegralListBean> getList() {
        return list;
    }

    public void setList(List<IntegralBean.IntegralListBean> list) {
        this.list = list;
    }

    public IntegralListAdapter(BaseActivity baseActivity, List<IntegralBean.IntegralListBean> list) {

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
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_integral,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.remark.setText(list.get(position).getRemarks_user());
        viewHolder.date.setText(list.get(position).getCreatetime());
        viewHolder.surplusIntegral.setText("剩余积分 "+list.get(position).getNew_integral());
        viewHolder.offIntegral.setText(list.get(position).getOff_integral());
        return convertView;
    }
    class ViewHolder{
        private TextView remark,date,surplusIntegral,offIntegral;

        public ViewHolder(View view) {
            remark = view.findViewById(R.id.item_integralRemark_tv);
            date = view.findViewById(R.id.item_time_tv);
            surplusIntegral = view.findViewById(R.id.item_surplusIntegral_tv);
            offIntegral = view.findViewById(R.id.item_offIntegral_tv);
        }
    }
}
