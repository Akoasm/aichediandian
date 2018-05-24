package com.sinata.rwxchina.component_basic.basic.basicbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.component_basic.R;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 轮播图工具类
 * @modifyRecord
 */

public class BannerUtils {
    /**
     * 设置店铺轮播图
     * @param view
     * @param context
     * @param shopInfo
     */
    public static void setBanner(View view, Context context, BaseShopInfo shopInfo){
        ViewPager viewPager=view.findViewById(R.id.carousel_viewpager);
        LinearLayout linearLayout=view.findViewById(R.id.carousel_dots);
        //设置轮播图轮播图
        ViewPagerUtils viewPagerUtils=new ViewPagerUtils(linearLayout,viewPager,context,shopInfo.getShop_show());
        viewPagerUtils.viewpagergo();
    }

    /**
     * 设置店铺轮播图
     * @param viewPager
     * @param linearLayout
     * @param context
     * @param shopInfo
     */
    public static void setBanner(ViewPager viewPager,LinearLayout linearLayout,Context context,BaseShopInfo shopInfo){
        //设置轮播图轮播图
        ViewPagerUtils viewPagerUtils=new ViewPagerUtils(linearLayout,viewPager,context,shopInfo.getShop_show());
        viewPagerUtils.viewpagergo();
    }

    public static void setBanner(View view, Context context, BaseGoodsInfo groupInfo){
        ViewPager viewPager=view.findViewById(R.id.carousel_viewpager);
        LinearLayout linearLayout=view.findViewById(R.id.carousel_dots);
        //设置轮播图轮播图
        ViewPagerUtils viewPagerUtils=new ViewPagerUtils(linearLayout,viewPager,context,groupInfo.getAddition_image());
        viewPagerUtils.viewpagergo();
    }
}
