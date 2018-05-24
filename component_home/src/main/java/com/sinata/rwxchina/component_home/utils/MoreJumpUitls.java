package com.sinata.rwxchina.component_home.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityDetailActivity;
import com.sinata.rwxchina.basiclib.webViewUtils.activity.DefaultWebViewActivity;
import com.sinata.rwxchina.component_basic.basic.basiclist.BasicListActivity;
import com.sinata.rwxchina.component_basic.car.activity.CarListActivity;
import com.sinata.rwxchina.component_basic.scenic.activity.ScenicListActivity;
import com.sinata.rwxchina.component_home.activity.InsuranceListActivity;
import com.sinata.rwxchina.component_home.entity.IconMoreEntity;

import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.AICHE;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.BAODAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.BAOXIAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.DAIJIA;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.FENXIANG;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.HELP;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.JIANCEZHAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.JIANGQU;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.JIAOJINGDUI;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.JIAYOUZHAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.JIUDIAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.KEFU;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.KTV;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.MEISHI;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.MORE;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.QICHE;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.QICHEPEIJIAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.SHENCHE;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.SHENCHEJILU;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.TEMAI;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.WEIZHANG;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.YANGSHENG;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.YIYUAN;
import static com.sinata.rwxchina.component_basic.jumputils.IconUtils.ZUCHE;

/**
 * @author:wj
 * @datetime：2018/1/3
 * @describe：更多跳转到各个列表
 * @modifyRecord:
 */


public class MoreJumpUitls {

    /**
     * 首页、娱乐首页、我的首页icon点击跳转
     * @param more
     * @param activity
     * @param cls 目标页面
     * @param bundle
     */
    public static void jump(IconMoreEntity.ListBean more, Context activity, Class<?> cls, Bundle bundle){
        if (cls!=null){
            startActivity(activity,cls,bundle);
            return;
        }
        String label=more.getIcon_label();
        //是否是H5页面，1：H5页面；0：原生页面
        String isUrl=more.getIs_url();
        if ("1".equals(isUrl)){
            startActivity(activity,DefaultWebViewActivity.class,bundle);
            return;
        }
        switch (label){
            case QICHE:
                startActivity(activity, CarListActivity.class,bundle);
//                try {
//                    startActivity(activity, Class.forName("CarListActivity.class"),bundle);
//                } catch (ClassNotFoundException e) {
//                    LogUtils.e("JumpUitls","反射异常="+e.toString());
//                    e.printStackTrace();
//                }
                break;
            case JIUDIAN:
            case KTV:
            case MEISHI:
            case YANGSHENG:
                startActivity(activity, BasicListActivity.class,bundle);
                break;
            case JIANGQU:
                startActivity(activity, ScenicListActivity.class,bundle);
                break;
            case BAOXIAN:
                startActivity(activity, InsuranceListActivity.class,bundle);
                break;
            case DAIJIA:
                break;
            case SHENCHE:
                break;

            case QICHEPEIJIAN:
//                startActivity(activity, CommodityDetailActivity.class,bundle);
                break;

            case JIANCEZHAN:
                break;
            case JIAOJINGDUI:
                break;
            case JIAYOUZHAN:
                break;
            case YIYUAN:
                break;
            case HELP:
                break;
            case KEFU:
                break;
            case AICHE:
                break;
            case BAODAN:
                break;
            case FENXIANG:
                break;
            case SHENCHEJILU:
                break;
            default:
                break;
        }
    }

    /**
     * 页面跳转，可携带bundle跳转
     * @param activity
     * @param cls
     * @param bundle 携带的bundle
     */
    private static void startActivity(Context activity,Class<?> cls,Bundle bundle){
        Intent intent=new Intent(activity,cls);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }
}
