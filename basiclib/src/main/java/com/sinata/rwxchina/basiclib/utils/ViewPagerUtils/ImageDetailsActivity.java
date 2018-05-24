package com.sinata.rwxchina.basiclib.utils.ViewPagerUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * @author HRR
 * @datetime 2018/2/26
 * @describe
 * @modifyRecord
 */

public class ImageDetailsActivity  extends Activity implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    /**
     * 用于管理图片的滑动
     */
    private ViewPager viewPager;

    /**
     * 显示当前图片的页数
     */
    private TextView pageText;
    ArrayList<String> list;
    int imagePosition;
    private long downTime;

    // 记录开始手指点击的位置,和滑动的X距离
    private int StartX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_details);
        ImmersionUtils.darkMode(getWindow(),false);
        list = getIntent().getStringArrayListExtra("list_img");
        imagePosition = getIntent().getIntExtra("image_position", 0);
        pageText = findViewById(R.id.page_text);

        pageText.setText((imagePosition) + "/" + (list.size()));
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(this);// 为ViewPager设置ontouch监听获取滚动距离
        viewPager.setCurrentItem(imagePosition - 1);
        viewPager.setOnPageChangeListener(this);
//		viewPager.setEnabled(false);
        // 设定当前的页数和总页数


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            // 记录用户手指点击的位置
            case MotionEvent.ACTION_DOWN:
                StartX = (int) ev.getX();
                downTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                if (System.currentTimeMillis() - downTime < 100
                        && Math.abs(StartX - ev.getX()) < 30) {
                    finish();
                }
                break;
        }
        return false;// return false,继续向下传递，return true;拦截,不向下传递
    }


    /**
     * ViewPager的适配器
     *
     * @author guolin
     */
    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ImageDetailsActivity.this).inflate(
                    R.layout.photoview, null);

            final PhotoView pv = view.findViewById(R.id.zoom_image_view);


//            Glide.with(getApplicationContext()).load(HttpPath.IMAGEURL + list.get(position)).error(R.mipmap.image_fail_c).into(pv);
            ImageUtils.showImage(getApplicationContext(), HttpPath.IMAGEURL +list.get(position),pv,R.mipmap.image_fail_c);

            ViewParent viewParent = view.getParent();
            if (viewParent != null) {
                ViewGroup parent = (ViewGroup) viewParent;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int currentPage) {


        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        pageText.setText((currentPage + 1) + "/" + (list.size()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}