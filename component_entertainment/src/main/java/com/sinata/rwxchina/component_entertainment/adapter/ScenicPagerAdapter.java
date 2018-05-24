package com.sinata.rwxchina.component_entertainment.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.component_entertainment.R;
import com.sinata.rwxchina.component_entertainment.entity.NearScenicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/19
 * @describe：景区viewpager适配器
 * @modifyRecord:
 */


public class ScenicPagerAdapter extends PagerAdapter {
    private List<NearScenicEntity.TopBean> list;
    private Context mC;

    public ScenicPagerAdapter( Context mC) {
        this.list = new ArrayList<>();
        this.mC = mC;
    }

    public List<NearScenicEntity.TopBean> getList() {
        return list;
    }

    public void setList(List<NearScenicEntity.TopBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mC, R.layout.layout_roll_view, null);
        ImageView imageView = view.findViewById(R.id.image);
//        String imgUrl=list.get(position % list.size());
        String imgUrl = list.get(position%list.size()).getDefault_image();
        //如果图片没有头地址，则添加头地址
        if ((!imgUrl.startsWith("http://"))&&(!imgUrl.startsWith("https://"))){
            imgUrl= HttpPath.IMAGEURL+imgUrl;
        }
        Glide.with(mC)
                .load(imgUrl)
                .apply(new RequestOptions()
                        .fallback(com.sinata.rwxchina.basiclib.R.mipmap.background)//请求图片的url为空
                        .placeholder(com.sinata.rwxchina.basiclib.R.mipmap.background)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .error(com.sinata.rwxchina.basiclib.R.mipmap.image_fail_c))
                .thumbnail(0.1f)
                .into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // onTouchViewPager方法一定要写在instantiateItem内部，表示触摸的是当前位置的页面
//        onTouchViewPager(view,position % imglist.size()+1);
        container.addView(view);
        return view;
    }
}
