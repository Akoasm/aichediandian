package com.sinata.rwxchina.component_login.activity

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import com.jaeger.library.StatusBarUtil
import com.sinata.rwxchina.basiclib.HttpPath
import com.sinata.rwxchina.basiclib.utils.appUtils.CountDownUtils
import com.sinata.rwxchina.basiclib.base.BaseActivity
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo

import com.sinata.rwxchina.component_login.R
import com.sinata.rwxchina.retrofitutils.exception.ApiException
import kotlinx.android.synthetic.main.activity_find_pass_word.*
import kotlinx.android.synthetic.main.activity_login_pass_word.*

class FindPasswordActivity : BaseActivity() {
    override fun initParms(parms: Bundle?) {
    }

    override fun bindView(): View? {
        return null
    }

    override fun bindLayout(): Int {
        return R.layout.activity_find_pass_word
    }

    override fun initView(view: View?, bundle: Bundle?) {
        StatusBarUtil.setTransparentForImageViewInFragment(this@FindPasswordActivity, null)
        EditTextUtils.setHint(findpwd_phone,14)
        EditTextUtils.setHint(findpwd_code,14)
        EditTextUtils.setHint(findpwd_pwd,14)
    }

    override fun setListener() {
        findpwd_codedisplay.setOnClickListener(this)
        findpwd_finish.setOnClickListener(this)
        findpwd_ok.setOnClickListener(this)
        //查看密码点击监听
        findpwd_seePwd.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            buttonView, isChecked ->
            if (isChecked) {
                findpwd_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                findpwd_pwd.setSelection(findpwd_pwd.text.length)
            } else {
                findpwd_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                findpwd_pwd.setSelection(findpwd_pwd.text.length)
            }
        })
    }

    override fun widgetClick(v: View?) {
        when(v?.id){
            R.id.findpwd_codedisplay->getCode()
            R.id.findpwd_ok->tofind()
            R.id.findpwd_finish->finish()

        }
    }

    override fun doBusiness(mContext: Context?) {
    }

    /**
     * 找回密码
     */
    fun tofind(){
        val phone=findpwd_phone.text.toString()
        val code=findpwd_code.text.toString()
        val newPassword=findpwd_pwd.text.toString()
        if (phone.isEmpty()){
            showToast("请输入手机号！")
            return
        }
        if (code.isEmpty()){
            showToast("请输入验证码！")
            return
        }
        if (newPassword.isEmpty()){
            showToast("请输入新密码！")
            return
        }
        findPassword(phone,code,newPassword)
    }

    fun findPassword(phone:String,verify:String,password:String){
        val params= hashMapOf("phone" to phone,"verify" to verify,"password" to password)
        val apimanager=ApiManager(this,findPWDCallBack)
        apimanager.post(HttpPath.FINDPWD,params)
    }

    var findPWDCallBack:StringCallBack=object:StringCallBack(){
        override fun onResultNext(result: String?, method: String?, code: Int, msg: String?,pageInfo: PageInfo?) {
            showLog(result)
            finish()
        }

        override fun onResultError(e: ApiException?, method: String?) {
        }

    }

    /**
     * 获取验证码
     */
    fun getCode(){
        val phone=findpwd_phone.text.toString()
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
            CountDownUtils.countDown(findpwd_codedisplay,60)
        }

        override fun onResultError(e: ApiException?, method: String?) {
        }
    }

}
