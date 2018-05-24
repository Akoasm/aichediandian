package com.sinata.rwxchina.component_aboutme.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.commonclass.activity.MyCouponActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.commonclass.activity.InsuranceWebViewActivity;
import com.sinata.rwxchina.component_aboutme.activity.BalanceActivity;
import com.sinata.rwxchina.component_aboutme.activity.IntegralActivity;
import com.sinata.rwxchina.component_aboutme.activity.MyOrderActivity;
import com.sinata.rwxchina.component_aboutme.activity.RefundActivity;
import com.sinata.rwxchina.component_aboutme.activity.SettingActivity;
import com.sinata.rwxchina.component_aboutme.activity.UpdatePersonalInfoActivity;
import com.sinata.rwxchina.component_aboutme.bean.AboutMeFragmentFunctionBean;
import com.sinata.rwxchina.component_aboutme.bean.PersonalBean;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.view.DividerGridItemDecoration;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_aboutme.adapter.FunctionRVAdapter;
import com.sinata.rwxchina.component_aboutme.animation.ViewPoint;
import com.sinata.rwxchina.component_aboutme.contract.AboutMeFragmentContract;
import com.sinata.rwxchina.component_aboutme.presenter.AboutMeFragmentPresenter;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_login.activity.LoginPhoneActivity;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * Created by HRR on 2017/10/16.
 */

public class AboutMeFragment extends BaseFragment implements AboutMeFragmentContract.View {
    private RecyclerView function;
    private FunctionRVAdapter functionRVAdapter;
    private ImageView setting, headimage, gender, agentiv, insuranceAgent_iv, businessAlliance_iv;
    private ImageView mWaitPayIv;
    private ImageView mWaitVerificationIv;
    private ImageView mWaitReceiptIv;
    private MyScrollView meFragmentScrollView;
    private RelativeLayout headRelative, personalInfo, moreOrder, waitPay, waitVerification, waitReceipt,finish;
    private TextView login, userName, agent, myOrder;
    private static AboutMeFragment aboutMeFragment;
    private AboutMeFragmentPresenter aboutMeFragmentPresenter;
    private LinearLayout integration, coupon, wallet;//积分，优惠券，钱包
    private int hight;
    private PersonalBean personalBean;
    private View statusBar;
    private QBadgeView waitPayQBadgeView, waitVerificationQBadgeView, waitReceiptQBadgeView;




    public static AboutMeFragment getNewInstance() {
        if (aboutMeFragment == null) {
            aboutMeFragment = new AboutMeFragment();
        }
        return aboutMeFragment;
    }
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_aboutme;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        setting = view.findViewById(R.id.fragment_me_set);
        headimage = view.findViewById(R.id.fragment_me_headphoto);
        login = view.findViewById(R.id.fragment_me_login);
        userName = view.findViewById(R.id.fragment_me_user_name);
        gender = view.findViewById(R.id.fragment_me_user_gender);
        agentiv = view.findViewById(R.id.dl_iv);
        agent = view.findViewById(R.id.dl);
        headRelative = view.findViewById(R.id.meFragment_head_rl);
        meFragmentScrollView = view.findViewById(R.id.meFragment_scroll_sv);
        personalInfo = view.findViewById(R.id.personalInfo_rl);
        function = view.findViewById(R.id.aboutMeFragment_function_Rv);
        integration = view.findViewById(R.id.fragment_me_integration);
        coupon = view.findViewById(R.id.fragment_me_voucher);
        wallet = view.findViewById(R.id.fragment_me_wallet);
        myOrder = view.findViewById(R.id.fragment_me_order_management);
        moreOrder = view.findViewById(R.id.meFragment_moreOrder_rl);
        statusBar = view.findViewById(R.id.aboutme_fakeview);
        waitPay = view.findViewById(R.id.meFragment_waitPay_rl);
        waitVerification = view.findViewById(R.id.meFragment_waitVerification_rl);
        waitReceipt = view.findViewById(R.id.meFragment_waitReceipt_rl);
        setStatusBar(statusBar, ScreenUtils.getStatusHeight(getActivity()));
        insuranceAgent_iv = view.findViewById(R.id.insuranceAgent_iv);
        businessAlliance_iv = view.findViewById(R.id.businessAlliance_iv);
        mWaitPayIv = view.findViewById(R.id.waitPay_iv);
        mWaitVerificationIv = view.findViewById(R.id.waitVerification_iv);
        mWaitReceiptIv = view.findViewById(R.id.waitReceipt_iv);
        waitPayQBadgeView = new QBadgeView(getActivity());
        waitVerificationQBadgeView = new QBadgeView(getActivity());
        waitReceiptQBadgeView = new QBadgeView(getActivity());
        finish = view.findViewById(R.id.meFragment_finish_rl);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
        headimage.setOnClickListener(this);
        setting.setOnClickListener(this);
        wallet.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        moreOrder.setOnClickListener(this);
        integration.setOnClickListener(this);
        coupon.setOnClickListener(this);
        waitVerification.setOnClickListener(this);
        waitPay.setOnClickListener(this);
        waitReceipt.setOnClickListener(this);
        insuranceAgent_iv.setOnClickListener(this);
        businessAlliance_iv.setOnClickListener(this);
        finish.setOnClickListener(this);
//        ViewTreeObserver viewTreeObserver = headRelative.getViewTreeObserver();
//        viewTreeObserver.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//         startanmition();
//            }
//        });
//        meFragmentScrollView .setScrollViewListener(new MyScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
//
//            }
//        });
    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle = new Bundle();
        int i = v.getId();
        if (i == R.id.fragment_me_login) {//登录
            startActivity(LoginPhoneActivity.class);
        } else if (i == R.id.fragment_me_wallet) {//我的钱包
            if (AppUtils.isLogin((BaseActivity) getActivity()))
                startActivity(BalanceActivity.class);
            else
                startActivity(LoginPhoneActivity.class);
        } else if (i == R.id.fragment_me_order_management) {//全部订单
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putInt("page", 0);
                startActivity(MyOrderActivity.class, bundle);
            }else{
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.meFragment_moreOrder_rl) {//退款订单
            if (AppUtils.isLogin((BaseActivity) getActivity()))
                startActivity(RefundActivity.class);
            else
                startActivity(LoginPhoneActivity.class);
        } else if (i == R.id.fragment_me_integration) {//积分
            if (AppUtils.isLogin((BaseActivity) getActivity()))
                startActivity(IntegralActivity.class);
            else
                startActivity(LoginPhoneActivity.class);
        } else if (i == R.id.fragment_me_voucher) {//优惠券
            if (AppUtils.isLogin((BaseActivity) getActivity()))
                startActivity(MyCouponActivity.class);
            else
                startActivity(LoginPhoneActivity.class);
        } else if (i == R.id.fragment_me_set) {//设置
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putSerializable("personalInfo", personalBean);
                startActivity(SettingActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.fragment_me_headphoto) {//修改个人资料
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putSerializable("personalInfo", personalBean);
                startActivity(UpdatePersonalInfoActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.meFragment_waitPay_rl) {//待付款
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putInt("page", 1);
                startActivity(MyOrderActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.meFragment_waitVerification_rl) {//待验证
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putInt("page", 2);
                startActivity(MyOrderActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.meFragment_waitReceipt_rl) {//待收货
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putInt("page", 3);
                startActivity(MyOrderActivity.class, bundle);
            }else{
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.meFragment_finish_rl){//已完成
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putInt("page", 4);
                startActivity(MyOrderActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        }else if (i == R.id.insuranceAgent_iv) {
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putString("uid", CommonParametersUtils.getUid(getActivity()));
                bundle.putString("token", CommonParametersUtils.getToken(getActivity()));
                bundle.putString("url", HttpPath.WEB_INSURANCE);
                startActivity(InsuranceWebViewActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        } else if (i == R.id.businessAlliance_iv) {
            if (AppUtils.isLogin((BaseActivity) getActivity())) {
                bundle.putString("uid", CommonParametersUtils.getUid(getActivity()));
                bundle.putString("token", CommonParametersUtils.getToken(getActivity()));
                bundle.putString("url", HttpPath.WEB_BUSINESSALLIANCE);
                startActivity(InsuranceWebViewActivity.class, bundle);
            }else {
                startActivity(LoginPhoneActivity.class);
            }
        }
    }


    @Override
    public void doBusiness(Context mContext) {
        aboutMeFragmentPresenter = new AboutMeFragmentPresenter((BaseActivity) mContext);
        aboutMeFragmentPresenter.attachView(this);
        aboutMeFragmentPresenter.getIcon();
    }

    @Override
    public void onStart() {
        getPersonalData();
        super.onStart();
    }

    private void getPersonalData() {
        if (!TextUtils.isEmpty(CommonParametersUtils.getUid(getActivity()))) {
            aboutMeFragmentPresenter.getPersonalData();
        } else
            showLoginView();
    }

    @Override
    public void showView(PersonalBean personalBean) {
        this.personalBean = personalBean;
        login.setVisibility(View.GONE);
        ImageUtils.showCircleImage(getActivity(), HttpPath.IMAGEURL + personalBean.getUser_head(), headimage, R.mipmap.icon_me_headphoto);
        gender.setVisibility(View.VISIBLE);
        switch (personalBean.getSex()) {
            case "1":
                gender.setImageResource(R.mipmap.man);
                break;
            case "2":
                gender.setImageResource(R.mipmap.women);
                break;
        }
        userName.setVisibility(View.VISIBLE);
        userName.setText(personalBean.getUser_name());
        agent.setVisibility(View.VISIBLE);
        agent.setText(personalBean.getUid_name());
        showBadgeView(waitPayQBadgeView,waitPay,Integer.parseInt(personalBean.getW_pay()));
        showBadgeView(waitVerificationQBadgeView,waitVerification,Integer.parseInt(personalBean.getW_check()));
        showBadgeView(waitReceiptQBadgeView,waitReceipt,Integer.parseInt(personalBean.getW_get()));
        if (personalBean.getIs_safeagent().equals("1") || personalBean.getIs_spreadagent().equals("1"))
            agentiv.setVisibility(View.VISIBLE);

    }


    private void showBadgeView(QBadgeView qBadgeView, View bindView, int num) {
        if (num>0) {
            qBadgeView.bindTarget(bindView).setBadgeNumber(num)
                    .setShowShadow(false)
                    .setBadgePadding(3,true)
                    .setBadgeGravity(Gravity.END | Gravity.TOP)
                    .setBadgeTextSize(10,true)
            .setGravityOffset(10,0,true);
            qBadgeView.setVisibility(View.VISIBLE);
        }else {
            qBadgeView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoginView() {
        gender.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);
        agent.setVisibility(View.GONE);
        agentiv.setVisibility(View.GONE);
        userName.setVisibility(View.GONE);
        headimage.setImageResource(R.mipmap.icon_me_headphoto);
        waitReceiptQBadgeView.setVisibility(View.GONE);
        waitPayQBadgeView.setVisibility(View.GONE);
        waitVerificationQBadgeView.setVisibility(View.GONE);
    }

    @Override
    public void ShowFunction(List<AboutMeFragmentFunctionBean> list) {
        functionRVAdapter = new FunctionRVAdapter((BaseActivity) getActivity(), list);
        function.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        function.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        function.setNestedScrollingEnabled(false);
        function.setAdapter(functionRVAdapter);
    }

    public void setFabLoc(ViewPoint newLoc) {
        headRelative.setTranslationX(newLoc.x);
        headRelative.setTranslationY(newLoc.y);
    }

    @Override
    public void onDestroy() {
        aboutMeFragmentPresenter.detachView();
        super.onDestroy();
    }

    /**
     * 设置假状态栏高度
     */
    private void setStatusBar(View view, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
    }

}
