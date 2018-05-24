package com.sinata.rwxchina.component_find.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.view.SwitchView;
import com.sinata.rwxchina.component_find.R;

/**
 * Created by HRR on 2017/10/16.
 */

public class FindFragment extends BaseFragment {
    /**假的状态栏*/
    private View statusView;
    private FindInformationFragment mFind_InformationFragment;//资讯
    private WuJiInformationFragment wuJiInformationFragment;
    private OtherFragment otherFragment;
    private SwitchView switchView;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        switchView = view.findViewById(R.id.find_switchview);
        statusView=view.findViewById(R.id.findfragment_status);
        setStatusBar(statusView, ScreenUtils.getStatusHeight(getActivity()));
        addFragment();
    }

    @Override
    public void setListener() {
        switchView.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                if (isChecked) {
                    setFragment(0);
                } else {
                    setFragment(1);

                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
    }

    public void setFragment(int i) {
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        hideFragment(mTransaction);
        switch (i) {
            case 0:
//                if (mFind_InformationFragment == null) {
//                    mFind_InformationFragment = new FindInformationFragment();
//                    mTransaction.add(R.id.find_content, mFind_InformationFragment);
//                } else {
//                    mTransaction.show(mFind_InformationFragment);
//                }
                if (wuJiInformationFragment == null) {
                    wuJiInformationFragment = new WuJiInformationFragment();
                    mTransaction.add(R.id.find_content, wuJiInformationFragment);
                } else {
                    mTransaction.show(wuJiInformationFragment);
                }
                break;

            case 1:
                if (otherFragment == null) {
                    otherFragment = new OtherFragment();
                    mTransaction.add(R.id.find_content, otherFragment);
                } else {
                    mTransaction.show(otherFragment);
                }
                break;
            default:
                break;

        }
        mTransaction.commit();
    }


    private void hideFragment(FragmentTransaction mTransaction) {
//        if (mFind_InformationFragment != null) {
//            mTransaction.hide(mFind_InformationFragment);
//        }
        if (wuJiInformationFragment != null) {
            mTransaction.hide(wuJiInformationFragment);
        }

        if (otherFragment != null) {
            mTransaction.hide(otherFragment);
        }

    }

    private void addFragment() {
        FragmentManager mFragmentManager  = getActivity().getSupportFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        setFragment(0);
    }

    /**
     * 设置假状态栏高度
     */
    private void setStatusBar(View view,int height){
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height=height;
    }
}
