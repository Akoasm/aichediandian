package com.sinata.rwxchina.basiclib.utils.controlutils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe textview操作类
 * @modifyRecord
 */

public class TextviewUtils {

    /**
     * textview设置中划线
     * @param textView
     */
    public static void setStrikethrough(TextView textView){
        textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG| Paint.ANTI_ALIAS_FLAG);
    }
}
