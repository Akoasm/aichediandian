package com.sinata.rwxchina.basiclib.webViewUtils;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

/**
 * @author HRR
 * @datetime 2017/11/28
 * @describe 默认WebViewClient
 * @modifyRecord
 */

public class DefaultWebViewClient extends WebViewClient {
    private Context mC;

    public DefaultWebViewClient(Context mC) {
        this.mC = mC;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtils.e("DefaultWebViewClient","url:"+url);
        if (url.startsWith("tel:")){//拨打电话
            String phoneNumber=url.substring(4,url.length());
            LogUtils.e("DefaultWebViewClient","url:"+url+"  phoneNumber:"+phoneNumber);
            CallPhoneUtils.call(mC,phoneNumber);
        }
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }



}
