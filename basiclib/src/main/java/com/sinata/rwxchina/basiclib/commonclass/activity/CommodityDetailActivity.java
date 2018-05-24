package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.MyApplication;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentEntity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentListActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentListAdapter;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.CommodityDetailContract;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.CommodityDetailPresenter;
import com.sinata.rwxchina.basiclib.commonclass.adpter.CommodityAttrLabelsRVAdapter;
import com.sinata.rwxchina.basiclib.commonclass.adpter.CommodityIamgeRVAdapter;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.mallpayment.MallPayMentActivity;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：自营商城商品详情
 * @modifyRecord:修改记录
 */

public class CommodityDetailActivity extends BaseActivity implements CommodityDetailContract.View {
    private ImageView mBack;
    private TabLayout mTabTitle;
    private TextView mRightIconTv;
    private CommodityAttrLabelsRVAdapter commodityAttrLabelsRVAdapter;
    private FrameLayout frameLayout;
    private MyScrollView myScrollView;
    private ViewPager mCarouselViewpager;
    private LinearLayout mCarouselDots;
    private TextView mBrandCommodityDetailsType;
    private LinearLayout mBrandCommodityDetailsDefination;
    private TextView mBrandCommodityDetailsName;
    private TextView mBrandCommodityDetailsSubtitle;
    private TextView mBrandCommodityDetailsPricePre;
    private TextView mBrandCommodityDetailsPriceOri;
    private TextView mBrandCommodityDetailsNumber;
    private RecyclerView mBrandCommodityDetailsCondition;
    private ImageView mShopInformationLogo;
    private TextView mShopInformationName;
    private TextView mShopInformationType, lookMore;
    private LinearLayout mShopInformationCustomService;
    private LinearLayout mShopInformationGotoshop;
    private RecyclerView mBrandCommodityDetailsParticulars, commentlist;
    private CommodityBean commodityBean;
    private CommodityDetailPresenter commodityDetailPresenter;
    private CommodityIamgeRVAdapter commodityIamgeRVAdapter;
    private View comment;
    private ShopInfoBean shopInfoBean;
    private Button buy;
    private Map<String, String> comparams;
    private ImageView mPopCommodityImageIv;
    private TextView mOrderPayMoneyTv;
    private TextView mPopCommodityNameTv;
    private ImageView mPopCloseIv;
    private RelativeLayout mPopBrandConfimOrderAmountReduce;
    private EditText mActivityConfimOrderSureorderEdit;
    private RelativeLayout mPopBrandConfimOrderAmountAdd;
    private Button mConfirmOrderBtn;
    private PopupWindow popupWindow;
    private View fview;
    private int count = 1;
    private View statusBar,tabView;
    private boolean isScroll;
    private List<String> list;
    private TextView tvTabName;


    @Override
    public void initParms(Bundle params) {
        comparams = new HashMap<>();
        commodityBean = (CommodityBean) params.getSerializable("commodity");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_brand_commodity_details;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        fview = view;
        frameLayout = findViewById(R.id.carousel_frame);
        myScrollView = findViewById(R.id.commodityDetail_sv);
        mCarouselViewpager = findViewById(R.id.carousel_viewpager);
        mCarouselDots = findViewById(R.id.carousel_dots);
        mBrandCommodityDetailsType = findViewById(R.id.brand_commodity_details_type);
        mBrandCommodityDetailsDefination = findViewById(R.id.brand_commodity_details_defination);
        mBrandCommodityDetailsName = findViewById(R.id.brand_commodity_details_name);
        mBrandCommodityDetailsSubtitle = findViewById(R.id.brand_commodity_details_subtitle);
        mBrandCommodityDetailsPricePre = findViewById(R.id.brand_commodity_details_price_pre);
        mBrandCommodityDetailsPriceOri = findViewById(R.id.brand_commodity_details_price_ori);
        mBrandCommodityDetailsNumber = findViewById(R.id.brand_commodity_details_number);
        mBrandCommodityDetailsCondition = findViewById(R.id.brand_commodity_details_condition);
        mShopInformationLogo = findViewById(R.id.shop_information_logo);
        mShopInformationName = findViewById(R.id.shop_information_name);
        mShopInformationType = findViewById(R.id.shop_information_type);
        View shopView = view.findViewById(R.id.shopInfo_ll);
        mShopInformationCustomService = shopView.findViewById(R.id.shop_information_customService);
        mShopInformationGotoshop = shopView.findViewById(R.id.shop_information_gotoshop);
        mBrandCommodityDetailsParticulars = findViewById(R.id.brand_commodity_details_particulars);
        View titleView = view.findViewById(R.id.commodidty_tab_title);
        mBack = titleView.findViewById(R.id.back);
        mTabTitle = titleView.findViewById(R.id.tab_title);
        mRightIconTv = titleView.findViewById(R.id.rightIcon_tv);
        comment = view.findViewById(R.id.commodityDetail_userComment);
        commentlist = comment.findViewById(R.id.basic_shop_comment);
        lookMore = comment.findViewById(R.id.food_tv_comments);
        buy = view.findViewById(R.id.commodity_buy_btn);
        View popItem = LayoutInflater.from(this).inflate(R.layout.pop_confirmorder, null);
        popupWindow = new PopupWindow(popItem, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopCommodityImageIv = popItem.findViewById(R.id.pop_commodityImage_iv);
        mOrderPayMoneyTv = popItem.findViewById(R.id.order_payMoney_tv);
        mPopCommodityNameTv = popItem.findViewById(R.id.pop_commodityName_tv);
        mPopCloseIv = popItem.findViewById(R.id.pop_close_iv);
        mPopBrandConfimOrderAmountReduce = popItem.findViewById(R.id.pop_brand_confim_order_amount_reduce);
        mActivityConfimOrderSureorderEdit = popItem.findViewById(R.id.activity_confim_order_sureorder_edit);
        mPopBrandConfimOrderAmountAdd = popItem.findViewById(R.id.pop_brand_confim_order_amount_add);
        mConfirmOrderBtn = popItem.findViewById(R.id.confirmOrder_btn);
        statusBar = findViewById(R.id.tab_title_faker);
        tabView = LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        tvTabName =  tabView.findViewById(R.id.tv_tab_name);
    }

    @Override
    public void setListener() {
        mPopCloseIv.setOnClickListener(this);
        mConfirmOrderBtn.setOnClickListener(this);
        mTabTitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.e("CommodityDetailActivity","tab:"+tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        myScrollView.scrollTo(0,frameLayout.getTop());
                        myScrollView.smoothScrollTo(0, frameLayout.getTop());
                        break;
                    case 1:
                        myScrollView.scrollTo(0,comment.getTop());
                        myScrollView.smoothScrollTo(0, comment.getTop());
                        break;
                    case 2:
                        myScrollView.scrollTo(0,mBrandCommodityDetailsParticulars.getTop());
                        myScrollView.smoothScrollTo(0, mBrandCommodityDetailsParticulars.getTop());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        myScrollView.setScrollViewListener(new MyScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                   setTabTitle();
            }

            @Override
            public void onScrollStop(boolean isScrollStop) {
                if (isScrollStop)
                    isScroll = false;
                else
                    isScroll = true;
            }
        });
        buy.setOnClickListener(this);
        mShopInformationCustomService.setOnClickListener(this);
        mShopInformationGotoshop.setOnClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(CommodityDetailActivity.this, 1f);
            }
        });
        mActivityConfimOrderSureorderEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int i) {
                if (!TextUtils.isEmpty(s.toString().trim()))
                count = Integer.parseInt(s.toString().trim());
                else
                    count = -1;
                if (count<0){
                    count = 0;
                }else if (count == 0){
                    count = 1;
                }else if (count>99){
                    count = 99;
                    mActivityConfimOrderSureorderEdit.setText(count+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPopCloseIv.setOnClickListener(this);
        mConfirmOrderBtn.setOnClickListener(this);
        mPopBrandConfimOrderAmountReduce.setOnClickListener(this);
        mPopBrandConfimOrderAmountAdd.setOnClickListener(this);
        commentlist.setNestedScrollingEnabled(false);
        lookMore.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        Bundle bundle=new Bundle();
        if (i == R.id.shop_information_customService) {
            if (shopInfoBean!=null)
            CallPhoneUtils.call(this, shopInfoBean.getShopinfo().getShop_telephone());
        } else if (i == R.id.shop_information_gotoshop) {
            if (shopInfoBean != null) {
                bundle.putSerializable("shopInfo", shopInfoBean);
                startActivity(ShopDetailActivity.class, bundle);
            }
        } else if (i == R.id.commodity_buy_btn) {
            showPop();
        } else if (i == R.id.pop_close_iv) {
            popupWindow.dismiss();
        }else if(i==R.id.confirmOrder_btn){
            if (!UserUtils.isLogin(MyApplication.getContext())){
                return;
            }
            SinglePayment.getSinglePayment().setGoods_number(mActivityConfimOrderSureorderEdit.getText().toString());
            if (shopInfoBean!=null) {
            String shopinfo=new Gson().toJson(shopInfoBean);
            String commodity=new Gson().toJson(commodityBean);
            Intent intent=new Intent(this, MallPayMentActivity.class);

                bundle.putString("shopinfo", shopinfo);
                bundle.putString("commodity", commodity);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }else if (i == R.id.pop_brand_confim_order_amount_add){
            count++;
            if (count>99){
                count = 99;
            }
            mActivityConfimOrderSureorderEdit.setText(count+"");
        }else if (i == R.id.pop_brand_confim_order_amount_reduce){
            count--;
            if (count<1){
                count=1;
            }
            mActivityConfimOrderSureorderEdit.setText(count+"");
        }else if (i == R.id.food_tv_comments){
            bundle.putString("shopId",commodityBean.getShopid());
            startActivity(CommentListActivity.class,bundle);
        }
//        else if (i == R.id.contactCustomerService_btn){
//
//            CallPhoneUtils.call(this, shopInfoBean.getShopinfo().getShop_telephone());
//        }
    }

    private void showPop() {
        backgroundAlpha(this,0.3f);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setAnimationStyle(R.style.popbottom_anim_style);
        popupWindow.showAtLocation(fview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void doBusiness(Context mContext) {
        setmTabTitle();
        showView();
        setTitleBarView();
        commodityDetailPresenter = new CommodityDetailPresenter(this);
        commodityDetailPresenter.attachView(this);
        comparams = new HashMap<>();
        comparams.put("shopid", commodityBean.getShopid());
        commodityDetailPresenter.getCommentData(comparams);
        commodityDetailPresenter.getShopLogo(comparams);
    }

    private void showView() {

        ViewPagerUtils viewPagerUtils = new ViewPagerUtils(mCarouselDots, mCarouselViewpager, this, commodityBean.getAddition_image());
        viewPagerUtils.viewpagergo();
        mBrandCommodityDetailsName.setText(commodityBean.getGoods_name());
        mBrandCommodityDetailsSubtitle.setText(commodityBean.getGoods_subtitle());
        mBrandCommodityDetailsPricePre.setText(commodityBean.getGoods_price());
        mBrandCommodityDetailsPriceOri.setText("￥" + commodityBean.getGoods_market_price());
        mBrandCommodityDetailsPriceOri.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mBrandCommodityDetailsPriceOri.getPaint().setAntiAlias(true);
        ImageUtils.showImage(this, HttpPath.IMAGEURL + commodityBean.getDefault_image(), mPopCommodityImageIv);
        mOrderPayMoneyTv.setText("￥" + commodityBean.getGoods_price());
        mPopCommodityNameTv.setText(commodityBean.getGoods_name());
        mBrandCommodityDetailsNumber.setText(commodityBean.getGoods_sale());
        commodityAttrLabelsRVAdapter = new CommodityAttrLabelsRVAdapter(commodityBean.getGoods_labels_attr(), this);
        mBrandCommodityDetailsCondition.setLayoutManager(new GridLayoutManager(this, 4));
        mBrandCommodityDetailsCondition.setNestedScrollingEnabled(false);
        mBrandCommodityDetailsCondition.setAdapter(commodityAttrLabelsRVAdapter);
        commodityIamgeRVAdapter = new CommodityIamgeRVAdapter(this, commodityBean.getGoods_description_img());
        mBrandCommodityDetailsParticulars.setLayoutManager(new LinearLayoutManager(this));
        mBrandCommodityDetailsParticulars.setAdapter(commodityIamgeRVAdapter);
        mBrandCommodityDetailsParticulars.setNestedScrollingEnabled(false);
    }

    @Override
    public void showComment(List<CommentEntity> commentEntity) {
        List<CommentEntity> list;
        if (commentEntity.size() > 3) {
            list = commentEntity.subList(0, 4);
        } else {
            list = commentEntity;
        }
        CommentListAdapter adapter = new CommentListAdapter(this, R.layout.item_basic_comment, list);
        commentlist.setLayoutManager(new LinearLayoutManager(this));
        commentlist.setAdapter(adapter);

    }

    @Override
    public void showLogo(ShopInfoBean shopInfoBean) {
        this.shopInfoBean = shopInfoBean;
        ImageUtils.showImage(this, HttpPath.IMAGEURL + shopInfoBean.getShopinfo().getShop_logo(), mShopInformationLogo);
        mShopInformationName.setText(shopInfoBean.getShopinfo().getShop_name());

    }

    public void setmTabTitle() {
        mTabTitle.addTab(mTabTitle.newTab().setText("商品"));
        mTabTitle.addTab(mTabTitle.newTab().setText("评论"));
        mTabTitle.addTab(mTabTitle.newTab().setText("详情"));
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        commodityDetailPresenter.detachView();
        super.onDestroy();
    }

    private void setTabTitle(){
        Rect scrollBounds = new Rect();
        myScrollView.getHitRect(scrollBounds);
        if (frameLayout.getLocalVisibleRect(scrollBounds)&&!comment.getLocalVisibleRect(scrollBounds)) {
            if (!mTabTitle.getTabAt(0).isSelected()){
                mTabTitle.getTabAt(0).select();
            }
        } else if (!mBrandCommodityDetailsDefination.getLocalVisibleRect(scrollBounds)&&comment.getLocalVisibleRect(scrollBounds)){
            if (!mTabTitle.getTabAt(1).isSelected()){
                mTabTitle.getTabAt(1).select();
            }
        } else if (mBrandCommodityDetailsParticulars.getLocalVisibleRect(scrollBounds)&&!comment.getLocalVisibleRect(scrollBounds)){
            if (!mTabTitle.getTabAt(2).isSelected()){
                mTabTitle.getTabAt(2).select();
            }

        }

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
        StatusBarUtil.setTranslucentForImageViewInFragment(CommodityDetailActivity.this, null);
    }
}
