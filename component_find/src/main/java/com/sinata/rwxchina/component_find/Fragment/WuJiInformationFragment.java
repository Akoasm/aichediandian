package com.sinata.rwxchina.component_find.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.component_find.R;

/**
 * @author HRR
 * @datetime 2017/12/1
 * @describe 无机平台资讯
 * @modifyRecord
 */

public class WuJiInformationFragment extends BaseFragment {
    private WebView webView;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_wuji_information;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        webView = view.findViewById(R.id.wuji);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setWebView();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                url = url.toLowerCase();
                if (!ADFilterTool.hasAd(getContext(), url)) {
                    return super.shouldInterceptRequest(view, url);//正常加载
                }else{
                    return new WebResourceResponse(null,null,null);//含有广告资源屏蔽请求
                }
            }
        });
        webView.loadUrl("http://buser.giiso.com/auth/login.jsp?appId=C0008530001&appType=1");

//        AgentWeb.with(this)
//                .setAgentWebParent(this.linearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
//                .useDefaultIndicator()// 使用默认进度条
//                .setWebChromeClient(new WebChromeClient())
//                .setWebViewClient(new WebViewClient())
//                .createAgentWeb()//
//                .ready()
////                .go("http://buser.giiso.com/auth/login.jsp?appId=C0008530001&appType=1");
//                .go("http://www.baidu.com/");
//        WebViewUtils utils=new WebViewUtils(linearLayout);
//        utils.setmAgentWeb("http://buser.giiso.com/auth/login.jsp?appId=C0008530001&appType=1",this);
    }

    /**
     * 设置webview
     */
    private void setWebView() {
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(false);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //不显示webview缩放按钮false:隐藏收缩图标
        settings.setDisplayZoomControls(false);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);

    }

}
