package com.sinata.rwxchina.basiclib.webViewUtils.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.webViewUtils.DefaultWebChromeClient;
import com.sinata.rwxchina.basiclib.webViewUtils.DefaultWebViewClient;
import com.sinata.rwxchina.basiclib.webViewUtils.WebViewUtils;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 默认fragment中webview
 * @modifyRecord
 */

public class DefaultWebViewFragment extends BaseFragment {
    private LinearLayout linearLayout;
    private String url;
    private AgentWeb mAgentWeb;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_defaultwebview;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        linearLayout=view.findViewById(R.id.default_webview);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        WebViewUtils webViewUtils=new WebViewUtils(linearLayout,new DefaultWebChromeClient(),new DefaultWebViewClient(getActivity()));
        webViewUtils.setmAgentWeb(url,this);
        mAgentWeb=webViewUtils.getmAgentWeb();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }



    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }


}
