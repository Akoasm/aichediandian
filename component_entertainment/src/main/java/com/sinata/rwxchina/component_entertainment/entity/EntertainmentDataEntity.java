package com.sinata.rwxchina.component_entertainment.entity;

import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/14
 * @describe：娱乐总数据实体类
 * @modifyRecord:
 */


public class EntertainmentDataEntity {

    private List<TopBannerEntity> banner;
    private List<BaseIconEntity> icon;
    private List<RecommendEntity> recommend;
    private List<PopularityEntity>popularity;
    private NearFoodEntity near_food;
    private NearHotelEntity near_hotel;
    private NearKtvEntity near_ktv;
    private NearHealthEntity near_health;
    private NearScenicEntity near_scenicspot;

    public List<TopBannerEntity> getBanner() {
        return banner;
    }

    public void setBanner(List<TopBannerEntity> banner) {
        this.banner = banner;
    }

    public List<BaseIconEntity> getIcon() {
        return icon;
    }

    public void setIcon(List<BaseIconEntity> icon) {
        this.icon = icon;
    }

    public List<RecommendEntity> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendEntity> recommend) {
        this.recommend = recommend;
    }

    public List<PopularityEntity> getPopularity() {
        return popularity;
    }

    public void setPopularity(List<PopularityEntity> popularity) {
        this.popularity = popularity;
    }

    public NearFoodEntity getNear_food() {
        return near_food;
    }

    public void setNear_food(NearFoodEntity near_food) {
        this.near_food = near_food;
    }

    public NearHotelEntity getNear_hotel() {
        return near_hotel;
    }

    public void setNear_hotel(NearHotelEntity near_hotel) {
        this.near_hotel = near_hotel;
    }

    public NearKtvEntity getNear_ktv() {
        return near_ktv;
    }

    public void setNear_ktv(NearKtvEntity near_ktv) {
        this.near_ktv = near_ktv;
    }

    public NearHealthEntity getNear_health() {
        return near_health;
    }

    public void setNear_health(NearHealthEntity near_health) {
        this.near_health = near_health;
    }

    public NearScenicEntity getNear_scenicspot() {
        return near_scenicspot;
    }

    public void setNear_scenicspot(NearScenicEntity near_scenicspot) {
        this.near_scenicspot = near_scenicspot;
    }

    @Override
    public String toString() {
        return "EntertainmentDataEntity{" +
                "banner=" + banner +
                ", icon=" + icon +
                ", recommend=" + recommend +
                ", popularity=" + popularity +
                ", near_food=" + near_food +
                ", near_hotel=" + near_hotel +
                ", near_ktv=" + near_ktv +
                ", near_health=" + near_health +
                ", near_scenicspot=" + near_scenicspot +
                '}';
    }
}
