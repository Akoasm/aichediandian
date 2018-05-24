package com.sinata.rwxchina.component_home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.component_home.entity.LovelyListEntity;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/7
 * @describe 猜你喜欢卡项适配器
 * @modifyRecord
 */

public class LovelyAdapter extends FragmentPagerAdapter{
    private List<Fragment> mViewList;
    private List<LovelyListEntity> lovelyEntities;

    public LovelyAdapter(FragmentManager fm,List<Fragment> fragments,List<LovelyListEntity> lovelyEntities) {
        super(fm);
        this.mViewList=fragments;
        this.lovelyEntities=lovelyEntities;
    }

    public void setmViewList(List<Fragment> mViewList) {
        this.mViewList = mViewList;
    }

    public void setLovelyEntities(List<LovelyListEntity> lovelyEntities) {
        this.lovelyEntities = lovelyEntities;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtils.e("LovelyAdapter",position+"");
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return lovelyEntities.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lovelyEntities.get(position).getTitle();
    }
}
