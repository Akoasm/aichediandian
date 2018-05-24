package com.sinata.rwxchina.component_aboutme.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.AddressListActivity;
import com.sinata.rwxchina.basiclib.utils.chooseimageutils.ChooseImageUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.PersonalBean;
import com.sinata.rwxchina.component_aboutme.contract.UpdatePersonalInfoContract;
import com.sinata.rwxchina.component_aboutme.presenter.UpdatePresenterInfoPresenter;
import com.tbruyelle.rxpermissions.Permission;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/1/4
 * @describe：修改个人资料
 * @modifyRecord:修改记录
 */

public class UpdatePersonalInfoActivity extends BaseActivity implements UpdatePersonalInfoContract.View {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private ImageView mPersonalInfoHeadIv, back;
    private EditText mPersonalInfoNickNameTv;
    private EditText mPersonalInfoNameEt;
    private TextView title;
    private CheckBox mPersonalInfoManCb;
    private CheckBox mPersonalInfoWomenCb;
    private EditText mPersonalInfoIDCardNumberEt;
    private TextView mPersonalInfoIdentityTv;
    private TextView mPersonalInfoAddressTv;
    private RelativeLayout mPersonalInfoAddress;
    private Button mPersonalInfoSaveBtn;
    private PersonalBean Identity;
    private List<File> list;
    private Map<String, String> params;
    private UpdatePresenterInfoPresenter updatePresenterInfoPresenter;
    private boolean isAgent = false;
    private View statusBar;


    @Override
    public void initParms(Bundle params) {
        list = new ArrayList<>();
        Identity = (PersonalBean) params.getSerializable("personalInfo");
        if (Identity.getIs_safeagent().equals("1") || Identity.getIs_spreadagent().equals("1")) {
            isAgent = true;
        }
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_updatepersonalinfo;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        params = new HashMap<>();
        mPersonalInfoHeadIv = view.findViewById(R.id.personalInfo_head_iv);
        mPersonalInfoNickNameTv = view.findViewById(R.id.personalInfo_nickName_tv);
        mPersonalInfoNameEt = view.findViewById(R.id.personalInfo_Name_et);
        mPersonalInfoManCb = view.findViewById(R.id.personalInfo_man_cb);
        mPersonalInfoWomenCb = view.findViewById(R.id.personalInfo_women_cb);
        mPersonalInfoIDCardNumberEt = view.findViewById(R.id.personalInfo_IDCardNumber_et);
        mPersonalInfoIdentityTv = view.findViewById(R.id.personalInfo_Identity_tv);
        mPersonalInfoAddressTv = view.findViewById(R.id.personalInfo_address_tv);
        mPersonalInfoAddress = view.findViewById(R.id.personalInfo_address_relative);
        mPersonalInfoSaveBtn = view.findViewById(R.id.personalInfo_save_btn);
        View titleView = view.findViewById(R.id.updateUserInfo_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        mPersonalInfoManCb.setOnClickListener(this);
        mPersonalInfoWomenCb.setOnClickListener(this);
        mPersonalInfoHeadIv.setOnClickListener(this);
        mPersonalInfoSaveBtn.setOnClickListener(this);
        mPersonalInfoAddress.setOnClickListener(this);
        mPersonalInfoNickNameTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    params.put("user_name", mPersonalInfoNickNameTv.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.personalInfo_man_cb) {
            mPersonalInfoManCb.setChecked(true);
            mPersonalInfoWomenCb.setChecked(false);
        } else if (i == R.id.personalInfo_women_cb) {
            mPersonalInfoManCb.setChecked(false);
            mPersonalInfoWomenCb.setChecked(true);
        } else if (i == R.id.personalInfo_head_iv) {
            chooseImage();
        } else if (i == R.id.personalInfo_save_btn) {
            setParams();
            if (list.size() > 0)
                updatePresenterInfoPresenter.updateInfo(params, list);
            else
                updatePresenterInfoPresenter.updateInfo(params, null);
        }else if(i == R.id.personalInfo_address_relative){
            startActivity(AddressListActivity.class);
        }
    }

    private void chooseImage() {
        PermissionUtils.permissonMoreAll(this, new PermissionCall() {
            @Override
            public void success() {
                ChooseImageUtils.chooseImage(UpdatePersonalInfoActivity.this, REQUEST_CODE_CHOOSE, 1);
            }

            @Override
            public void fail() {
                ToastUtils.showShort("缺少必要权限");
            }

            @Override
            public void next(Permission permission) {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            ImageUtils.showCircleImageAsUri(this, Matisse.obtainResult(data).get(0), mPersonalInfoHeadIv);
            list.clear();
            list.add(new File(Matisse.obtainPathResult(data).get(0)));
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        showInfo();
        updatePresenterInfoPresenter = new UpdatePresenterInfoPresenter(this);
        updatePresenterInfoPresenter.attachView(this);
        setTitleBarView();
    }

    private void showInfo() {
        ImageUtils.showCircleImage(this, HttpPath.IMAGEURL + Identity.getUser_head(), mPersonalInfoHeadIv, R.mipmap.icon_me_headphoto);
        mPersonalInfoNickNameTv.setText(Identity.getUser_name());
        updatePresenterInfoPresenter = new UpdatePresenterInfoPresenter(this);
        mPersonalInfoNameEt.setText(Identity.getNames());
        mPersonalInfoIDCardNumberEt.setText(Identity.getCard_number());
        mPersonalInfoIdentityTv.setText(Identity.getUid_name());
        if (isAgent) {
            mPersonalInfoNameEt.setEnabled(false);
            mPersonalInfoIDCardNumberEt.setEnabled(false);
        }
        switch (Identity.getSex()){
            case "1":
                mPersonalInfoManCb.setChecked(true);
                break;
            case "2":
                mPersonalInfoWomenCb.setChecked(true);
                break;
        }


    }

    private void setParams() {
        if (!isAgent) {
            if (!TextUtils.isEmpty(mPersonalInfoNameEt.getText().toString().trim()))
                params.put("names", mPersonalInfoNameEt.getText().toString().trim());
            if (!TextUtils.isEmpty(mPersonalInfoIDCardNumberEt.getText().toString().trim()))
                params.put("card_number", mPersonalInfoIDCardNumberEt.getText().toString().trim());
        }
        if (mPersonalInfoManCb.isChecked()) {
            params.put("sex", 1 + "");
        } else if (mPersonalInfoWomenCb.isChecked()) {
            params.put("sex", 2 + "");
        }
    }

    @Override
    public void showView(String s) {
        ToastUtils.showShort(s);
    }

    private void setTitle() {
        title.setText("个人资料");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        updatePresenterInfoPresenter.detachView();
        super.onDestroy();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(UpdatePersonalInfoActivity.this, null);
    }
}
