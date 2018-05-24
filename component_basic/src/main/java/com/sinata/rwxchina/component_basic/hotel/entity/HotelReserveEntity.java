package com.sinata.rwxchina.component_basic.hotel.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe 酒店预约实体类
 * @modifyRecord
 */

public class HotelReserveEntity {

    /**
     * title : 双床
     * count : 1
     * list : [{"goods_id":"251","shopid":"15940954515","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"0","goods_type_zh":"","goods_name":"主题房间","goods_subtitle":"入住前18点免费取消","default_image":"/Uploads/User/15940954508/de94e783ba2494580a05afd91893662d.jpg","addition_image":["/Uploads/User/15940954508/54148f189cb4f3251186f8c2e6290fbe.jpg","/Uploads/User/15940954508/71e8f2a5d58c24cf0b2629b2c7e55ae1.jpg","/Uploads/User/15940954508/cb267bfd849812d71d0894e610525a0a.jpg","/Uploads/User/15940954508/a79f026d301c914486ffd2693df0eff9.jpg","/Uploads/User/15940954508/17dc91ce30e00e5c007332dbdf156cff.jpg"],"goods_score":"0","goods_type_labels":["双床"],"goods_unit":"间","goods_market_price":"208","goods_price":"198","goods_number":"999","goods_description":"免费无线","goods_description_img":[],"goods_status":"1","goods_attribute":"","goods_is_wifi":"1","goods_is_window":"1","goods_is_breakfast":"1","goods_floor":"10","goods_live_people":"4","goods_bed_type":"2.4*2.0","goods_acreage":"60","goods_rule":"当天有效，过期无效","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"1","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1513051259","createdate":"2017-12-12 12:00:59","updatetime":"","is_delete":"0","is_mallgoods":"0","goods_sale":"0","goods_period":"2017-01-23--20178-12-23","goods_services":"提供免费停车位，无线等","goods_prompt":"有什么不明白的，电话咨询","goods_scope_people":"","goods_labels_attr":[],"goods_is_freight":"1","goods_freight":"0.00","goods_type_labels_str":"双床"}]
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
