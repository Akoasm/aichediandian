package com.sinata.rwxchina.component_entertainment.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;
import com.sinata.rwxchina.basiclib.view.HorizontalListView;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.jumputils.IconJumpUtils;
import com.sinata.rwxchina.component_basic.scenic.activity.ScenicActivity;
import com.sinata.rwxchina.component_entertainment.R;
import com.sinata.rwxchina.component_entertainment.adapter.HealthAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.HotelAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.IconAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.FoodAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.KtvAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.ScenicBodyAdapter;
import com.sinata.rwxchina.component_entertainment.adapter.ScenicPagerAdapter;
import com.sinata.rwxchina.component_entertainment.entity.EntertainmentDataEntity;
import com.sinata.rwxchina.component_entertainment.entity.NearFoodEntity;
import com.sinata.rwxchina.component_entertainment.entity.NearHealthEntity;
import com.sinata.rwxchina.component_entertainment.entity.NearHotelEntity;
import com.sinata.rwxchina.component_entertainment.entity.NearKtvEntity;
import com.sinata.rwxchina.component_entertainment.entity.NearScenicEntity;
import com.sinata.rwxchina.component_entertainment.entity.PopularityEntity;
import com.sinata.rwxchina.component_entertainment.entity.RecommendEntity;
import com.sinata.rwxchina.component_entertainment.entity.TopBannerEntity;
import com.sinata.rwxchina.component_entertainment.utils.ShopJumpUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HRR on 2017/10/16.
 * 娱乐主页
 */

public class EntertainmentFragment extends BaseFragment {
    //下拉刷新控件
    private SmartRefreshLayout smartRefresh;
    private int lastX;
    private int lastY;
    private MyScrollView scrollView;
    /**
     * 标题栏
     */
    private View statusBar;
    private RelativeLayout titleBar;
    //  娱乐实体类
    private EntertainmentDataEntity entertainmentData;
    // 娱乐顶部banner
    private ViewPager topBannerVp;
    private LinearLayout topBannerLl;
    // 娱乐icon
    private RecyclerView entertainmentIcon;
    // 娱乐icon适配器
    private IconAdapter iconAdapter;
    //今日推荐
    private TextSwitcher switcher;
    private List<RecommendEntity> recommend;
    private int id = 0;
    //人气排行
    private ImageView popularity_one_img, popularity_two_img, popularity_three_img, popularity_four_img;
    private TextView popularity_one_tv, popularity_two_tv, popularity_three_tv, popularity_four_tv;
    private LinearLayout populatiry_linear;
    List<PopularityEntity> popularityEntity;
    //美食
    private RecyclerView foodRecycler;
    private FoodAdapter foodAdapter;
    //酒店
    private RecyclerView hotelRecycler;
    private ImageView hotel_left_img, hotel_right_img;
    private TextView hotel_left_tv, hotel_right_tv;
    private HotelAdapter hotelAdapter;
    private LinearLayout hotel_toplinear,hotel_linear;

    List<NearHotelEntity.TopBean> hotelTop;
    //KTV
    private HorizontalListView ktvList;
    private ImageView ktv_left_img, ktv_right_img;
    private TextView ktv_left_tv, ktv_right_tv;
    private KtvAdapter ktvAdapter;
    private LinearLayout ktv_toplinear,ktv_linear;
    List<NearKtvEntity.TopBean> ktvTop;
    //养生
    private ImageView health_img;
    private TextView healthTop_name, healthTop_address, healthTop_mile, healthTop_score, healthTop_price;
    private RatingBar healthTop_rate;
    private HorizontalListView healthList;
    private HealthAdapter healthAdapter;
    private LinearLayout health_toplinear,health_linear;
    List<NearHealthEntity.TopBean> healthTop;
    //周边游
    private ViewPager scenicViewpager;
    private RecyclerView scenicRecycler;
    private ScenicBodyAdapter scenicBodyAdapter;
    private ScenicPagerAdapter scenicPagerAdapter;
    private LinearLayout scenic_toplinear;
    private LinearLayout scenic_line;
    private View lines[];
    private List<NearScenicEntity.TopBean> scenicTop;
    private int currentImgIndex;

    private String shopId;
    private String shop_name;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_entertainment;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        smartRefresh = view.findViewById(R.id.entertainment_smartRefresh);
        smartRefresh.setEnableLoadmore(false);
        statusBar = view.findViewById(R.id.entertainment_fakeview);
        scrollView = view.findViewById(R.id.entertainment_scroll);
        titleBar = view.findViewById(R.id.yule_top);
        topBannerLl = view.findViewById(R.id.entertainment_dotGroup);
        topBannerVp = view.findViewById(R.id.entertainment_banner_viewPager);
        entertainmentIcon = view.findViewById(R.id.entertainment_icon);
        entertainmentIcon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        switcher = view.findViewById(R.id.entertaiment_recommend);
        popularity_one_img = view.findViewById(R.id.popularity_one_image);
        popularity_one_tv = view.findViewById(R.id.popularity_one_name);
        popularity_two_img = view.findViewById(R.id.popularity_two_image);
        popularity_two_tv = view.findViewById(R.id.popularity_two_name);
        popularity_three_img = view.findViewById(R.id.popularity_three_image);
        popularity_three_tv = view.findViewById(R.id.popularity_three_name);
        popularity_four_img = view.findViewById(R.id.popularity_four_image);
        popularity_four_tv = view.findViewById(R.id.popularity_four_name);
        populatiry_linear = view.findViewById(R.id.entertainment_popularity_linear);
        foodRecycler = view.findViewById(R.id.entertainment_food_recycler);
        foodRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        hotelRecycler = view.findViewById(R.id.entertainment_hotel_recycler);
        hotelRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        hotel_left_img = view.findViewById(R.id.hotel_head_image_left);
        hotel_left_tv = view.findViewById(R.id.hotel_head_name_left);
        hotel_right_img = view.findViewById(R.id.hotel_head_image_right);
        hotel_right_tv = view.findViewById(R.id.hotel_head_name_right);
        hotel_toplinear = view.findViewById(R.id.entertainment_hotel_top);
        hotel_linear = view.findViewById(R.id.entertainment_hotel_linear);
        ktvList = view.findViewById(R.id.entertainment_ktv_list);
        ktv_left_img = view.findViewById(R.id.entertainment_ktv_image_left);
        ktv_left_tv = view.findViewById(R.id.entertainment_ktv_name_left);
        ktv_right_img = view.findViewById(R.id.entertainment_ktv_image_right);
        ktv_right_tv = view.findViewById(R.id.entertainment_ktv_name_right);
        ktv_toplinear = view.findViewById(R.id.entertainment_ktv_top);
        ktv_linear = view.findViewById(R.id.entertainment_ktv_linear);
        health_img = view.findViewById(R.id.entertainment_health_image);
        healthTop_name = view.findViewById(R.id.entertainment_health_name);
        healthTop_address = view.findViewById(R.id.entertainment_health_address);
        healthTop_mile = view.findViewById(R.id.entertaiment_health_miles);
        healthTop_rate = view.findViewById(R.id.entertainment_health_rating);
        healthTop_score = view.findViewById(R.id.entertainment_health_score);
        healthTop_price = view.findViewById(R.id.entertainment_health_price);
        healthList = view.findViewById(R.id.entertainment_health_list);
        health_toplinear = view.findViewById(R.id.entertianment_health_top);
        health_linear = view.findViewById(R.id.entertainment_health_linear);
        SolveConflict();
        scenicViewpager = view.findViewById(R.id.entertainment_scenic_viewpager);
        scenicRecycler = view.findViewById(R.id.entertainment_scenic_recycler);
        scenicRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        scenic_line = view.findViewById(R.id.entertainment_scenic_line);
        scenic_toplinear = view.findViewById(R.id.entertaiment_scenic_linear);
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
    }

    @Override
    public void setListener() {
        popularity_one_img.setOnClickListener(this);
        popularity_two_img.setOnClickListener(this);
        popularity_three_img.setOnClickListener(this);
        popularity_four_img.setOnClickListener(this);
        hotel_right_img.setOnClickListener(this);
        hotel_left_img.setOnClickListener(this);
        ktv_right_img.setOnClickListener(this);
        ktv_left_img.setOnClickListener(this);
        health_img.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id = v.getId();
        Bundle bundle = new Bundle();
        if (id == R.id.popularity_one_image) {
            ShopJumpUtils.toJump(getContext(), popularityEntity.get(0).getShop_type(), popularityEntity.get(0).getShopid(), popularityEntity.get(0).getShop_name());
        } else if (id == R.id.popularity_two_image) {
            ShopJumpUtils.toJump(getContext(), popularityEntity.get(1).getShop_type(), popularityEntity.get(1).getShopid(), popularityEntity.get(1).getShop_name());
        } else if (id == R.id.popularity_three_image) {
            ShopJumpUtils.toJump(getContext(), popularityEntity.get(2).getShop_type(), popularityEntity.get(2).getShopid(), popularityEntity.get(2).getShop_name());
        } else if (id == R.id.popularity_four_image) {
            ShopJumpUtils.toJump(getContext(), popularityEntity.get(3).getShop_type(), popularityEntity.get(3).getShopid(), popularityEntity.get(3).getShop_name());
        } else if (id == R.id.hotel_head_image_right) {
            ShopJumpUtils.toJump(getContext(), hotelTop.get(1).getShop_type(), hotelTop.get(1).getShopid(), hotelTop.get(1).getShop_name());
        } else if (id == R.id.hotel_head_image_left) {
            ShopJumpUtils.toJump(getContext(), hotelTop.get(0).getShop_type(), hotelTop.get(0).getShopid(), hotelTop.get(0).getShop_name());
        } else if (id == R.id.entertainment_ktv_image_left) {
            ShopJumpUtils.toJump(getContext(), ktvTop.get(0).getShop_type(), ktvTop.get(0).getShopid(), ktvTop.get(0).getShop_name());
        } else if (id == R.id.entertainment_ktv_image_right) {
            ShopJumpUtils.toJump(getContext(), ktvTop.get(1).getShop_type(), ktvTop.get(1).getShopid(), ktvTop.get(1).getShop_name());
        } else if (id == R.id.entertainment_health_image) {
            ShopJumpUtils.toJump(getContext(), healthTop.get(0).getShop_type(), healthTop.get(0).getShopid(), healthTop.get(0).getShop_name());
        }

    }


    @Override
    public void doBusiness(Context mContext) {
        setImmersion();
        getData();
    }

    private void getData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("lng", LocationUtils.getLng(getContext()));
        params.put("lat", LocationUtils.getLat(getContext()));
        params.put("cityid", "1");
        ApiManager apiManager = new ApiManager((BaseActivity) this.getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                entertainmentData = gson.fromJson(result, EntertainmentDataEntity.class);
                setTopBanner();
                setIcon();
                startRecommend();
                setPopularity();
                setFood();
                setHotel();
                setKtv();
                setHealth();
                setScenic();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.ENTERTAINMENT, params);
    }

    /**
     * 设置轮播图
     */
    private void setTopBanner() {
        List<TopBannerEntity> topBanner = entertainmentData.getBanner();
        List<String> imgs = new ArrayList<String>();
        for (int i = 0; i < topBanner.size(); i++) {
            imgs.add(topBanner.get(i).getUrlsmall());
        }
        ViewPagerUtils viewPagerUtils = new ViewPagerUtils(topBannerLl, topBannerVp, getActivity(), imgs, new ViewPagerUtils.ViewPagerClickListener() {
            @Override
            public void onClick(int postion) {
                //点击方法
                showToast(postion + "");
            }
        });
        viewPagerUtils.viewpagergo();
    }

    /**
     * 设置娱乐icon
     */
    private void setIcon() {
        final List<BaseIconEntity> iconEntity = entertainmentData.getIcon();
        iconAdapter = new IconAdapter(R.layout.item_icon, iconEntity, getContext());
        entertainmentIcon.setAdapter(iconAdapter);
        iconAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //判断是否需要登录 1：需要 0：不需要
                if ("1".equals(iconEntity.get(position).getIs_must_login())) {
                    if (!UserUtils.isLogin(getActivity())) {
                        return;
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString("shopType", iconEntity.get(position).getShop_type());
                bundle.putString("shopTypeLabels", iconEntity.get(position).getShop_type_labels());
                bundle.putString("iconLabel", iconEntity.get(position).getIcon_label());
                bundle.putString("title", iconEntity.get(position).getTitle());
                IconJumpUtils.jump(iconEntity.get(position), getActivity(), null, bundle);
            }
        });
    }

    /**
     * 今日推荐
     */
    private void setRecommend(final RecommendEntity recommend) {
        shopId = recommend.getShopid();
        shop_name = recommend.getShop_name();
        String shop_point = recommend.getShop_point();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(shop_name).append("\n").append(shop_point);
        SpannableString string = new SpannableString(stringBuffer);
        string.setSpan(new AbsoluteSizeSpan(15, true), 0, shop_name.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, shop_name.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(new AbsoluteSizeSpan(13, true), shop_name.length(), string.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        switcher.setText(string);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopJumpUtils.toJump(getContext(), recommend.getShop_type(), shopId, shop_name);

            }
        });
    }

    /**
     * 开始更新推荐内容
     */
    private void startRecommend() {
        recommend = entertainmentData.getRecommend();
        if (recommend != null && recommend.size() > 0) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new MyTask(), 1, 5000);//每5秒更新
        }
    }

    private int next() {
        int flag = id + 1;
        if (flag > recommend.size() - 1) {
            flag = flag - recommend.size();
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
                    setRecommend(recommend.get(id));  //更新TextSwitcherd显示内容;
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 人气排行
     */
    private void setPopularity() {
        popularityEntity = entertainmentData.getPopularity();


        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + popularityEntity.get(0).getShop_logo(), popularity_one_img);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + popularityEntity.get(1).getShop_logo(), popularity_two_img);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + popularityEntity.get(2).getShop_logo(), popularity_three_img);
        ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + popularityEntity.get(3).getShop_logo(), popularity_four_img);
        popularity_one_tv.setText(popularityEntity.get(0).getShop_name());
        popularity_two_tv.setText(popularityEntity.get(1).getShop_name());
        popularity_three_tv.setText(popularityEntity.get(2).getShop_name());
        popularity_four_tv.setText(popularityEntity.get(3).getShop_name());


    }

    /**
     * 美食
     */
    private void setFood() {
        NearFoodEntity nearFood = entertainmentData.getNear_food();
        final List<NearFoodEntity.OtherBean> foodCommodity = nearFood.getOther();
        if (foodCommodity.size() > 0) {
            foodAdapter = new FoodAdapter(R.layout.item_food, foodCommodity, getContext());
            foodRecycler.setAdapter(foodAdapter);
            foodAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopJumpUtils.toJump(getContext(), foodCommodity.get(position).getShop_type(), foodCommodity.get(position).getShopid(), foodCommodity.get(position).getShop_name());

                }
            });
        } else {
            foodRecycler.setVisibility(View.GONE);
        }


    }

    /**
     * 酒店
     */
    private void setHotel() {
        NearHotelEntity nearHotel = entertainmentData.getNear_hotel();
        hotelTop = nearHotel.getTop();
        final List<NearHotelEntity.BodyBean> hotelBody = nearHotel.getOther();
        if(hotelTop.size() == 0 && hotelBody.size() ==0){
            hotel_linear.setVisibility(View.GONE);
        }else {
            if (hotelTop.size() > 0) {
                ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + hotelTop.get(0).getShop_logo(), hotel_left_img);
                ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + hotelTop.get(1).getShop_logo(), hotel_right_img);
                hotel_left_tv.setText(hotelTop.get(0).getShop_name());
                hotel_right_tv.setText(hotelTop.get(1).getShop_name());
            } else {
                hotel_toplinear.setVisibility(View.GONE);
            }
            if (hotelBody.size() > 0) {
                hotelAdapter = new HotelAdapter(R.layout.item_food, hotelBody, getContext());
                hotelRecycler.setAdapter(hotelAdapter);

                hotelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ShopJumpUtils.toJump(getContext(), hotelBody.get(position).getShop_type(), hotelBody.get(position).getShopid(), hotelBody.get(position).getShop_name());

                    }
                });
            } else {
                hotelRecycler.setVisibility(View.GONE);
            }
        }
    }

    /**
     * KTV
     */
    private void setKtv() {
        NearKtvEntity nearKtv = entertainmentData.getNear_ktv();
        ktvTop = nearKtv.getTop();
        final List<NearKtvEntity.BodyBean> ktvBody = nearKtv.getOther();
        if(ktvTop.size()==0 && ktvBody.size()==0){
            ktv_linear.setVisibility(View.GONE);
        }else {
            if (ktvTop.size() > 0) {
                ImageUtils.showCircleImage(getContext(), HttpPath.IMAGEURL + ktvTop.get(0).getShop_logo(), ktv_left_img);
                ImageUtils.showCircleImage(getContext(), HttpPath.IMAGEURL + ktvTop.get(1).getShop_logo(), ktv_right_img);
                ktv_left_tv.setText(ktvTop.get(0).getShop_name());
                ktv_right_tv.setText(ktvTop.get(1).getShop_name());
            } else {
                ktv_toplinear.setVisibility(View.GONE);
            }
            if (ktvBody.size() > 0) {
                ktvAdapter = new KtvAdapter(getContext());
                ktvList.setAdapter(ktvAdapter);
                ktvAdapter.setList(ktvBody);
                ktvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ShopJumpUtils.toJump(getContext(), ktvBody.get(position).getShop_type(), ktvBody.get(position).getShopid(), ktvBody.get(position).getShop_name());
                    }
                });
            } else {
                ktvList.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 养生
     */
    private void setHealth() {
        NearHealthEntity nearHealth = entertainmentData.getNear_health();
        healthTop = nearHealth.getTop();
        final List<NearHealthEntity.BodyBean> healthBody = nearHealth.getOther();
        if(healthTop.size() == 0 && healthBody.size() == 0){
            health_linear.setVisibility(View.GONE);
        }else{
            if (healthTop.size() > 0) {
                ImageUtils.showImage(getContext(), HttpPath.IMAGEURL + healthTop.get(0).getShop_logo(), health_img);
                healthTop_name.setText(healthTop.get(0).getShop_name());
                healthTop_address.setText(healthTop.get(0).getShop_address());
                healthTop_mile.setText(healthTop.get(0).getDistance() + "km");
                healthTop_rate.setRating(Float.parseFloat(healthTop.get(0).getShop_starlevel()));
                healthTop_score.setText(healthTop.get(0).getShop_starlevel() + "分");
                healthTop_price.setText("人均" + healthTop.get(0).getShop_people_avgmoney() + "元");
            } else {
                health_toplinear.setVisibility(View.GONE);
            }
            if (healthBody.size() > 0) {
                healthAdapter = new HealthAdapter(getContext());
                healthList.setAdapter(healthAdapter);
                healthAdapter.setList(healthBody);
                healthList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ShopJumpUtils.toJump(getContext(), healthBody.get(position).getShop_type(), healthBody.get(position).getShopid(), healthBody.get(position).getShop_name());
                    }
                });
            } else {
                healthList.setVisibility(View.GONE);
            }
        }


    }

    /**
     * 景区
     */
    private void setScenic() {
        NearScenicEntity nearScenic = entertainmentData.getNear_scenicspot();
        List<NearScenicEntity.BodyBean> scenicBody = nearScenic.getBody();
        scenicTop = nearScenic.getTop();
        initScenicLine();
        if (scenicBody.size() > 0) {
            scenicBodyAdapter = new ScenicBodyAdapter(R.layout.item_scenic, scenicBody, getContext());
            scenicRecycler.setAdapter(scenicBodyAdapter);
            scenicBodyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getContext(), ScenicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("scenic", (Serializable) adapter.getData().get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
        } else {
            scenicRecycler.setVisibility(View.GONE);
        }


    }

    /**
     * 设置沉浸式
     */
    private void setImmersion() {
//        TitleBarUtils.setScrollListener(scrollView,titleBar);
        ImmersionUtils.setImmersion(getActivity().getWindow(), getActivity(), scrollView, titleBar, statusBar);
//        ImmersionUtils.immersive(getActivity().getWindow(),R.color.colorWhite,0.5f);
    }

    /**
     * 景区横向滑动布局
     */
    private void initScenicLine() {
        if (scenicTop.size() > 0) {
            ViewConfiguration configuration = ViewConfiguration.get(getContext());
            final int mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
            lines = new View[scenicTop.size()];
            for (int i = 0; i < scenicTop.size(); i++) {
                View line = new View(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(54, 4);
                params.setMargins(20, 5, 5, 5);
                line.setLayoutParams(params);
                if (i == 0) {
                    line.setBackgroundResource(R.mipmap.entertainment_line_select);
                } else {
                    line.setBackgroundResource(R.mipmap.entertainment_line_normal);
                }
                lines[i] = line;
                scenic_line.addView(line);
            }
            scenicPagerAdapter = new ScenicPagerAdapter(getContext());
            scenicViewpager.setAdapter(scenicPagerAdapter);
            scenicPagerAdapter.setList(scenicTop);
            scenicPagerAdapter.notifyDataSetChanged();
            scenicViewpager.setCurrentItem(currentImgIndex);
            scenicViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentImgIndex = position;
                    if (scenicTop.size() != 1) {
                        for (int i = 0; i < scenicTop.size(); i++) {
                            if (i == currentImgIndex % scenicTop.size()) {
                                lines[i].setBackgroundResource(com.sinata.rwxchina.basiclib.R.mipmap.entertainment_line_select);
                            } else {
                                lines[i].setBackgroundResource(com.sinata.rwxchina.basiclib.R.mipmap.entertainment_line_normal);
                            }
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            scenicViewpager.setOnTouchListener(new View.OnTouchListener() {
                int touchFlag = 0;
                float x = 0, y = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touchFlag = 0;
                            x = event.getX();
                            y = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float xDiff = Math.abs(event.getX() - x);
                            float yDiff = Math.abs(event.getY() - y);
                            if (xDiff < mTouchSlop && xDiff >= yDiff) {
                                touchFlag = 0;
                            } else {
                                touchFlag = -1;
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if (touchFlag == 0) {
                                int currentItem = scenicViewpager.getCurrentItem();
                                Intent intent = new Intent(getContext(), ScenicActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("scenic", (Serializable) scenicTop.get(currentItem));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                            break;
                    }


                    return false;
                }
            });
        } else {
            scenicViewpager.setVisibility(View.GONE);
        }

    }


    /**
     * 解决滑动冲突
     */
    private void SolveConflict() {
        smartRefresh.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ktvList.getParent().requestDisallowInterceptTouchEvent(false);
                healthList.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        ktvList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ktvList.getParent().requestDisallowInterceptTouchEvent(true);

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
                            ktvList.getParent().requestDisallowInterceptTouchEvent(false);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            ktvList.getParent().requestDisallowInterceptTouchEvent(true);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    default:
                        break;
                }

                return false;
            }
        });
        healthList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                healthList.getParent().requestDisallowInterceptTouchEvent(true);

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
                            healthList.getParent().requestDisallowInterceptTouchEvent(false);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            healthList.getParent().requestDisallowInterceptTouchEvent(true);
                            smartRefresh.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    default:
                        break;
                }

                return false;
            }
        });

    }
}
