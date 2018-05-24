package com.sinata.rwxchina.component_home.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：
 * @modifyRecord:
 */


public class IconMoreEntity {

    /**
     * name : 周边查询
     * list : [{"id":"9","title":"加油站","img":"/Uploads/App/icon/jyz.png","is_url":"0","url":"","describe":"周边查询","shop_type":"","shop_type_labels":"","icon_label":"map_jyz"},{"id":"10","title":"医院","img":"/Uploads/App/icon/yy.png","is_url":"0","url":"","describe":"周边查询","shop_type":"","shop_type_labels":"","icon_label":"map_yy"},{"id":"11","title":"检测站","img":"/Uploads/App/icon/jcz.png","is_url":"0","url":"","describe":"周边查询","shop_type":"","shop_type_labels":"","icon_label":"map_jcz"},{"id":"12","title":"交警队","img":"/Uploads/App/icon/jjd.png","is_url":"0","url":"","describe":"周边查询","shop_type":"","shop_type_labels":"","icon_label":"map_jjd"}]
     */

    private String name;
    private List<ListBean> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    public static class ListBean {
        /**
         * id : 9
         * title : 加油站
         * img : /Uploads/App/icon/jyz.png
         * is_url : 0
         * url :
         * describe : 周边查询
         * shop_type :
         * shop_type_labels :
         * icon_label : map_jyz
         */

        private String id;
        private String title;
        private String img;
        private String is_url;
        private String url;
        private String describe;
        private String shop_type;
        private String shop_type_labels;
        private String icon_label;
        public static final int PERIMETER = 1;
        public static final int SERVICE = 2;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_url() {
            return is_url;
        }

        public void setIs_url(String is_url) {
            this.is_url = is_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getShop_type_labels() {
            return shop_type_labels;
        }

        public void setShop_type_labels(String shop_type_labels) {
            this.shop_type_labels = shop_type_labels;
        }

        public String getIcon_label() {
            return icon_label;
        }

        public void setIcon_label(String icon_label) {
            this.icon_label = icon_label;
        }

    }
}
