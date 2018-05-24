package com.sinata.rwxchina.basiclib.utils.chooseimageutils;

import android.Manifest;
import android.content.pm.ActivityInfo;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.MyGlideEngine;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.tbruyelle.rxpermissions.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

/**
 * @author:zy
 * @detetime:2018/1/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ChooseImageUtils {

    /**
     *
     * @param activity
     * @param code
     * @param number
     */
    public static void chooseImage(final BaseActivity activity, final int code,int number) {
        Matisse.from(activity)
                .choose(MimeType.ofImage())
                .theme(R.style.Matisse_Dracula)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "com.sinata.rwxchina.aichediandian.fileprovider"))
                .maxSelectable(number)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new MyGlideEngine())
                .forResult(code);
    }
}
