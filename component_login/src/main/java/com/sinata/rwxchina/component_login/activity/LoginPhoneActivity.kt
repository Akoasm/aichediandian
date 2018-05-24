@file:Suppress("UNREACHABLE_CODE")

package com.sinata.rwxchina.component_login.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import com.jaeger.library.StatusBarUtil
import com.sinata.rwxchina.basiclib.HttpPath
import com.sinata.rwxchina.basiclib.utils.appUtils.CountDownUtils
import com.sinata.rwxchina.basiclib.base.BaseActivity
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo
import com.sinata.rwxchina.basiclib.utils.sharedutils.SharedUserUtils
import com.sinata.rwxchina.basiclib.webViewUtils.activity.DefaultWebViewActivity
import com.sinata.rwxchina.component_login.R
import com.sinata.rwxchina.retrofitutils.exception.ApiException
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginPhoneActivity : BaseActivity() {
    override fun initParms(parms: Bundle?) {
    }

    override fun bindView(): View? {
        return null
    }

    override fun bindLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView(view: View?, bundle: Bundle?) {
        StatusBarUtil.setTransparentForImageViewInFragment(this@LoginPhoneActivity, null)
        EditTextUtils.setHint(phoneLogin_phone,14)
        EditTextUtils.setHint(phoneLogin_code,14)
    }


    override fun setListener() {
        phoneLogin_finish.setOnClickListener(this)
        phoneLogin_clean.setOnClickListener(this)
        phoneLogin_to_acdd.setOnClickListener(this)
        acdd_agreement.setOnClickListener(this)
        pwdLogin.setOnClickListener(this)
        phoneLogin_codedisplay.setOnClickListener(this)
    }

    override fun widgetClick(v: View?) {
        when(v?.id){
            R.id.phoneLogin_finish ->
                    finish()
            R.id.phoneLogin_clean->
                phoneLogin_phone.setText("")
            R.id.phoneLogin_to_acdd->
                    toacdd()
            R.id.acdd_agreement->
                    agreement()
            R.id.pwdLogin->
                    pwdLogin()
            R.id.phoneLogin_codedisplay->
                    getCode()
        }
    }

    override fun doBusiness(mContext: Context?) {

    }


    var loginCallBack:StringCallBack=object :StringCallBack(){
        override fun onResultNext(result: String?, method: String?, code: Int, msg: String?,pageInfo: PageInfo?) {
//            showLog(result)
            LogUtils.e("zzz","111111111111")
            val jsonObject=JSONObject(result)
            val uid = jsonObject.optString("uid")
            val token = jsonObject.optString("token")
            val agent = jsonObject.optString("is_safeagent")
            if (agent.equals("1")){
                CommonParametersUtils.saveIdentity(applicationContext,true)
            }
            CommonParametersUtils.saveUid(applicationContext, uid)
            CommonParametersUtils.saveToken(applicationContext, token)
            //保存登录手机号
            SharedUserUtils.saveLoginPhone(applicationContext,phoneLogin_phone.text.toString().trim())
            gotoMain()
        }

        override fun onResultError(e: ApiException?, method: String?) {
        }
    }

    /**
     * 进入爱车点点
     */
    fun toacdd(){
        if (phoneLogin_phone.text.toString().isEmpty()){
            showToast("请输入手机号")
            return
        }
        if (phoneLogin_code.text.toString().isEmpty()){
            showToast("请输入验证码")
            return
        }
        val phone=phoneLogin_phone.text.toString().trim()
        val verify= phoneLogin_code.text.toString().trim()
        val mobilemodel = Build.MANUFACTURER + "-" + Build.MODEL//手机厂商+手机型号
        val systemmodel = Build.VERSION.RELEASE//系统版本
        //app版本
        var appversion = ""
        val packageManager = packageManager
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo("com.sinata.rwxchina.aichediandian", 0)
        } catch (e: PackageManager.NameNotFoundException) {

            e.printStackTrace()
        }

        if (packageInfo != null) {
            appversion = packageInfo.versionName
        }
        val retrofit=ApiManager(this,loginCallBack)
        val params= hashMapOf("phone" to phone,"verify" to verify,"mobiletype" to "Android",
                "mobilemodel" to mobilemodel,"systemmodel" to systemmodel,"appversion" to appversion,"appitem" to "爱车用户端")
        retrofit.get(HttpPath.LOGINPHONE,params)
    }

    /**
     * 协议
     */
    fun agreement(){
        val intent=Intent(this,DefaultWebViewActivity::class.java)
        intent.putExtra("url", HttpPath.WEB_AGREEMENT_USER)
        startActivity(intent)
    }

    /**
     * 跳转到首页
     */
    fun gotoMain(){
        val cla=Class.forName("com.sinata.rwxchina.aichediandian.MainActivity")
        val pwdlogin=Intent(this, cla)
        startActivity(pwdlogin)
        finish()
    }

    /**
     * 密码登录
     */
    fun pwdLogin(){
        val pwdlogin=Intent(this,LoginPasswordActivity::class.java)
        startActivity(pwdlogin)
    }

    /**
     * 获取验证码
     */
    fun getCode(){
        val phone=phoneLogin_phone.text.toString()
        if (phone.isEmpty()){
            showToast("请输入手机号")
            return
        }
        val retrofit=ApiManager(this,getCodeCallBack)
        val params= hashMapOf("phone" to phone)
        retrofit.get(HttpPath.GETCODE,params)
    }
    var getCodeCallBack:StringCallBack=object :StringCallBack(){
        override fun onResultNext(result: String?, method: String?, code: Int, msg: String?,pageInfo: PageInfo?) {
            showLog(result)
            CountDownUtils.countDown(phoneLogin_codedisplay,60)
        }

        override fun onResultError(e: ApiException?, method: String?) {
        }
    }

}
