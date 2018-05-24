package com.sinata.rwxchina.component_home.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.commonclass.activity.InsuranceWebViewActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.MyCouponActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.ShareWebActivity;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.AppUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.view.BaseGridView;
import com.sinata.rwxchina.basiclib.view.HorizontalListView;
import com.sinata.rwxchina.basiclib.webViewUtils.WebViewUtils;
import com.sinata.rwxchina.basiclib.webViewUtils.activity.DefaultWebViewActivity;
import com.sinata.rwxchina.component_basic.basic.basiclist.BasicListActivity;
import com.sinata.rwxchina.component_basic.car.activity.CarListActivity;
import com.sinata.rwxchina.component_home.activity.BrandListActivity;
import com.sinata.rwxchina.component_home.activity.CommodityListActivity;
import com.sinata.rwxchina.component_home.entity.HotEntity;
import com.sinata.rwxchina.component_home.utils.CityUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.activity.BrandActivitiesActivity;
import com.sinata.rwxchina.component_home.activity.IconMoreAcitivity;
import com.sinata.rwxchina.component_home.activity.InsuranceListActivity;
import com.sinata.rwxchina.component_home.activity.ScanCodeActivity;
import com.sinata.rwxchina.component_home.activity.SearchActivity;
import com.sinata.rwxchina.component_home.adapter.HomeIconAdapter;
import com.sinata.rwxchina.component_home.adapter.InstrumentAdapter;
import com.sinata.rwxchina.component_home.adapter.LovelyAdapter;
import com.sinata.rwxchina.component_home.adapter.QualityBannerAdapter;
import com.sinata.rwxchina.component_home.entity.CheckLimitEntity;
import com.sinata.rwxchina.component_home.entity.IndexDataEntity;
import com.sinata.rwxchina.component_home.entity.LovelyListEntity;
import com.sinata.rwxchina.component_home.entity.NewsEntity;
import com.sinata.rwxchina.component_home.entity.TopBannerEntity;
import com.sinata.rwxchina.component_home.utils.HomeIconJump;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;
import static com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils.darkMode;


/**
 * 首页布局
 * Created by HRR on 2017/10/16.
 */

public class HomeFragment extends BaseFragment {
    private IndexDataEntity indexData;
    /**
     * 滑动控件
     */
    private AppBarLayout appBarLayout;
    /**
     * 顶部搜索栏
     */
    private RelativeLayout search_top;
    private TextView search_tv;
    private View statusBar;
    private TextView myCar;
    /**
     * 下拉刷新上拉加载控件
     */
    private SmartRefreshLayout smartRefresh;
    private int lastX;
    private int lastY;
    private boolean isRefresh = false;
    /**
     * 首页顶部banner
     */
    private ViewPager topBannerVp;
    private LinearLayout topBannerLl;
    private ViewPagerUtils viewPagerUtils;
    /**
     * 首页icon
     */
    private BaseGridView homeIcon;
    /**
     * 首页icon适配器
     */
    private HomeIconAdapter iconAdapter;
    /**
     * 实时状态
     */
    private TextSwitcher switcher;
    /**
     * news的title和url
     */
    private String title, newsURL;
    private int id = 0;
    /**
     * 新闻集合
     */
    private List<NewsEntity> news;
    /**
     * 火爆活动区图片
     */
    private ImageView hotOne, hotTwo, hotThree, more;
    /**
     * 品质生活区图片
     */
    private ImageView qualityOne, qualityTwo;
    private HorizontalListView qualityListView;
    private QualityBannerAdapter qualityAdapter;
    /**
     * 品牌配件区
     */
    private LinearLayout brandOne, brandTwo, brandThree, brandFour, brandFive;
    private ImageView brandBanner, brandOne_img, brandTwo_img, brandThree_img, brandFour_img, brandFive_img, more_accessory;
    private TextView brandOne_title, brandTwo_title, brandThree_title, brandFour_title, brandFive_title, brandOne_sub, brandTwo_sub, brandThree_sub, brandFour_sub, brandFive_sub;
    /**
     * 猜你喜欢
     */
    private NestedScrollView loveScrollView;
    private TabLayout tabLayout;
    private ViewPager lovelyVp;
    private LovelyAdapter lovelyAdapter;
    /**
     * 标题栏
     */
    private ImageView instrument;
    private PopupWindow instrument_pop;
    private View contentview;
    private ListView pop_list;
    private ImageView arrow_down;
    private TextView city_tv;
    private LinearLayout top_search_linear;
    /**
     * 城市选择
     */
    private LinearLayout location;
    private CityInfoBean cityInfoBean;
    private String cityName;

    /**
     * 今日限行
     */
    private String pinyinCity;
    private LinearLayout limitline;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        //添加默认的配置
        CityListLoader.getInstance().loadCityData(getContext());
        appBarLayout = view.findViewById(R.id.home_appbar);
        search_top = view.findViewById(R.id.home_titlebar);
        search_tv = view.findViewById(R.id.search_tv);
        //设置搜索栏获取焦点，防止一进入页面edittext获取焦点弹出键盘
        search_top.setFocusable(true);
        search_top.setFocusableInTouchMode(true);
        statusBar = view.findViewById(R.id.status_bar);
        myCar = view.findViewById(R.id.fragment_home_perfectModels);
        //设置假状态栏高度
        setStatusBar(statusBar, ScreenUtils.getStatusHeight(getActivity()));
        smartRefresh = view.findViewById(R.id.home_smartRefresh);
        setRefresh();
        topBannerVp = view.findViewById(R.id.home_banner_viewPager);
        topBannerLl = view.findViewById(R.id.home_dotGroup);
        homeIcon = view.findViewById(R.id.home_icon);
        iconAdapter = new HomeIconAdapter(getContext());
        news = new ArrayList<NewsEntity>();
        switcher = view.findViewById(R.id.home_news_ts);
        hotOne = view.findViewById(R.id.home_hotarea_img_one);
        hotTwo = view.findViewById(R.id.home_hotarea_img_two);
        hotThree = view.findViewById(R.id.home_hotarea_img_three);
        qualityOne = view.findViewById(R.id.home_qualitylife_img_one);
        qualityTwo = view.findViewById(R.id.home_qualitylife_img_two);
        qualityListView = view.findViewById(R.id.home_qualitylife_listview);
        setHorizontalListView();
        brandBanner = view.findViewById(R.id.brandBanner);
        brandOne = view.findViewById(R.id.brand_one);
        brandOne_img = view.findViewById(R.id.brand_one_img);
        brandOne_title = view.findViewById(R.id.brand_one_title);
        brandOne_sub = view.findViewById(R.id.brand_one_subtitle);
        brandTwo = view.findViewById(R.id.brand_two);
        brandTwo_img = view.findViewById(R.id.brand_two_img);
        brandTwo_title = view.findViewById(R.id.brand_two_title);
        brandTwo_sub = view.findViewById(R.id.brand_two_subtitle);
        brandThree = view.findViewById(R.id.brand_three);
        brandThree_img = view.findViewById(R.id.brand_three_img);
        brandThree_title = view.findViewById(R.id.brand_three_title);
        brandThree_sub = view.findViewById(R.id.brand_three_subtitle);
        brandFour = view.findViewById(R.id.brand_four);
        brandFour_img = view.findViewById(R.id.brand_four_img);
        brandFour_title = view.findViewById(R.id.brand_four_title);
        brandFour_sub = view.findViewById(R.id.brand_four_subtitle);
        brandFive = view.findViewById(R.id.brand_five);
        brandFive_img = view.findViewById(R.id.brand_five_img);
        brandFive_title = view.findViewById(R.id.brand_five_title);
        brandFive_sub = view.findViewById(R.id.brand_five_subtitle);
        more_accessory = view.findViewById(R.id.home_more_accessory);
        loveScrollView = view.findViewById(R.id.love_nestedscrollview);
        loveScrollView.setFillViewport(true);
        tabLayout = view.findViewById(R.id.home_guessYouLike_tab);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        lovelyVp = view.findViewById(R.id.home_guessYouLike_lv);
        more = view.findViewById(R.id.home_more_hotarea);
        instrument = view.findViewById(R.id.home_convenient_img);
        arrow_down = view.findViewById(R.id.home_location_more);
        city_tv = view.findViewById(R.id.home_location_tv);
        top_search_linear = view.findViewById(R.id.home_serch_linear);
        location = view.findViewById(R.id.home_location_linear);
        limitline = view.findViewById(R.id.home_today_limit_linear);
        cityName = LocationUtils.getCity(getContext());
        qualityAdapter = new QualityBannerAdapter(getContext());
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                return textView;
            }
        });
        switcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_up));

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        LogUtils.e("HomeFragment");
    }

    @Override
    public void setListener() {
        more.setOnClickListener(this);
        instrument.setOnClickListener(this);
        search_tv.setOnClickListener(this);
        location.setOnClickListener(this);
        hotOne.setOnClickListener(this);
        hotTwo.setOnClickListener(this);
        hotThree.setOnClickListener(this);
        qualityOne.setOnClickListener(this);
        qualityTwo.setOnClickListener(this);
        more_accessory.setOnClickListener(this);
        brandOne.setOnClickListener(this);
        brandTwo.setOnClickListener(this);
        brandThree.setOnClickListener(this);
        brandFour.setOnClickListener(this);
        brandFive.setOnClickListener(this);
        myCar.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        Bundle bundle = new Bundle();
        if (i == R.id.home_more_hotarea) {
            startActivity(BrandActivitiesActivity.class);
        } else if (i == R.id.home_convenient_img) {
            setPopInstrument(v);
        } else if (i == R.id.search_tv) {
            startActivity(SearchActivity.class);
        } else if (i == R.id.home_location_linear) {
            Intent intent = new Intent(getContext(), CityListSelectActivity.class);
            startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
        } else if (i == R.id.home_hotarea_img_one) {
            if(indexData.getHuobao().size() > 0){
                //判断是否需要登录 1：需要 0：不需要
                if ("1".equals(indexData.getHuobao().get(0).getIs_must_login())) {
                    if (!UserUtils.isLogin(getActivity())) {
                        return;
                    }
                }
            }
                LogUtils.e("HomeIconJump","审车");
                Intent intent=new Intent(getActivity(),InsuranceWebViewActivity.class);
                Bundle shenche=new Bundle();
                shenche.putString("url", indexData.getHuobao().get(0).getUrl());
                if (AppUtils.isLogin((BaseActivity) getActivity())){
                    shenche.putString("uid", CommonParametersUtils.getUid(getContext()));
                }
                shenche.putString("token", CommonParametersUtils.getToken(getContext()));
                intent.putExtras(shenche);
                startActivity(intent);
                return;
//            setHotActivity(indexData.getHuobao().get(0), bundle);
        } else if (i == R.id.home_hotarea_img_two) {
            if ("1".equals(indexData.getHuobao().get(1).getIs_must_login())) {
                if (!UserUtils.isLogin(getActivity())) {
                    return;
                }
            }
            setHotActivity(indexData.getHuobao().get(1), bundle);
        } else if (i == R.id.home_hotarea_img_three) {
            if ("1".equals(indexData.getHuobao().get(2).getIs_must_login())) {
                if (!UserUtils.isLogin(getActivity())) {
                    return;
                }
            }
            setHotActivity(indexData.getHuobao().get(2), bundle);
        } else if (i == R.id.home_qualitylife_img_one) {
            bundle.putString("iconLabel", indexData.getPinzhi().get(0).getIcon_label());
            bundle.putString("shopType", indexData.getPinzhi().get(0).getShop_type());
            bundle.putString("shopTypeLabels", indexData.getPinzhi().get(0).getShop_type_labels());
            bundle.putString("title", indexData.getPinzhi().get(0).getTitle());
            startActivity(BasicListActivity.class, bundle);
        } else if (i == R.id.home_qualitylife_img_two) {
            bundle.putString("iconLabel", indexData.getPinzhi().get(1).getIcon_label());
            bundle.putString("shopType", indexData.getPinzhi().get(1).getShop_type());
            bundle.putString("shopTypeLabels", indexData.getPinzhi().get(1).getShop_type_labels());
            bundle.putString("title", indexData.getPinzhi().get(1).getTitle());
            startActivity(BasicListActivity.class, bundle);
        } else if (i == R.id.home_more_accessory) {
            startActivity(BrandListActivity.class);
        } else if (i == R.id.brand_one) {
            bundle.putString("goods_type", indexData.getPinpai().get(0).getGoods_type());
            bundle.putString("title", indexData.getPinpai().get(0).getName());
            bundle.putBoolean("isHome", true);
            startActivity(CommodityListActivity.class, bundle);
        } else if (i == R.id.brand_two) {
            bundle.putString("goods_type", indexData.getPinpai().get(1).getGoods_type());
            bundle.putString("title", indexData.getPinpai().get(1).getName());
            bundle.putBoolean("isHome", true);
            startActivity(CommodityListActivity.class, bundle);
        } else if (i == R.id.brand_three) {
            bundle.putString("goods_type", indexData.getPinpai().get(2).getGoods_type());
            bundle.putString("title", indexData.getPinpai().get(2).getName());
            bundle.putBoolean("isHome", true);
            startActivity(CommodityListActivity.class, bundle);
        } else if (i == R.id.brand_four) {
            bundle.putString("goods_type", indexData.getPinpai().get(3).getGoods_type());
            bundle.putString("title", indexData.getPinpai().get(3).getName());
            bundle.putBoolean("isHome", true);
            startActivity(CommodityListActivity.class, bundle);
        } else if (i == R.id.brand_five) {
            bundle.putString("goods_type", indexData.getPinpai().get(4).getGoods_type());
            bundle.putString("title", indexData.getPinpai().get(4).getName());
            bundle.putBoolean("isHome", true);
            startActivity(CommodityListActivity.class, bundle);
        } else if (i == R.id.fragment_home_perfectModels) {
            bundle.putString("url", "http://chengdu.cdrwx.cn/h5/acdd_user/html/myCar.html");
            if (UserUtils.isLogin(getActivity())) {
                startActivity(DefaultWebViewActivity.class, bundle);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getCarNumber();
    }


    private void getCarNumber() {
        ApiManager apiManager = new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    myCar.setText(jsonArray.getJSONObject(0).optString("region")+jsonArray.getJSONObject(0).optString("car_num"));
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String, String> map = new HashMap<>();
        apiManager.get(HttpPath.MYCAR_SHOW, map);
    }

    @Override
    public void doBusiness(Context mContext) {
        CityUtils.setCity(location, (BaseActivity) getActivity(), cityName);
        getLimitCity();
        //获取首页内容
        getIndex();
        setTitleBar();
    }

    private void setHotActivity(HotEntity hotActivity, Bundle bundle) {
        bundle.putString("url", hotActivity.getUrl());
        if (hotActivity.getIs_share().equals("1")) {
            bundle.putString("url_share", hotActivity.getUrl_share());
            bundle.putString("uid", CommonParametersUtils.getUid(getActivity()));
            bundle.putString("content", hotActivity.getShare_content());
            bundle.putString("share_title", hotActivity.getShare_name());
            bundle.putBoolean("isTitleShare", true);
            startActivity(ShareWebActivity.class, bundle);
//            startActivity(DefaultWebViewActivity.class, bundle);
        } else {
            if (hotActivity.getIs_head().equals("1"))
                bundle.putBoolean("isTitle",true);
            startActivity(DefaultWebViewActivity.class,bundle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefresh.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
//                LogUtils.e("HomeFragment","percent:"+percent+"  offset:"+offset+"  headerHeight:"+headerHeight+"  extendHeight:"+extendHeight);
                if (offset > 0) {
                    search_top.setVisibility(View.GONE);
                } else {
                    search_top.setVisibility(View.VISIBLE);
                }
                super.onHeaderPulling(header, percent, offset, headerHeight, extendHeight);
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int footerHeight, int extendHeight) {
//                LogUtils.e("HomeFragment","percent:"+percent+"  offset:"+offset+"  footerHeight:"+footerHeight+"  extendHeight:"+extendHeight);

                if (offset <= 0) {
                    search_top.setVisibility(View.VISIBLE);
                } else {
                    search_top.setVisibility(View.GONE);
                }
                super.onHeaderReleasing(header, percent, offset, footerHeight, extendHeight);
            }
        });
    }

    /**
     * 获取首页内容
     */
    private void getIndex() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cityid", "1");
        params.put("lng", LocationUtils.getLng(getContext()));
        params.put("lat", LocationUtils.getLat(getContext()));
        LogUtils.e("zzz",LocationUtils.getLng(getContext())+"   "+LocationUtils.getLat(getContext()));
        ApiManager apiManager = new ApiManager((BaseActivity) this.getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                indexData = gson.fromJson(result, IndexDataEntity.class);
                setTopBanner();
                setIcon();
                startNews();
                setHot();
                setQuality();
                setBrand();
                setLovelyCateList();
                smartRefresh.finishRefresh();
            }

            @Override
            public void onResultError(ApiException e, String method) {
            }
        });
        apiManager.post(HttpPath.INDEX, params);
    }

    /**
     * 设置顶部banner图
     */
    private void setTopBanner() {
        final List<HotEntity> topBanner = indexData.getBanner();
        List<String> imgs = new ArrayList<String>();
        for (int i = 0; i < topBanner.size(); i++) {
            imgs.add(topBanner.get(i).getUrlsmall());
        }
        if (isRefresh) {
//            viewPagerUtils.setImglist(imgs);
//            viewPagerUtils.change();
//            viewPagerUtils.restart();
        } else {
            viewPagerUtils = new ViewPagerUtils(topBannerLl, topBannerVp, getActivity(), imgs, new ViewPagerUtils.ViewPagerClickListener() {
                @Override
                public void onClick(int postion) {
                    HotEntity topBannerEntity = topBanner.get(postion - 1);
                    String url = topBannerEntity.getUrl();
                    if (!TextUtils.isEmpty(url)) {
                        Bundle bundle = new Bundle();
                        setHotActivity(topBannerEntity, bundle);
                    }
                }
            });
            viewPagerUtils.viewpagergo();
        }
    }

    /**
     * 设置首页icon
     */
    private void setIcon() {
        final List<BaseIconEntity> iconInfo = indexData.getIcon();
        iconAdapter.setIcons(indexData.getIcon());
        homeIcon.setAdapter(iconAdapter);
        homeIcon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //判断是否需要登录 1：需要 0：不需要
                if ("1".equals(iconInfo.get(position).getIs_must_login())) {
                    if (!UserUtils.isLogin(getActivity())) {
                        return;
                    }
                }
                Bundle bundle = new Bundle();
                String iconLabel = iconInfo.get(position).getIcon_label();
                bundle.putString("shopType", iconInfo.get(position).getShop_type());
                bundle.putString("shopTypeLabels", iconInfo.get(position).getShop_type_labels());
                bundle.putString("iconLabel", iconLabel);
                bundle.putString("title", iconInfo.get(position).getTitle());
                bundle.putString("url", iconInfo.get(position).getUrl());
                HomeIconJump.jump(iconInfo.get(position), getActivity(), null, bundle);

            }
        });
    }

    /**
     * 更新news内容
     *
     * @param news
     */
    private void updateText(NewsEntity news) {
        newsURL = news.getUrl();
        title = news.getTitle();
        String s2 = news.getContent();
        StringBuffer sb = new StringBuffer();
//        sb.append(title).append("\n").append(s2);
        sb.append(s2);
        SpannableString string = new SpannableString(sb);
        string.setSpan(new AbsoluteSizeSpan(18, true), 0, title.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, title.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(new AbsoluteSizeSpan(13, true), title.length(), string.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        switcher.setText(string);
    }

    /**
     * 开始更新新闻
     */
    private void startNews() {
        news = indexData.getNews();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 1, 5000);//每5秒更新
    }

    private int next() {
        int flag = id + 1;
        if (flag > news.size() - 1) {
            flag = flag - news.size();
        }
        return flag;
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);

        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    id = next(); //更新Id值
                    updateText(news.get(id));  //更新TextSwitcherd显示内容;
                    break;
            }
        }
    };

    /**
     * 设置火爆专区
     */
    private void setHot() {
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + indexData.getHuobao().get(0).getUrlsmall(), hotOne);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + indexData.getHuobao().get(1).getUrlsmall(), hotTwo);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + indexData.getHuobao().get(2).getUrlsmall(), hotThree);
    }

    /**
     * 设置品质生活区
     */
    private void setQuality() {
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + indexData.getPinzhi().get(0).getImg(), qualityOne);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + indexData.getPinzhi().get(1).getImg(), qualityTwo);
        qualityAdapter.setQuality(indexData.getPinzhi_banner());
        qualityListView.setAdapter(qualityAdapter);
        qualityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 设置HorizontallListView
     */
    private void setHorizontalListView() {
        //解决滑动冲突
        smartRefresh.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                qualityListView.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        qualityListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                qualityListView.getParent().requestDisallowInterceptTouchEvent(true);

                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaY = y - lastY;
                        int deltaX = x - lastX;
                        if (Math.abs(deltaX) < Math.abs(deltaY)) {
                            qualityListView.getParent().requestDisallowInterceptTouchEvent(false);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            qualityListView.getParent().requestDisallowInterceptTouchEvent(true);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    default:
                        break;
                }

                return false;
            }
        });
    }

    /**
     * 设置品牌配件区
     */
    private void setBrand() {
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai_banner().get(0).getUrlsmall(), brandBanner);
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai().get(0).getLogo(), brandOne_img);
        brandOne_title.setText(indexData.getPinpai().get(0).getName());
        brandOne_sub.setText(indexData.getPinpai().get(0).getDescribe());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai().get(1).getLogo(), brandTwo_img);
        brandTwo_title.setText(indexData.getPinpai().get(1).getName());
        brandTwo_sub.setText(indexData.getPinpai().get(1).getDescribe());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai().get(2).getLogo(), brandThree_img);
        brandThree_title.setText(indexData.getPinpai().get(2).getName());
        brandThree_sub.setText(indexData.getPinpai().get(2).getDescribe());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai().get(3).getLogo(), brandFour_img);
        brandFour_title.setText(indexData.getPinpai().get(3).getName());
        brandFour_sub.setText(indexData.getPinpai().get(3).getDescribe());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + indexData.getPinpai().get(4).getLogo(), brandFive_img);
        brandFive_title.setText(indexData.getPinpai().get(4).getName());
        brandFive_sub.setText(indexData.getPinpai().get(4).getDescribe());

    }

    /**
     * 设置猜你喜欢类别列表
     */
    private void setLovelyCateList() {
        List<LovelyListEntity> lovelyList = indexData.getXihuan();
        showLog(lovelyList.toString());
        List<Fragment> views = new ArrayList<Fragment>();
        for (int i = 0; i < lovelyList.size(); i++) {
            Bundle bundle = new Bundle();
            if (i == 0) {
//                bundle.putString("shop_type",lovelyList.get(i).getShop_type());
//                bundle.putString("shop_type_labels",lovelyList.get(i).getShop_type_labels());
                bundle.putSerializable("data", new Gson().toJson(indexData.getXihuan_temai()));
                SaleFragment saleFragment = new SaleFragment();
                saleFragment.setArguments(bundle);
                views.add(saleFragment);
            } else {
                bundle.putString("shop_type", lovelyList.get(i).getShop_type());
                bundle.putString("shop_type_labels", lovelyList.get(i).getShop_type_labels());
                OtherLovelyFragment otherLovelyFragment = new OtherLovelyFragment();
                otherLovelyFragment.setArguments(bundle);
                views.add(otherLovelyFragment);
            }
        }
        LogUtils.e(views.size() + "");
        lovelyAdapter = new LovelyAdapter(getChildFragmentManager(), views, lovelyList);
        lovelyVp.setAdapter(lovelyAdapter);
        tabLayout.setupWithViewPager(lovelyVp);
    }


    /**
     * 设置假状态栏高度
     */
    private void setStatusBar(View view, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
    }

    /**
     * 设置顶部标题栏
     */
    private void setTitleBar() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    statusBar.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    search_top.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    instrument.setImageResource(R.mipmap.icon_home_convenient);
                    arrow_down.setImageResource(R.mipmap.icon_home_arrowdown);
                    city_tv.setTextColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        top_search_linear.setBackground(getResources().getDrawable(R.drawable.home_head_coner));
                    }
                } else if (verticalOffset < 0 && verticalOffset > -100) {
                    float scale = (float) verticalOffset / 100;
                    float alpha = (255 * scale);
                    statusBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    search_top.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    instrument.setImageResource(R.mipmap.icon_home_convenient_black);
                    arrow_down.setImageResource(R.mipmap.icon_home_arrowdown_black);
                    city_tv.setTextColor(getResources().getColor(R.color.black));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        top_search_linear.setBackground(getResources().getDrawable(R.drawable.home_head_coner_gray));
                    }
                    darkMode(getActivity().getWindow(), false);
                } else {
                    statusBar.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    search_top.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    darkMode(getActivity().getWindow(), true);
                }


            }
        });
    }

    /**
     * 设置右上角弹出框
     */

    private void setPopInstrument(View view) {
        if (instrument_pop == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            contentview = layoutInflater.inflate(R.layout.pop_home_instrument, null);
            pop_list = contentview.findViewById(R.id.pop_home_instrument);
            List<String> instrument_list = new ArrayList<>();
            instrument_list.add("扫一扫");
            instrument_list.add("找客服");
            instrument_list.add("代金券");
            InstrumentAdapter instrumentAdapter = new InstrumentAdapter(getContext(), (ArrayList<String>) instrument_list);
            pop_list.setAdapter(instrumentAdapter);
            instrument_pop = new PopupWindow(contentview, getActivity().getWindowManager().getDefaultDisplay().getWidth() / 3, WindowManager.LayoutParams.WRAP_CONTENT);
        }
        instrument_pop.setBackgroundDrawable(new BitmapDrawable());
        instrument_pop.setFocusable(true);
        int xPos = getActivity().getWindowManager().getDefaultDisplay().getWidth() / 16;
        instrument_pop.showAsDropDown(view, xPos, 0);

        pop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (instrument_pop != null) {
                    instrument_pop.dismiss();
                }
                if (i == 0) {
                    startActivity(ScanCodeActivity.class);
                }
                if (i == 1) {
                    CallPhoneUtils.call(getContext(), "4008004656");
                }
                if (i == 2) {
                    startActivity(MyCouponActivity.class);
                }
            }
        });
    }

    /**
     * 城市选择回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                cityInfoBean = bundle.getParcelable("cityinfo");
                if (null == cityInfoBean) {
                    return;
                }
                CityUtils.setCity(location, (BaseActivity) getActivity(), cityInfoBean.getName());
                CityUtils.setLimit(limitline, (BaseActivity) getActivity(), cityInfoBean.getPinYin());
            }

        }
    }

    /**
     * 限行
     */
    private void getLimitCity() {
        Map<String, String> params = new HashMap<>();
        ApiManager apiManager = new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                List<CheckLimitEntity> list = gson.fromJson(result, new TypeToken<List<CheckLimitEntity>>() {
                }.getType());
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        String names = list.get(i).getCityname();
                        if ((cityName.indexOf(names)) != -1) {
                            pinyinCity = list.get(i).getCity();
                            CityUtils.setLimit(limitline, (BaseActivity) getActivity(), pinyinCity);
                        }
                    }

                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.CHECKLIMIT, params);
    }

    private void setRefresh() {
        smartRefresh.setEnableLoadmore(false);
        smartRefresh.setNestedScrollingEnabled(true);
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
                CityUtils.setCity(location, (BaseActivity) getActivity(), cityName);
                getLimitCity();
                //获取首页内容
                getIndex();
            }
        });
    }

}

