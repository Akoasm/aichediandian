package com.sinata.rwxchina.component_home.entity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：
 * @modifyRecord:
 */


public class InsuanceListEntity {

    private List<ListBean> list;
    private List<BannerListBean> banner_list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<BannerListBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerListBean> banner_list) {
        this.banner_list = banner_list;
    }

    public static class ListBean {
        /**
         * id : 1
         * logo : ./Uploads/User/4/4a10f598869ffb926bbd0667f74fd97c.jpg
         * name : 景泰车险
         * intro : 景泰车险
         */

        private String id;
        private String logo;
        private String name;
        private String intro;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }

    public static class BannerListBean {
        /**
         * urlsmall : /Uploads/_old/advs/9de4bbf4bc7177692a92a69b04b8d4f8.jpg
         */

        private String urlsmall;

        public String getUrlsmall() {
            return urlsmall;
        }

        public void setUrlsmall(String urlsmall) {
            this.urlsmall = urlsmall;
        }
    }
}
