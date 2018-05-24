package com.sinata.rwxchina.component_aboutme.bean;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceDetailBean {


    /**
     * typestr : 提现
     * off_money : -99.00
     * date : 2017-11-27 15:38:40
     * new_money : 9763.00
     */

    private String typestr;
    private String off_money;
    private String date;
    private String money_new;

    public String getTypestr() {
        return typestr;
    }

    public void setTypestr(String typestr) {
        this.typestr = typestr;
    }

    public String getOff_money() {
        return off_money;
    }

    public void setOff_money(String off_money) {
        this.off_money = off_money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney_new() {
        return money_new;
    }

    public void setMoney_new(String money_new) {
        this.money_new = money_new;
    }
}
