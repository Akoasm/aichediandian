package com.sinata.rwxchina.component_aboutme.bean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class IntegralBean {

    /**
     * integral : 100
     * integral_list : [{"logid":"1","remarks_user":"积分测试","createtime":"2017-10-17","off_integral":"+100"},{"logid":"2","remarks_user":"测试2","createtime":"2017-10-17","off_integral":"+80"}]
     */

    private String integral;
    private List<IntegralListBean> integral_list;

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public List<IntegralListBean> getIntegral_list() {
        return integral_list;
    }

    public void setIntegral_list(List<IntegralListBean> integral_list) {
        this.integral_list = integral_list;
    }

    public static class IntegralListBean {
        /**
         * logid : 1
         * remarks_user : 积分测试
         * createtime : 2017-10-17
         * off_integral : +100
         */

        private String logid;
        private String remarks_user;
        private String createtime;
        private String off_integral;
        private String new_integral;

        public String getNew_integral() {
            return new_integral;
        }

        public void setNew_integral(String new_integral) {
            this.new_integral = new_integral;
        }

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public String getRemarks_user() {
            return remarks_user;
        }

        public void setRemarks_user(String remarks_user) {
            this.remarks_user = remarks_user;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getOff_integral() {
            return off_integral;
        }

        public void setOff_integral(String off_integral) {
            this.off_integral = off_integral;
        }
    }
}
