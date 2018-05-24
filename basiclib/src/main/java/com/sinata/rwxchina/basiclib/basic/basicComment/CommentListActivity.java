package com.sinata.rwxchina.basiclib.basic.basicComment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupListActivity;
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
 * @describe 用户评价列表页面
 * @modifyRecord
 */

public class CommentListActivity extends BaseActivity {
    private String shopId;
    private RecyclerView commentRecycler;
    private CommentListAdapter adapter;
    private ImageView back;
    private TextView title;
    private View statusBar;
    private List<CommentEntity> commentEntities;
    @Override
    public void initParms(Bundle parms) {
        shopId=parms.getString("shopId");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_commentlist;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        commentRecycler=findViewById(R.id.activity_comment_recycler);
        commentRecycler.setLayoutManager(new LinearLayoutManager(this));
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("用户评价");
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
        getComment();
        setTitleBarView();
    }

    /**
     * 获取用户评论
     */
    private void getComment(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                commentEntities=new Gson().fromJson(result,new TypeToken<List<CommentEntity>>(){}.getType());
                setAdapter();

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETCOMMENT,params);
    }
    private void setAdapter(){
        adapter=new CommentListAdapter(CommentListActivity.this,R.layout.item_basic_comment,commentEntities);
        commentRecycler.setAdapter(adapter);
        commentRecycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,8,getResources().getColor(R.color.background)));
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
        StatusBarUtil.setTranslucentForImageViewInFragment(CommentListActivity.this, null);
    }
}
