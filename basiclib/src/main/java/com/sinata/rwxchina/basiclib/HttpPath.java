package com.sinata.rwxchina.basiclib;

/**
 * @author HRR
 * @datetime 2017/11/13
 * @describe 接口地址类
 * @modifyRecord
 */

public class HttpPath {
//    public static final String BASEURL="http://chengdu.cdrwx.cn/api.php/acdd/";
//    public static final String BASEHOST="http://chengducs.cdrwx.cn/";//测试服
    public static final String BASEHOST="http://chengdu.cdrwx.cn/";//正式服
    public static final String BASEURL=BASEHOST+"api.php/acdd/";
//    public static final String IMAGEURL = "http://192.168.0.166:8088";
    public static final String IMAGEURL = "http://chengdu.cdrwx.cn/";

    /*广告页*/
    public static final String START_IMG_PATH = "v1/Other/Other/getAppFirstImg";
    /*登录注册模块*/
    /**手机号登录*/
    public static final String LOGINPHONE="v1/Other/Login/phone";
    /**获取验证码*/
    public static final String GETCODE="v1/Other/Verify/getVerify";
    /**密码登录*/
    public static final String LOGINPWD="v1/Other/Login/pass";
    /**找回密码*/
    public static final String FINDPWD="v1/Other/login/los_pwd";
    /**用户协议地址*/
    public static final String WEB_AGREEMENT_USER="http://chengdu.cdrwx.cn/h5/xieyi/acdd_user.html";

    /*首页模块*/
    /**首页*/
    public static final String INDEX="v1/Index/Index/index";
    /**获取特卖区内容*/
    public static final String GETDETAILLIST="v1/Index/Index/getDetailList";
    /**火爆专区*/
    public static final String BRANDACTIVITIES = "v1/Index/Index/getHuobaoMore";
    /**保险列表*/
    public static final String INSURANCELIST = "v1/Index/Insurance/insurance";
    /**保险详情*/
    public static final String INSURANCE = "v1/Index/Insurance/insurance_detail";
    /**更多图标*/
    public static final String MOREICON = "v1/Index/index/getIconMore";
    /**首页热门的搜索*/
    public static final String HOTSEARCH = "v1/Index/index/getKeywordHot";
    /**品牌列表*/
    public static final String BRANDLIST = "v1/Index/Index/brand_list";
    /**城市*/
    public static final String CITYNAME = "v1/Index/Index/getOpenCityID";
    /**今日限行*/
    public static final String LIMITLINE = "v1/Other/jisu/jisuapi_query";
    /**今日限行可查的城市*/
    public static final String CHECKLIMIT = "v1/Other/jisu/jisuapi_city";
    /**商城店铺详情*/
    public static final String SHOPDETAIL = "v1/Shop/Api/getShopMall";
    /**买车险*/
    public static final String BUYINSURANCE = "v1/Index/Insurance/regist_and_login";
    /**买寿险*/
    public static final String BUYLIFEINSURANCE = "v1/Index/Insurance/get_lebaoba_url";
    /**完善保险代理资料H5*/
    public static final String WEB_COMPLETEAGENTINFO =BASEHOST+"app.php/Insurance/Index/update_ext";

    /*我的模块*/
    /**用户资料展示*/
    public static final String PERSONALINFO = "v1/My/personal/show";
    /** 修改个人资料*/
    public static final String UPDATEPERSONALINFO = "v1/My/personal/update";
    /**获取功能模块图标*/
    public static final String GETFUCNTIONICON = "v1/Other/Other/my_icon";
    /**我的钱包*/
    public static final String MYWALLET = "v1/My/Wallet/wallet";
    /**余额明细*/
    public static final String BALANCEDETAIL = "v1/My/Wallet/balance_list";
    /**添加银行卡*/
    public static final String ADDBANKCARD = "v1/My/Wallet/add_bank_card";
    /**银行卡列表*/
    public static final String BANKCARDLIST = "v1/My/Wallet/bank_list";
    /**提现申请*/
    public static final String WITHDRAW = "v1/My/Wallet/withdrawal";
    /**提现界面数据*/
    public static final String WITHDRAWDATA = "v1/My/Wallet/withdrawal_do";
    /**提现明细列表*/
    public static final String WITHDRAWDETAILLIST = "v1/My/Wallet/withdrawal_list";
    /**提现状态*/
    public static final String WITHDRAWSTATUS = "v1/My/Wallet/withdrawal_status";
    /**代金券*/
    public static final String COUPON = "v1/My/Coupon/coupon";
    /**积分*/
    public static final String INTEGRAL = "v1/My/Integral/integral";
    /**我的订单*/
    public static final String MYORDER = "v1/My/Indent/indent_list";
    /**退货/退款列表*/
    public static final String REFUNDLIST = "v1/My/Indent/refund_list";
    /**退货/退款详情*/
    public static final String REFUNDSTATUS = "v1/My/Indent/retreat_yet";
    /**申请退款*/
    public static final String APPLYREFUND = "v1/My/Indent/refund_put";
    /**订单详情*/
    public static final String ORDERDETAIL = "v1/My/Indent/pay_order";
    /**确认收货*/
    public static final String CONFIRMRECEIVE = "v1/My/Indent/confirm_pay";
    /**代金券使用规则H5地址*/
    public static final String WEB_COUPONUSERULE = "http://chengdu.cdrwx.cn/h5/xieyi/shiyong.html";
    /**取消订单*/
    public static final String CANCELORDER = "app.php/Indent/Api/cancelAcddAppIndent";
    /**删除订单*/
    public static final String DELETEORDER = "v1/My/Indent/del_order";
    /**查看订单二维码*/
    public static final String ORDERQRCODE = "v1/My/Indent/check_code";
    /**添加或修改地址*/
    public static final String NEWADDRESS = "v1/My/address/address";
    /**地址列表*/
    public static final String ADDRESSLIST = "v1/My/address/add_list";
    /**删除地址*/
    public static final String DELETEADDRESS = "v1/My/address/del";
    /**设为默认地址*/
    public static final String DEFAULTADDRESS = "v1/My/address/do_mo";
    /**关于我们*/
    public static final String WEB_ABOUTUS = "http://chengdu.cdrwx.cn/h5/xieyi/acdd_about.html";
    /**保险代理H5*/
    public static final String WEB_INSURANCE = BASEHOST+"app.php/Insurance/Index/h5";
    /** 商业联盟H5*/
    public static final String WEB_BUSINESSALLIANCE = BASEHOST+"app.php/Spread/Index/h5";

    /*娱乐模块*/
    /**洗车系列-商铺详情*/
    public static final String GETCAR = "v1/Shop/Api/getShopInfoCar";
    /**获取景区列表*/
    public static final String GETSCENIC="v1/Yule/Shop/spot_list";
    /**娱乐*/
    public static final String ENTERTAINMENT="v1/Yule/Index/index";
    /**养生店铺详情*/
    public static final String GETHEALTH="v1/Shop/Api/getShopInfoHealth";
    /**酒店店铺详情*/
    public static final String GETHOTEL="v1/Shop/Api/getShopInfoHotel";
    /**美食店铺详情*/
    public static final String GETFOOD="v1/Shop/Api/getShopInfoFood";
    /**KTV店铺详情*/
    public static final String GETKTV="v1/Shop/Api/getShopInfoKTV";


    /*发现模块*/
    /**资讯列表*/
    public static final String INFORMATION = "v1/Find/News/getNewsList";
    /**资讯详情*/
    public static final String INFORMATION_DETAILS = BASEURL+"v1/Find/News/showNews?news_id=";

    /*公共接口*/
    /**获取指定店铺的基本资料*/
    public static final String GETSHOPINFO="v1/Yule/Shop/getShopInfo";
    /**获取附近店铺列表*/
    public static final String GETSHOPLIST="v1/Yule/Shop/getShopList";
    /**获取某店铺*/
    public static final String GETCOMMENT="v1/Indent/Comment/getCommentList";
    /**获取商品列表*/
    public static final String GETGOODLIST="v1/Yule/Shop/getGoodsList";
    /**获取指定店铺的成员展示，如获取所有技师*/
    public static final String GETSHOPMEMBER="v1/Shop/Api/getShopTteamson";
    /**获取店铺指定成员信息，如获取技师信息*/
    public static final String GETMEMBERINFO="v1/Shop/Api/getShopTteamsonInfo";
    /**获取指定店铺类别下的所有标签*/
    public static final String GETSHOPLABELLIST="v1/Yule/Shop/getShopTypeLabelsList";
    /**检查APP是否需要更新*/
    public static final String APP_UPDATE=BASEHOST+"apk/user_update.php";
    /**我的爱车展示*/
    public static final String MYCAR_SHOW ="v1/My/Car/car";

    /*支付模块*/
//    public static final String CREATORDER=BASEHOST+"app.php/Indent/Api/createAcddAppIndent";
    /**创建订单*/
    public static final String CREATORDER="http://chengdu.cdrwx.cn/app.php/Indent/Api/createAcddAppIndent";
    /**进入支付页面时返回给客户端用户的代金券，积分，余额等信息*/
    public static final String GETWALLET="v1/My/Coupon/get_wallet";
    /**获取满足条件的代金券--支付时*/
    public static final String GET_COUPON="v1/My/Coupon/get_coupon";
    /**继续支付*/
    public static final String PAY_AGAIN="http://chengdu.cdrwx.cn/app.php/Indent/Api/payAcddIndent";
}
