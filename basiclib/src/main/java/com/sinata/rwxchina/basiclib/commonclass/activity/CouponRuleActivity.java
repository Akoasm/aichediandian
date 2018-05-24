package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：优惠券使用规则
 * @modifyRecord:修改记录
 */

public class CouponRuleActivity extends BaseActivity {
    private WebView webView;
    private WebSettings mWebSettings;
    private TextView title;
    private ImageView back;

    @Override
    public void initParms(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_couponrule;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        webView = view.findViewById(R.id.couponRule_web);
        View titleView = view.findViewById(R.id.couponRule_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        titleView.setVisibility(View.GONE);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("#app"))
                    finish();
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(HttpPath.WEB_COUPONUSERULE);
    }
    private void setTitle(){
        title.setText("使用规则");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
