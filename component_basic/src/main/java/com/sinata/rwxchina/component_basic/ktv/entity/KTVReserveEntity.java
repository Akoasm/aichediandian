package com.sinata.rwxchina.component_basic.ktv.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe KTV商品预定实体类
 * @modifyRecord
 */

public class KTVReserveEntity {

    /**
     * title : 小包(1-5人)
     * count : 1
     * list : [{"goods_id":"258","shopid":"15940954519","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"0","goods_type_zh":"","goods_name":"中包（2-5）","goods_subtitle":"欢唱6小时+酒水套餐","default_image":"/Uploads/User/15940954509/6128c281630ea02ff644329e30db5734.jpg","addition_image":[],"goods_score":"0","goods_type_labels":["小包(1-5人)"],"goods_unit":"间","goods_market_price":"258","goods_price":"238","goods_number":"999","goods_description":"","goods_description_img":[],"goods_status":"1","goods_attribute":"","goods_is_wifi":"0","goods_is_window":"0","goods_is_breakfast":"0","goods_floor":"","goods_live_people":"","goods_bed_type":"","goods_acreage":"","goods_rule":"","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"0","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1513055720","createdate":"2017-12-12 13:15:20","updatetime":"","is_delete":"0","is_mallgoods":"0","goods_sale":"0","goods_period":"","goods_services":"","goods_prompt":"","goods_scope_people":"","goods_type_labels_str":"小包(1-5人)"}]
     */

    private String title;
    private int count;
    private List<BaseGoodsInfo> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BaseGoodsInfo> getList() {
        return list;
    }

    public void setList(List<BaseGoodsInfo> list) {
        this.list = list;
    }

}
