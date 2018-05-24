package com.sinata.rwxchina.component_home.Bean;

import java.io.Serializable;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BrandBean implements Serializable{

    /**
     * brand_id : 219
     * brand_type : 0
     * brand_name : 米其林
     * brand_logo : /Uploads/App/brand/goods/6b49ab5c9c463ec58330779f03adcb69.jpg
     */

    private String brand_id;
    private String brand_type;
    private String brand_name;
    private String brand_logo;

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_type() {
        return brand_type;
    }

    public void setBrand_type(String brand_type) {
        this.brand_type = brand_type;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }
}
