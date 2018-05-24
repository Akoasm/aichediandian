package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.component_aboutme.fragment.AboutMeFragment;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/10/17
 * @describe
 */

public class MainActivity extends BaseActivity {
   private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> mAboutmefragment;
    private void swichContent(Fragment from,Fragment to){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mAboutmefragment = new ArrayList<>();
        mAboutmefragment.add(AboutMeFragment.getNewInstance());
        if (to.isAdded()){
            fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mAboutmefragment.get(position);
                }

                @Override
                public int getCount() {
                    return mAboutmefragment.size();
                }
            };
        }

    }
    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view, Bundle bundle) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
