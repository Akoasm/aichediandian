package com.sinata.rwxchina.component_basic.basic.basiclist.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.ClassfiyEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe
 * @modifyRecord
 */

public class ClassfiyAdapter extends BaseQuickAdapter<ClassfiyEntity,BaseViewHolder> {
    private Context mC;
    private String classfiy;
    public ClassfiyAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<ClassfiyEntity> data,String classfiy) {
        super(layoutResId, data);
        this.mC=context;
        this.classfiy=classfiy;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassfiyEntity item) {
        TextView textView=helper.getView(R.id.classify);
        if (item.getLabel_id().equals(classfiy)){
            textView.setTextColor(mC.getResources().getColor(R.color.colorOrange));
            textView.setBackgroundDrawable(mC.getResources().getDrawable(R.drawable.right_angle_border_orange));
        }else {
            textView.setTextColor(mC.getResources().getColor(R.color.text_hint));
            textView.setBackgroundDrawable(mC.getResources().getDrawable(R.drawable.right_angle_border));
        }
        helper.setText(R.id.classify,item.getLabel_name());
    }
}
