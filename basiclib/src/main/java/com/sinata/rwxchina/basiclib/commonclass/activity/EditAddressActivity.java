package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2018/1/8
 * @describe：添加新地址
 * @modifyRecord:
 */


public class EditAddressActivity extends BaseActivity {
    //显示item的数量
    private int visibleItems = 5;
    //显示类，只显示省份一级，显示省市两级还是显示省市区三级
    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
    //是否显示半透明背景
    private boolean isShowBg = true;
    //省份滚轮是否可以循环滚动
    private boolean isProvinceCyclic = true;
    //城市滚轮是否可以循环滚动
    private boolean isCityCyclic = true;
    //区县滚轮是否循环滚动
    private boolean isDistrictCyclic = true;
    //标题栏
    private ImageView back;
    private View statusBar;
    private TextView title;
    private String type;
    private String id;
    //姓名
    private EditText name_ed;
    private String name;
    //电话
    private EditText phone_ed;
    private String phone;
    //所在地址
    private LinearLayout address_linear;
    private TextView address_tv;
    private String address;
    //详细地址
    private EditText details_address_ed;
    private String details_address;
    //保存按钮
    private Button conserve;
    //默认地址
    private RelativeLayout default_address;
    private CheckBox isSelected;

    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
        CityPickerView.getInstance().init(this);
        type = params.getString("edit");
        LogUtils.e("types=" + type);
        if (type.equals("isEdit")) {
            id = params.getString("id");
            name = params.getString("name");
            phone = params.getString("phone");
            address = params.getString("region");
            details_address = params.getString("address");
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_address_newbuilt;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        conserve = findViewById(R.id.address_newbuilt_conserve);
        details_address_ed = findViewById(R.id.address_newbuilt_details_address);
        address_tv = findViewById(R.id.address_newbuilt_address);
        address_linear = findViewById(R.id.address_newbuilt_address_linear);
        phone_ed = findViewById(R.id.address_newbuilt_phone);
        name_ed = findViewById(R.id.address_newbuilt_name);
        back = findViewById(R.id.food_comment_back);
        title = findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        default_address = findViewById(R.id.address_newbuilt_check_relative);
        isSelected = findViewById(R.id.address_newbuilt_check);
        title.setText("添加新地址");
        if (type.equals("isEdit")) {
            name_ed.setText(name);
            phone_ed.setText(phone);
            address_tv.setText(address);
            details_address_ed.setText(details_address);
        }
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        address_linear.setOnClickListener(this);
        conserve.setOnClickListener(this);
//        default_address.setOnClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.address_newbuilt_conserve) {
            getData();

        } else if (i == R.id.food_comment_back) {
            finish();
        } else if (i == R.id.address_newbuilt_address_linear) {
            closeKeybord();
            wheel();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitleBarView();
    }

    private void getData() {
        final Map<String, String> params = new HashMap<String, String>();
        name = name_ed.getText().toString();
        phone = phone_ed.getText().toString();
        address = address_tv.getText().toString();
        details_address = details_address_ed.getText().toString();
        if (type.equals("isEdit")) {
            params.put("id", id);
            params.put("name", name);
            params.put("phone", phone);
            params.put("region", address);
            params.put("address", details_address);
        } else {
            params.put("name", name);
            params.put("phone", phone);
            params.put("region", address);
            params.put("address", details_address);
        }
        default_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected.isChecked()) {
                    isSelected.setChecked(false);
                } else {
                    isSelected.setChecked(true);
                    params.put("is_mo","1");
                }
            }
        });
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("EditAddressActivity","result=" + result);
                Intent intent = new Intent(EditAddressActivity.this, AddressListActivity.class);
                setResult(0, intent);
                finish();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.NEWADDRESS, params);
    }

    /**
     * 弹出地址选择
     */
    private void wheel() {
        CityConfig cityConfig = new CityConfig.Builder(EditAddressActivity.this).title("请选择城市")
                .titleTextSize(18)
                .titleTextColor("#333333")
                .titleBackgroundColor("#f2f2f2")
                .confirTextColor("#ff720a")
                .confirmText("确认")
                .confirmTextSize(16)
                .cancelTextColor("#999999")
                .cancelText("取消")
                .cancelTextSize(16)
                .setCityWheelType(mWheelType)
                .showBackground(isShowBg)
                .visibleItemsCount(visibleItems)
                .provinceCyclic(isProvinceCyclic)
                .cityCyclic(isCityCyclic)
                .province("四川省")//默认显示的省份
                .city("成都市")//默认显示省份下面的城市
                .districtCyclic(isDistrictCyclic)
                .setCityWheelType(mWheelType)
                .setCustomItemLayout(R.layout.item_city)
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .build();

        CityPickerView.getInstance().setConfig(cityConfig);
        CityPickerView.getInstance().setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                address_tv.setText(province.getName() + city.getName() + district.getName());
            }

            @Override
            public void onCancel() {
                ToastUtils.showShort("已取消");
            }
        });
        CityPickerView.getInstance().showCityPicker(this);
    }


//    /**
//     * 设为默认地址
//     */
//    private void setDefault() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", id);
//        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
//            @Override
//            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
//
//            }
//
//            @Override
//            public void onResultError(ApiException e, String method) {
//
//            }
//        });
//        apiManager.post(HttpPath.DEFAULTADDRESS, params);
//    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(EditAddressActivity.this, null);
    }

    public void closeKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
