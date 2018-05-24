package com.sinata.rwxchina.component_basic.scenic.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.basiclib.commonclass.entity.ScenicEntity;

import java.util.List;

/**
 *
 * @author wj
 * @date 2017/10/23
 * 景区详情页
 */

public class ScenicActivity extends BaseActivity {
    private ViewPager banner_vp;
    private LinearLayout banner_ll;
    private TextView name,address;
    private ImageView details_img;
    private MyScrollView myScrollView;
    private ScenicEntity scenicEntity;
    private View titleBarView;
    private View statusBar;
    @Override
    public void initParms(Bundle parms) {
        scenicEntity= (ScenicEntity) parms.getSerializable("scenic");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_scenic;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        banner_vp=findViewById(R.id.scenic_viewpager);
        banner_ll=findViewById(R.id.scenic_dots_ll);
        name=findViewById(R.id.scenic_name);
        address=findViewById(R.id.scenic_address);
        details_img=findViewById(R.id.scenic_details_img);
        myScrollView = findViewById(R.id.secnic_myscrollview);
        titleBarView = findViewById(R.id.scenic_title);
        statusBar = findViewById(R.id.title_bar_fakeview);
    }

    @Override
    public void setListener() {
    }

    @Override
    public void widgetClick(View v) {
    }

    @Override
    public void doBusiness(Context mContext) {
        setScenic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();

    }

    private void setScenic(){
        name.setText(scenicEntity.getGoods_name());
        address.setText(scenicEntity.getGoods_address());
        List<String> img=scenicEntity.getAddition_image();
        ViewPagerUtils viewPagerUtils=new ViewPagerUtils(banner_ll,banner_vp,this,img);
        viewPagerUtils.viewpagergo();
        ImageUtils.showImageAsBitmap(this,HttpPath.IMAGEURL+scenicEntity.getGoods_description_img().get(0),details_img);

    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,scenicEntity.getGoods_name());
        ImmersionUtils.setImmersionCanBack(getWindow(),this,myScrollView,titleBarView,statusBar);
    }
    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTransparentForImageViewInFragment(ScenicActivity.this, null);
    }

}
