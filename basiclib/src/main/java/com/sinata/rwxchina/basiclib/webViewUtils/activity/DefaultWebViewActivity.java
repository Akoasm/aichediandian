package com.sinata.rwxchina.basiclib.webViewUtils.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.webViewUtils.AndroidAgentInterface;
import com.sinata.rwxchina.basiclib.webViewUtils.DefaultWebChromeClient;
import com.sinata.rwxchina.basiclib.webViewUtils.DefaultWebViewClient;
import com.sinata.rwxchina.basiclib.webViewUtils.WebViewUtils;
import com.sinata.rwxchina.basiclib.webViewUtils.utlis.WebTitleUtils;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 默认activity中webview
 * @modifyRecord
 */

public class DefaultWebViewActivity extends BaseActivity {
    private String titleContent;
    private LinearLayout linearLayout;
    private String type;
    /**
     * 加载的url
     */
    private String url;
    /**
     * 是否展示标题栏，默认展示
     */
    private boolean isTitle = false;
    private String isHead;
    private AgentWeb mAgentWeb;
    private View toolbar;
    private WebViewUtils webViewUtils;
    private View statusBar;
    private String uid;
    private String token;

    @Override
    public void initParms(Bundle parms) {
        url = parms.getString("url");
        isHead = parms.getString("is_head");
        isTitle = parms.getBoolean("isTitle", false);
        type=parms.getString("type");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_defaultwebview;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        linearLayout = findViewById(R.id.default_webview);
        toolbar = findViewById(R.id.web_toolbar);
        statusBar = findViewById(R.id.webview_fakeview);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        uid = CommonParametersUtils.getUid(this);
        token = CommonParametersUtils.getToken(this);
        webViewUtils = new WebViewUtils(linearLayout, new DefaultWebChromeClient(), new DefaultWebViewClient(this));
        //将uid和token拼接到请求地址中
        if (url.contains("?")){
            url=url+"&uid="+uid+"&token="+token;
        }else {
            url=url+"?uid="+uid+"&token="+token;
        }
        if (!TextUtils.isEmpty(type)&&"daijia".equals(type)){
            url=url+"&lng="+LocationUtils.getLng(mContext)+"&lat="+LocationUtils.getLat(mContext)+"&address="+LocationUtils.getAddress(mContext)+"&gpsType=gaodeGPS";
        }
        if (isTitle) {
            webViewUtils.setTitleWeb(url, this, mCallback);
            LogUtils.e("lianjie"+url);
            toolbar.setVisibility(View.VISIBLE);
        } else {
            webViewUtils.setmAgentWeb(url, this);
            LogUtils.e("lianjie"+url);
            toolbar.setVisibility(View.GONE);
        }
        mAgentWeb = webViewUtils.getmAgentWeb();
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidAgentInterface(mAgentWeb,this));
        setTitleBarView();
    }


    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (!TextUtils.isEmpty(title)) {
                if (title.length() > 10) {
                    title = title.substring(0, 10).concat("...");
                }
            }
            titleContent = title;
            WebTitleUtils.setTitle(toolbar, DefaultWebViewActivity.this, mAgentWeb, titleContent);

//            if (mAgentWeb!=null){
//            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAgentWeb.uploadFileResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

}
