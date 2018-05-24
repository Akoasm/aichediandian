package com.sinata.rwxchina.component_basic.basic.basiccomment;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentEntity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 评论列表适配器
 * @modifyRecord
 */

public class CommentAdapter extends BaseQuickAdapter<CommentEntity,BaseViewHolder> {
    private Context mC;
    //默认显示图片张数：3张，如需显示其他张数，需调set方法
    private int num=3;

    public void setNum(int num) {
        this.num = num;
    }

    public CommentAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<CommentEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentEntity item) {
        helper.setText(R.id.pingjia_name,item.getUser_name())
                .setText(R.id.pingjia_time,item.getCreatedate())
                .setText(R.id.pingjia_tv,item.getEvaluation_comment());
        ImageUtils.showCircleImage(mC, HttpPath.IMAGEURL+item.getUser_head(), (ImageView) helper.getView(R.id.comment_item_name));
        //传入到九宫格的集合
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<String> imageDetails = item.getEvaluation_comment_pic();
        if (imageDetails != null) {
            for (String imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(HttpPath.IMAGEURL+imageDetail);
                info.setBigImageUrl(HttpPath.IMAGEURL+imageDetail);
                imageInfo.add(info);
                if (imageInfo.size()>=num){
                    break;
                }
            }
        }
        NineGridView nineGridView=helper.getView(R.id.nineGrid);
        nineGridView.setAdapter(new NineGridViewClickAdapter(mC,imageInfo));
    }
}
