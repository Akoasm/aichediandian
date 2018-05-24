package com.sinata.rwxchina.component_home.entity;

import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/6
 * @describe 首页总数据实体类
 * @modifyRecord
 */

public class IndexDataEntity {

    private List<HotEntity> banner;
    private List<BaseIconEntity> icon;
    private List<NewsEntity> news;
    private List<HotEntity> huobao;
    private List<QualityEntity> pinzhi;
    private List<QualityBannerEntity> pinzhi_banner;
    private List<BrandEntity> pinpai;
    private List<BrandBannerEntity> pinpai_banner;
    private List<LovelyListEntity> xihuan;
    private List<CommodityBean> xihuan_temai;

    public List<HotEntity> getBanner() {
        return banner;
    }

    public void setBanner(List<HotEntity> banner) {
        this.banner = banner;
    }

    public List<BaseIconEntity> getIcon() {
        return icon;
    }

    public void setIcon(List<BaseIconEntity> icon) {
        this.icon = icon;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }

    public List<HotEntity> getHuobao() {
        return huobao;
    }

    public void setHuobao(List<HotEntity> huobao) {
        this.huobao = huobao;
    }

    public List<QualityEntity> getPinzhi() {
        return pinzhi;
    }

    public void setPinzhi(List<QualityEntity> pinzhi) {
        this.pinzhi = pinzhi;
    }

    public List<QualityBannerEntity> getPinzhi_banner() {
        return pinzhi_banner;
    }

    public void setPinzhi_banner(List<QualityBannerEntity> pinzhi_banner) {
        this.pinzhi_banner = pinzhi_banner;
    }

    public List<BrandEntity> getPinpai() {
        return pinpai;
    }

    public void setPinpai(List<BrandEntity> pinpai) {
        this.pinpai = pinpai;
    }

    public List<BrandBannerEntity> getPinpai_banner() {
        return pinpai_banner;
    }

    public void setPinpai_banner(List<BrandBannerEntity> pinpai_banner) {
        this.pinpai_banner = pinpai_banner;
    }

    public List<LovelyListEntity> getXihuan() {
        return xihuan;
    }

    public void setXihuan(List<LovelyListEntity> xihuan) {
        this.xihuan = xihuan;
    }

    public List<CommodityBean> getXihuan_temai() {
        return xihuan_temai;
    }

    public void setXihuan_temai(List<CommodityBean> xihuan_temai) {
        this.xihuan_temai = xihuan_temai;
    }

    @Override
    public String toString() {
        return "IndexDataEntity{" +
                "banner=" + banner +
                ", icon=" + icon +
                ", news=" + news +
                ", huobao=" + huobao +
                ", pinzhi=" + pinzhi +
                ", pinzhi_banner=" + pinzhi_banner +
                ", pinpai=" + pinpai +
                ", pinpai_banner=" + pinpai_banner +
                ", xihuan=" + xihuan +
                ", xihuan_temai=" + xihuan_temai +
                '}';
    }
}
