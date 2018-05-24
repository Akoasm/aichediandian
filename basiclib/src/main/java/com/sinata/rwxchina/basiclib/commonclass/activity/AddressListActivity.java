package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.AddressListBean;
import com.sinata.rwxchina.basiclib.commonclass.adpter.AddressListAdapter;
import com.sinata.rwxchina.basiclib.payment.mallpayment.MallPayMentActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2018/1/8
 * @describe：管理收货地址
 * @modifyRecord:
 */


public class AddressListActivity extends BaseActivity {
    private List<AddressListBean> addressListBean;
    private AddressListAdapter adapter;
    private RecyclerView recyclerView;
    //无收货地址
    private LinearLayout noAddress;
    //新建地址
    private LinearLayout addAddress;
    //标题栏
    private TextView title;
    private ImageView back;
    private View statusBar;
    private String type;

    @Override
    public void initParms(Bundle params) {
        if (params != null) {
            type = params.getString("type");
        }
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manage_address;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

        recyclerView = findViewById(R.id.manage_address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noAddress = findViewById(R.id.manage_address_noaddress);
        addAddress = findViewById(R.id.manage_address_newbuilt);
        statusBar = findViewById(R.id.normal_fakeview);
        title = findViewById(R.id.food_comment_title_tv);
        back = findViewById(R.id.food_comment_back);
        title.setText("管理收货地址");
    }

    @Override
    public void setListener() {
        addAddress.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if (v.getId() == R.id.manage_address_newbuilt) {
            Bundle bundle = new Bundle();
            bundle.putString("edit", "noEdit");
            startActivityForResult(EditAddressActivity.class, bundle,0);
        } else if (v.getId() == R.id.food_comment_back) {
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("resultCode="+resultCode);
        if (requestCode == 0 && resultCode == 0) {
            getData();
            adapter.notifyDataSetChanged();
        } else if (requestCode == 1 && resultCode == 0) {
            getData();
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void doBusiness(Context mContext) {
        getData();
        setTitleBarView();
    }

    /**
     * 获取数据
     */
    private void getData() {
        Map<String, String> params = new HashMap<String, String>();
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                addressListBean = gson.fromJson(result, new TypeToken<List<AddressListBean>>() {
                }.getType());
                setList();
                if (addressListBean.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    noAddress.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    noAddress.setVisibility(View.GONE);
                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.ADDRESSLIST, params);
    }

    /**
     * 删除
     */
    private void delete(String id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.DELETEADDRESS, params);
    }

    /**
     * 设为默认地址
     */
    private void setDefault(String id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.DEFAULTADDRESS, params);
    }


    private void setList() {
        adapter = new AddressListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setList(addressListBean);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.background)));
        //type不为空则是从商城传递过来
        if (!TextUtils.isEmpty(type)) {
            adapter.setOnClickListener(new AddressListAdapter.OnClickListener() {
                @Override
                public void onItemClickListener(View v, AddressListBean address) {
                    EventBus.getDefault().post(address);
                    finish();
                }
            });
        }

        adapter.setOnItemActionListener(new AddressListAdapter.OnItemActionListener() {
            @Override
            public void onItemClickListener(View v, int pos) {
                int i = v.getId();
                LogUtils.e("idmsg="+v.getId());
                if (i == R.id.shipping_address_delete_linear) {
                    delete(addressListBean.get(pos).getId());
                    addressListBean.remove(pos);
                    if (addressListBean.size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        noAddress.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyItemRemoved(pos);
                    adapter.notifyItemRangeChanged(pos, addressListBean.size());
                }
                if (i == R.id.shipping_address_edit_linear) {
                    if (addressListBean.size() != 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("edit", "isEdit");
                        bundle.putString("id", addressListBean.get(pos).getId());
                        bundle.putString("name", addressListBean.get(pos).getName());
                        bundle.putString("phone", addressListBean.get(pos).getPhone());
                        bundle.putString("region", addressListBean.get(pos).getRegion());
                        bundle.putString("address", addressListBean.get(pos).getAddress());
                        bundle.putString("is_mo", addressListBean.get(pos).getIs_mo());
                        startActivityForResult(EditAddressActivity.class, bundle, 1);
                    }

                }
            }
        });

        adapter.setmCheckChange(new AddressListAdapter.CheckChange()

        {
            @Override
            public void check(int position, boolean flag) {
                addressListBean = adapter.getList();
                for (int i = 0; i < addressListBean.size(); i++) {
                    AddressListBean bean = addressListBean.get(i);
                    bean.setIs_mo("0");
                }
                AddressListBean bean = addressListBean.get(position);
                if (flag) {
                    bean.setIs_mo("1");
                } else {
                    bean.setIs_mo("1");
                }
                setDefault(addressListBean.get(position).getId());
                adapter.notifyDataSetChanged();
            }
        });
//        adapter = new AddressListAdapter(R.layout.item_manage_address, addressListBean);
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.background)));
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                int i = view.getId();
//                if (i == R.id.shipping_address_edit_linear) {
//                    if (addressListBean.size() != 0) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString("edit", "isEdit");
//                        bundle.putString("id",addressListBean.get(position).getId());
//                        bundle.putString("name", addressListBean.get(position).getName());
//                        bundle.putString("phone", addressListBean.get(position).getPhone());
//                        bundle.putString("region", addressListBean.get(position).getRegion());
//                        bundle.putString("address", addressListBean.get(position).getAddress());
//                        startActivityForResult(EditAddressActivity.class,bundle,1);
//                    }
//                } else if (i == R.id.shipping_address_delete_linear) {
//                    delete(addressListBean.get(position).getId());
//                    adapter.remove(position);
//                    if(addressListBean.size() == 0){
//                        recyclerView.setVisibility(View.GONE);
//                        noAddress.setVisibility(View.VISIBLE);
//                    }
//                    adapter.notifyDataSetChanged();
//                } else if (i == R.id.shipping_address_checkbox) {
//                    setDefault(addressListBean.get(position).getId());
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    public interface CallAddress {
        void callAddress(AddressListBean address);

    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }
}
