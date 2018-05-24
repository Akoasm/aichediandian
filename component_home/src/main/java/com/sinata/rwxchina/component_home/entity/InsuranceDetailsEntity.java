package com.sinata.rwxchina.component_home.entity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：
 * @modifyRecord:
 */


public class InsuranceDetailsEntity {


    /**
     * id : 8
     * logo : /Uploads/insurance/f996c85a044ee28d0aebb903392c81dd.png
     * name : 人保车险
     * fullname : 中国人民财产保险股份有限公司
     * cpyid : PICC
     * addr : 成都市锦江区梓潼桥西街23号
     * tel : 95518
     * intro :  铸金牌服务 ,为梦想护航
     * information : 1、可用微信支付；
     2、保险服务:出单核保问题请联系出单人员028-86638961，028-86661853;所有任我行的保单，资料提供齐全的情况下，我公司提供保险单批改，保险抄件等服务；
     3、车辆维修服务:任我行维修中心(028-84200851)提供正常情况的保险报案、维修、代索赔服务;
     4、保单配送服务:任我行配送中心028-62618592;
     5、车辆年审服务:任我行年审中心028-84397726有偿提供车辆上线、免检年审服务;
     * map_lng : 104.079888760677
     * map_lat : 30.660279614136
     * service_list : [{"id":"45","cid":"8","item":"搭电服务：","content":"车辆因电力不足无法启动时，帮助接电启动。"},{"id":"46","cid":"8","item":"紧急加水：","content":"车辆道路行驶中水箱缺水时，负责免费加水。"},{"id":"47","cid":"8","item":"紧急送油：","content":"限送5升，超过部分由客户承担(相关情况请拨打客服热线咨询)。"},{"id":"48","cid":"8","item":"更换轮胎：","content":"车辆道路行驶过程中因发生爆胎、轮胎损坏而无法行驶时，帮助更换车胎，但完好的备用车胎由客户提供，或客户承担修补轮胎费用。"},{"id":"49","cid":"8","item":"轮胎加气：","content":"车辆道路行驶过程中因发生轮胎漏气造成无法行驶时，帮助客户加气。"},{"id":"50","cid":"8","item":"拖车服务：","content":"车辆因故障无法行驶时，负责将车辆拖至客户指定地点。免费拖车距离为50公里（交通管理部门或道路管理机构不允许社会救援车辆驶入的部分高速公路、隧道、大桥、高架道路等路段除外）。"}]
     */

    private String id;
    private String logo;
    private String name;
    private String fullname;
    private String cpyid;
    private String addr;
    private String tel;
    private String intro;
    private String information;
    private String map_lng;
    private String map_lat;
    private List<ServiceListBean> service_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCpyid() {
        return cpyid;
    }

    public void setCpyid(String cpyid) {
        this.cpyid = cpyid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMap_lng() {
        return map_lng;
    }

    public void setMap_lng(String map_lng) {
        this.map_lng = map_lng;
    }

    public String getMap_lat() {
        return map_lat;
    }

    public void setMap_lat(String map_lat) {
        this.map_lat = map_lat;
    }

    public List<ServiceListBean> getService_list() {
        return service_list;
    }

    public void setService_list(List<ServiceListBean> service_list) {
        this.service_list = service_list;
    }

    public static class ServiceListBean {
        /**
         * id : 45
         * cid : 8
         * item : 搭电服务：
         * content : 车辆因电力不足无法启动时，帮助接电启动。
         */

        private String id;
        private String cid;
        private String item;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
