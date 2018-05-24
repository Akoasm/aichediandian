package com.sinata.rwxchina.basiclib.basic.basicComment;

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
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 评价列表适配器
 * @modifyRecord
 */

public class CommentListAdapter extends BaseQuickAdapter<CommentEntity,BaseViewHolder> {
    private Context mC;
    public CommentListAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<CommentEntity> data) {
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
            }
        }
        NineGridView nineGridView=helper.getView(R.id.nineGrid);
        nineGridView.setAdapter(new NineGridViewClickAdapter(mC,imageInfo));
    }
}
