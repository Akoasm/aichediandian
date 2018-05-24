package com.sinata.rwxchina.component_login.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import com.jaeger.library.StatusBarUtil
import com.sinata.rwxchina.basiclib.HttpPath
import com.sinata.rwxchina.basiclib.base.BaseActivity
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo
import com.sinata.rwxchina.basiclib.utils.sharedutils.SharedUserUtils
import com.sinata.rwxchina.component_login.R
import com.sinata.rwxchina.retrofitutils.exception.ApiException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login_pass_word.*
import org.json.JSONObject

class LoginPasswordActivity : BaseActivity() {
    override fun initParms(parms: Bundle?) {
    }

    override fun bindView(): View? {
        return null
    }

    override fun bindLayout(): Int {
        return R.layout.activity_login_pass_word
    }

    override fun initView(view: View?, bundle: Bundle?) {
        StatusBarUtil.setTransparentForImageViewInFragment(this@LoginPasswordActivity, null)
        EditTextUtils.setHint(pwdLogin_phone,14)
        EditTextUtils.setHint(pwdLogin_pwd,14)
    }

    override fun setListener() {
        pwdLogin_finish.setOnClickListener(this)
        pwdLogin_clean.setOnClickListener(this)
        //查看密码点击监听
        pwdLogin_seePwd.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            buttonView, isChecked ->
            if (isChecked) {
                pwdLogin_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                pwdLogin_pwd.setSelection(pwdLogin_pwd.text.length)
            } else {
                pwdLogin_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                pwdLogin_pwd.setSelection(pwdLogin_pwd.text.length)
            }
        })
        pwdLogin_to_acdd.setOnClickListener(this)
        findPwd.setOnClickListener(this)

    }

    override fun widgetClick(v: View?) {
        when(v?.id){
            R.id.pwdLogin_finish-> finish()
            R.id.pwdLogin_seePwd-> seePwd()
            R.id.pwdLogin_clean-> cleanPhone()
            R.id.pwdLogin_to_acdd-> toacdd()
            R.id.findPwd-> findPwd()
        }
    }

    override fun doBusiness(mContext: Context?) {

    }

    fun login(username:String,password:String){

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
        val params= hashMapOf("username" to username,"password" to password,"mobiletype" to "Android",
                "mobilemodel" to mobilemodel,"systemmodel" to systemmodel,"appversion" to appversion,"appitem" to "爱车用户端")
        val apimanager=ApiManager(this,loginCallBack)
        apimanager.post(HttpPath.LOGINPWD,params)
    }
    var loginCallBack:StringCallBack=object :StringCallBack(){
        override fun onResultNext(result: String?, method: String?, code: Int, msg: String?,pageInfo: PageInfo?) {
            showLog(result)
            val jsonObject= JSONObject(result)
            val uid = jsonObject.optString("uid")
            val token = jsonObject.optString("token")
            //保存登录手机号
            SharedUserUtils.saveLoginPhone(applicationContext,pwdLogin_phone.text.toString().trim())
            CommonParametersUtils.saveUid(applicationContext, uid)
            CommonParametersUtils.saveToken(applicationContext, token)
            gotoMain()
        }


        override fun onResultError(e: ApiException?, method: String?) {
        }

    }


    /**
     * 找回密码
     */
    fun findPwd(){
        val findpwd=Intent(this,FindPasswordActivity::class.java)
        startActivity(findpwd)
    }

    /**
     * 查看密码
     */
    fun seePwd(){
        if (pwdLogin_seePwd.isChecked){

        }else{
        }
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
     * 清除手机号
     */
    fun cleanPhone(){
        pwdLogin_phone.setText("")
    }

    /**
     *
     */
    fun toacdd(){
        val phone=pwdLogin_phone.text.toString()
        val pwd=pwdLogin_pwd.text.toString()
        if (phone.isEmpty()){
            showToast("请输入手机号")
            return
        }
        if (pwd.isEmpty()){
            showToast("请输入密码")
            return
        }
        login(phone,pwd)
    }

}

