package com.sinata.rwxchina.basiclib.commonclass.Bean;

/**
 * @author:wj
 * @datetime：2018/1/8
 * @describe：
 * @modifyRecord:
 */


public class AddressListBean {

    /**
     * id : 20
     * name : 小红
     * phone : 13333333333
     * region : 成都市
     * street :
     * address : 府青路
     * is_mo : 1
     * uid : 1902
     */

    private String id;
    private String name;
    private String phone;
    private String region;
    private String street;
    private String address;
    private String is_mo;
    private String uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_mo() {
        return is_mo;
    }

    public void setIs_mo(String is_mo) {
        this.is_mo = is_mo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "AddressListBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", is_mo='" + is_mo + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
