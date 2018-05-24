package com.sinata.rwxchina.basiclib.basic.basicGroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 套餐列表页
 * @modifyRecord
 */

public class GroupListActivity extends BaseActivity {
    private String shopId;
    private RecyclerView groupRecycler;
    private GroupListAdapter adapter;
    private ImageView back;
    private TextView title;
    private View statusBar;
    private List<GroupEntity> groupEntities;
    private String shopinfo;
    @Override
    public void initParms(Bundle parms) {
        shopId=parms.getString("shopId");
        shopinfo = parms.getString("shop");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_grouplist;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        groupRecycler=findViewById(R.id.activity_group_recycler);
        groupRecycler.setLayoutManager(new LinearLayoutManager(this));
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("全部套餐");
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.food_comment_back){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getGroupList();
        setTitleBarView();
    }

    /**
     * 获取店铺下所有套餐
     */
    private void getGroupList(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
        params.put("goods_is_group","1");
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                groupEntities=new Gson().fromJson(result,new TypeToken<List<GroupEntity>>(){}.getType());
                setAdapter();
                
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETGOODLIST,params);
    }
    private void setAdapter(){
        adapter=new GroupListAdapter(GroupListActivity.this,R.layout.item_basic_group,groupEntities);
        groupRecycler.setAdapter(adapter);
        groupRecycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,10,getResources().getColor(R.color.background)));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String group=new Gson().toJson(groupEntities.get(position));

                try {
                    Class groups = Class.forName("com.sinata.rwxchina.component_basic.basic.basicgroupdetail.GroupDetailActivity");
                    Intent intent=new Intent(GroupListActivity.this, groups);
                    intent.putExtra("group",group);
                    intent.putExtra("shop",shopinfo);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


//                startActivity(activity, Class.forName("com.sinata.rwxchina.component_basic.car.activity.CarListActivity"),bundle);

            }
        });
    }
    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setListImmersion(getWindow(),this,statusBar);
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(GroupListActivity.this, null);
    }
}
