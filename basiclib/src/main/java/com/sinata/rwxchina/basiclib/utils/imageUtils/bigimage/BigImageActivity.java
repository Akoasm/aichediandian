package com.sinata.rwxchina.basiclib.utils.imageUtils.bigimage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.github.piasy.biv.view.BigImageView;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;

/**
 * @author HRR
 * @datetime 2018/1/25
 * @describe
 * @modifyRecord
 */

public class BigImageActivity extends BaseActivity {
    private BigImageView bigImageView;
    private String url;
    @Override
    public void initParms(Bundle params) {
        url=params.getString("url");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bigimage;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        bigImageView=findViewById(R.id.bigimageview);
        bigImageView.setProgressIndicator(new ProgressPieIndicator());
        bigImageView.showImage(Uri.parse(url));
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
