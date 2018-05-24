package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.clearcacheutils.CacheManager;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.PersonalBean;
import com.sinata.rwxchina.component_login.activity.LoginPhoneActivity;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：设置
 * @modifyRecord:修改记录
 */

public class SettingActivity extends BaseActivity {
    private ImageView mIconImg;
    private TextView mSettingNameTv;
    private LinearLayout mSettingChangeAccount;
    private LinearLayout mSettingClearCacheLl;
    private LinearLayout mSettingAboutusLl;
    private TextView mAboutUsVersionTv,title;
    private ImageView back;
    private Button mLogOut;
    private View statusBar;
    private PersonalBean personalBean;
    private static final int LOGOUT = 12;

    @Override
    public void initParms(Bundle params) {
        personalBean = (PersonalBean) params.getSerializable("personalInfo");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        View titleView = view.findViewById(R.id.setting_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
        mIconImg = view.findViewById(R.id.icon_img);
        mSettingNameTv = view.findViewById(R.id.setting_name_tv);
        mSettingChangeAccount = view.findViewById(R.id.setting_changeAccount);
        mSettingClearCacheLl = view.findViewById(R.id.setting_clearCache_ll);
        mSettingAboutusLl = view.findViewById(R.id.setting_aboutus_ll);
        mAboutUsVersionTv = view.findViewById(R.id.aboutUs_version_tv);
        mLogOut = view.findViewById(R.id.log_out);
    }

    @Override
    public void setListener() {
        mSettingChangeAccount.setOnClickListener(this);
        mSettingClearCacheLl.setOnClickListener(this);
        mLogOut.setOnClickListener(this);
        mSettingAboutusLl.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.setting_changeAccount ){
            startActivity(LoginPhoneActivity.class);
            finish();
        }else if (i == R.id.log_out){
            CommonParametersUtils.clearParams(this);
            LogUtils.e("zxc",CommonParametersUtils.getUid(this));
            setResult(LOGOUT);
            finish();
        }else if (i == R.id.setting_clearCache_ll){
            CacheManager.cleanApplicationData(this);
        }else if (i == R.id.setting_aboutus_ll){
            startActivity(AboutUsActivity.class);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitleBarView();
        showView();
    }

    private void showView(){
        if (!TextUtils.isEmpty(personalBean.getUser_head()))
        ImageUtils.showCircleImage(this, HttpPath.IMAGEURL+personalBean.getUser_head(),mIconImg);
        mSettingNameTv.setText(personalBean.getUser_name());
        mAboutUsVersionTv.setText("V"+ AppUtils.getVersionName(this));
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setListImmersion(getWindow(),this,statusBar);
        title.setText("设置");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(SettingActivity.this, null);
    }
}
