package com.sinata.rwxchina.component_aboutme.bean;

/**
 * @author:zy
 * @detetime:2017/11/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceBean {


    /**
     * today_money : 0.00
     * balance : 8.16
     * month : 12.24
     */

    private String today_money;
    private String balance;
    private String month;

    public String getToday_money() {
        return today_money;
    }

    public void setToday_money(String today_money) {
        this.today_money = today_money;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
