package com.sinata.rwxchina.basiclib.utils.imageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lzy.ninegrid.NineGridView;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe 九宫格图片加载器（glide加载）
 * @modifyRecord
 */

public class GlideLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        ImageUtils.showImage(context,url,imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
