package com.sinata.rwxchina.component_basic.car.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.car.adapter.CleanAdapter;
import com.sinata.rwxchina.component_basic.car.entity.CarGoodsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @detetime：2017/11/3
 * @describe：
 */


public class WashcarFragment extends BaseFragment {
    private CleanAdapter cleanAdapter;
    private List<CarGoodsInfo> infos;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private ImageView clean_NoProduct;
    /**选择商品自定义回调商品信息*/
    private CallCarGoods carGoods;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_washcar;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        recyclerView=view.findViewById(R.id.clean_rv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        relativeLayout=view.findViewById(R.id.clean_rv_rl);
        clean_NoProduct=view.findViewById(R.id.clean_NoProduct);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        Bundle bundle=this.getArguments();
        String data=bundle.getString("cleanList");
        infos=new ArrayList<CarGoodsInfo>();
        List<CarGoodsInfo> clean=  new Gson().fromJson(data,new TypeToken<List<CarGoodsInfo>>(){}.getType());
        if (clean!=null&&clean.size()!=0){
            LogUtils.e("washcar","clean1="+clean.toString());
            infos.addAll(clean);
            relativeLayout.setVisibility(View.VISIBLE);
            clean_NoProduct.setVisibility(View.GONE);
        }else {
            LogUtils.e("washcar","clean2="+clean.toString());
            relativeLayout.setVisibility(View.GONE);
            clean_NoProduct.setVisibility(View.VISIBLE);
        }
        cleanAdapter=new CleanAdapter(getActivity(),true);
        cleanAdapter.setList(infos);
        recyclerView.setAdapter(cleanAdapter);
        cleanAdapter.setOnItemActionListener(new CleanAdapter.OnItemActionListener() {
            @Override
            public void onItemClickListener(View v, int pos) {
                if(infos.get(pos).isCheck()){
                    infos.get(pos).setCheck(false);
                    carGoods.SendPrice(null);

                }else {
                    for (int i = 0; i < infos.size(); i++) {
                        infos.get(i).setCheck(false);
                    }

                    infos.get(pos).setCheck(true);
                    carGoods.SendPrice(infos.get(pos));
                }
                cleanAdapter.notifyDataSetChanged();
            }
        });
    }

    public void choosestate(){
        for (int i = 0; i < infos.size(); i++) {
            infos.get(i).setCheck(false);
        }
        cleanAdapter.notifyDataSetChanged();
    }
    //定义一个回调接口
    public interface CallCarGoods{
        public void SendPrice(CarGoodsInfo strValue);
    }

    /**
     * fragment与activity产生关联是  回调这个方法
     */
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        carGoods = (CallCarGoods) getActivity();
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
