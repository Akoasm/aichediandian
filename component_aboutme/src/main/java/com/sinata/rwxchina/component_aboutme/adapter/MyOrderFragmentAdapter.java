package com.sinata.rwxchina.component_aboutme.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.sinata.rwxchina.component_aboutme.fragment.MyOrderFragment;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/18
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class MyOrderFragmentAdapter extends FragmentPagerAdapter{
    private List<String> title;
    private List<MyOrderFragment> fragmentList;
    private FragmentManager fm;

    public MyOrderFragmentAdapter(FragmentManager fm, List<String> title) {
        super(fm);
        this.fm = fm;
        this.title = title;
    }

    public void setFragmentList(List<MyOrderFragment> fragmentList) {
        if (this.fragmentList!=null){
            FragmentTransaction ft =fm.beginTransaction();
            for (Fragment f: this.fragmentList) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
