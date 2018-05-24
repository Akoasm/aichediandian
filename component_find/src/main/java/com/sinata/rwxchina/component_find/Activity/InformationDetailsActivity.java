package com.sinata.rwxchina.component_find.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.webViewUtils.WebViewUtils;
import com.sinata.rwxchina.component_find.R;

/**
 * @author:wj
 * @datetime：2017/11/15
 * @describe：
 * @modifyRecord:
 */


public class InformationDetailsActivity extends BaseActivity {
    private LinearLayout webView;
    private ImageView back;//返回键
    private TextView title_tv;//标题
    private String news_id,mTitle;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.information_details;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        Intent intent = getIntent();
        if(intent !=  null){
            news_id = intent.getStringExtra("news_id");
            mTitle = intent.getStringExtra("title");
        }
        webView = view.findViewById(R.id.information_webview);
        back = view.findViewById(R.id.information_back);
        title_tv = view.findViewById(R.id.information_title);
        title_tv.setText(mTitle);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if(v.getId() == R.id.information_back) {
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        WebViewUtils webViewUtils = new WebViewUtils(webView);
        webViewUtils.setmAgentWeb(HttpPath.INFORMATION_DETAILS+news_id,this);
    }
}
