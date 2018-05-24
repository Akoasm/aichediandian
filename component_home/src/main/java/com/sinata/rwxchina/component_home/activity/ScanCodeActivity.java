package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_home.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * @author:wj
 * @datetime：2017/12/27
 * @describe：
 * @modifyRecord:
 */


public class ScanCodeActivity extends BaseActivity implements QRCodeView.Delegate{
    private QRCodeView mQRCodeView;
    private TextView title;
    private ImageView back;
    private View statusBar;

    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_scan_code;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mQRCodeView = view.findViewById(R.id.scan_code_zxing);
        title = view.findViewById(R.id.food_comment_title_tv);
        back = view.findViewById(R.id.food_comment_back);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("二维码扫描");
        mQRCodeView.setDelegate(this);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if(v.getId() == R.id.food_comment_back){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
            setTitleBarView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

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
        StatusBarUtil.setTranslucentForImageViewInFragment(ScanCodeActivity.this, null);
    }
}
