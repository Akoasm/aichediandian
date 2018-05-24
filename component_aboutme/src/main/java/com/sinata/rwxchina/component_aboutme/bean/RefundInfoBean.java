package com.sinata.rwxchina.component_aboutme.bean;

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class RefundInfoBean {

    /**
     * actiondate : 2017-12-12 13:17:55
     * total_money : 66
     * goods_name : 测试商品
     * createdate : 2017-12-12 13:17:55
     * reason : 快点退钱，不解释
     * remarks : 不想退
     */

    private String actiondate;
    private String total_money;
    private String shop_name;
    private String createdate;
    private String reason;
    private String remarks;

    public String getActiondate() {
        return actiondate;
    }

    public void setActiondate(String actiondate) {
        this.actiondate = actiondate;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
