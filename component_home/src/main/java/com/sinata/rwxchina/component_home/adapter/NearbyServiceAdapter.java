package com.sinata.rwxchina.component_home.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.NativeUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.view.ActionSheetDialog;
import com.sinata.rwxchina.component_home.R;

import java.util.List;

/**
 * @author:zy
 * @detetime:2018/1/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class NearbyServiceAdapter extends BaseAdapter {
    List<PoiItem> list;
    private BaseActivity baseActivity;

    public NearbyServiceAdapter(List<PoiItem> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
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
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_civilianservice, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mItemCivilianserviceName.setText(list.get(position).getTitle());
        viewHolder.mItemCivilianserviceAddress.setText(list.get(position).getSnippet());
        if (list.get(position).getDistance() != -1) {
            float f = list.get(position).getDistance() / 1000.0f;
            viewHolder.mItemCivilianserviceMiles.setText(f + "km");
        }
        viewHolder.mItemCivilianserviceSubtitle.setText(list.get(position).getTypeDes());
        if (TextUtils.isEmpty(list.get(position).getTel())){
            viewHolder.mItemCivilianservicePhone.setVisibility(View.GONE);
        }else {
            viewHolder.mItemCivilianservicePhone.setVisibility(View.VISIBLE);
        }
        viewHolder.mItemCivilianservicePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPhoneUtils.call(baseActivity,list.get(position).getTel());
            }
        });
        viewHolder.mItemCivilianserviceRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(baseActivity)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("百度地图", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        NativeUtils.invokingBD(baseActivity,list.get(position).getTitle(),list.get(position).getCityName());
                                    }
                                })
                        .addSheetItem("高德地图", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        NativeUtils.invokingGD(baseActivity,list.get(position).getTitle());
                                    }
                                }).show();

            }
        });
        return convertView;
    }

    public List<PoiItem> getList() {
        return list;
    }

    public void setList(List<PoiItem> list) {
        this.list = list;
    }

    class ViewHolder {
        private TextView mItemCivilianserviceName;
        private TextView mItemCivilianserviceSubtitle;
        private TextView mItemCivilianserviceMiles;
        private TextView mItemCivilianserviceAddress;
        private LinearLayout mItemCivilianservicePhone;
        private LinearLayout mItemCivilianserviceRoute;

        public ViewHolder(View view) {
            mItemCivilianserviceName = view.findViewById(R.id.item_civilianservice_name);
            mItemCivilianserviceSubtitle = view.findViewById(R.id.item_civilianservice_subtitle);
            mItemCivilianserviceMiles = view.findViewById(R.id.item_civilianservice_miles);
            mItemCivilianserviceAddress = view.findViewById(R.id.item_civilianservice_address);
            mItemCivilianservicePhone = view.findViewById(R.id.item_civilianservice_phone);
            mItemCivilianserviceRoute = view.findViewById(R.id.item_civilianservice_route);
        }
    }
}
