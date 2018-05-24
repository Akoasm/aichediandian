package com.sinata.rwxchina.basiclib.basic.basicGroup;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 套餐实体类
 * @modifyRecord
 */

public class CashCouponGroupEntity {

    /**
     * m_meun_name : 按摩
     * meun_list : [{"m_goods_name":"较低按摩","m_goods_number":"1次","m_goods_money":"50"}]
     */

    private String m_meun_name;
    private List<MeunListBean> meun_list;

    public String getM_meun_name() {
        return m_meun_name;
    }

    public void setM_meun_name(String m_meun_name) {
        this.m_meun_name = m_meun_name;
    }

    public List<MeunListBean> getMeun_list() {
        return meun_list;
    }

    public void setMeun_list(List<MeunListBean> meun_list) {
        this.meun_list = meun_list;
    }

    public static class MeunListBean {
        /**
         * m_goods_name : 较低按摩
         * m_goods_number : 1次
         * m_goods_money : 50
         */

        private String m_goods_name;
        private String m_goods_number;
        private String m_goods_money;

        public String getM_goods_name() {
            return m_goods_name;
        }

        public void setM_goods_name(String m_goods_name) {
            this.m_goods_name = m_goods_name;
        }

        public String getM_goods_number() {
            return m_goods_number;
        }

        public void setM_goods_number(String m_goods_number) {
            this.m_goods_number = m_goods_number;
        }

        public String getM_goods_money() {
            return m_goods_money;
        }

        public void setM_goods_money(String m_goods_money) {
            this.m_goods_money = m_goods_money;
        }
    }
}
