package com.sinata.rwxchina.component_basic.basic.basiccomment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentEntity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentListActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类评论工具类
 * @modifyRecord
 */

public class CommentUtils {
    public static int num=3;
    /**
     * 获取商铺评论，只获取三条
     * @param activity
     * @param shopId
     */
    public static void getComment(final View view, final BaseActivity activity, final String shopId){
        final LinearLayout more=view.findViewById(R.id.comment_more);
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
        params.put("pageSize","3");
        ApiManager apiManager=new ApiManager(activity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<CommentEntity> commentEntities=new Gson().fromJson(result,new TypeToken<List<CommentEntity>>(){}.getType());
                if (commentEntities==null||commentEntities.size()==0){
                    view.setVisibility(View.GONE);
                    return;
                }
                if (commentEntities.size()<=3){
                    more.setVisibility(View.GONE);
                }
                TextView toList=view.findViewById(R.id.food_tv_comments);
                RecyclerView recyclerView=view.findViewById(R.id.basic_shop_comment);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                CommentAdapter adapter=new CommentAdapter(activity,R.layout.item_basic_comment,commentEntities);
                adapter.setNum(num);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(activity,LinearLayoutManager.HORIZONTAL,2,activity.getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));

                //跳转到评价列表
                toList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(activity, CommentListActivity.class);
                        intent.putExtra("shopId",shopId);
                        activity.startActivity(intent);
                    }
                });
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETCOMMENT,params);
    }
}
