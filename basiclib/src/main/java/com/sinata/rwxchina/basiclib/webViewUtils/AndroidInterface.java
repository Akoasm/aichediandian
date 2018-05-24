package com.sinata.rwxchina.basiclib.webViewUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.just.agentweb.AgentWeb;
import com.sinata.rwxchina.basiclib.base.BaseActivity;

/**
 * @author HRR
 * @datetime 2018/1/30
 * @describe
 * @modifyRecord
 */

public class AndroidInterface {
    private Handler deliver = new Handler(Looper.getMainLooper());
    private WebView agent;
    private Context context;

    public AndroidInterface(WebView agent, Context context) {
        this.agent = agent;
        this.context = context;
    }



    @JavascriptInterface
    public void callAndroid(final String msg) {
        switch (msg){
            case "#app":
                ((BaseActivity)context).finish();
                break;
            default:
                break;
        }
    }
}
