package com.sinata.rwxchina.component_aboutme.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.chooseimageutils.ChooseImageUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionCall;
import com.sinata.rwxchina.basiclib.utils.permissionutils.PermissionUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.view.PickerViewUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;
import com.sinata.rwxchina.component_aboutme.contract.ApplyRefundContract;
import com.sinata.rwxchina.component_aboutme.presenter.ApplyRefundPresenter;
import com.tbruyelle.rxpermissions.Permission;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：商城订单申请退款
 * @modifyRecord:修改记录
 */

public class ApplyRefundActivity extends BaseActivity implements ApplyRefundContract.View {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private TextView commdityName, commodityCount, commodityPrice, changeCommodity, refund, title;
    private EditText problem;
    private Button submit;
    private ImageView commodityImage, descriptionImage1, descriptionImage2, descriptionImage3, deleteimage1, deleteimage2, deleteimage3, back;
    private OrderBean orderBean;
    private RefundOrderBean refundOrderBean;
    private ApplyRefundPresenter applyRefundPresenter;
    private int refundType = 0;
    private String description, sendDate;
    private Map<String, File> imageList;
    private List<File> imageParamsList;
    private Map<String, String> imagePath;
    private Map<String, String> params;
    private int flag = 1;
    private boolean isFail;
    private EditText mApplyRefundOrderNumberEt;
    private TextView mApplyRefundSendTimeTv;


    @Override
    public void initParms(Bundle parms) {
        if (parms!=null) {
            isFail = parms.getBoolean("isFail", false);
            if (isFail) {
                refundOrderBean = (RefundOrderBean) parms.getSerializable("orderInfo");
            } else
                orderBean = (OrderBean) parms.getSerializable("orderInfo");
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_applyrefund_mall;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        imagePath = new HashMap<>();
        imageList = new HashMap<>();
        params = new HashMap<>();
        imageParamsList = new ArrayList<>();
        View commodityInfo = view.findViewById(R.id.applyRefund_commodityInfo_rl);
        commdityName = commodityInfo.findViewById(R.id.commodityName_tv);
        commodityImage = commodityInfo.findViewById(R.id.commodityImage_iv);
        commodityCount = commodityInfo.findViewById(R.id.commodityCount_tv);
        commodityPrice = commodityInfo.findViewById(R.id.commodityPrice_tv);
        changeCommodity = view.findViewById(R.id.applyRefund_changeCommodity_tv);
        refund = view.findViewById(R.id.applyRefund_refundCommodity_tv);
        problem = view.findViewById(R.id.applyRefund_problemDescription_et);
        descriptionImage1 = view.findViewById(R.id.applyRefund_addImage1_iv);
        descriptionImage2 = view.findViewById(R.id.applyRefund_addImage2_iv);
        descriptionImage3 = view.findViewById(R.id.applyRefund_addImage3_iv);
        deleteimage1 = view.findViewById(R.id.applyRefund_deleteImage1_iv);
        deleteimage2 = view.findViewById(R.id.applyRefund_deleteImage2_iv);
        deleteimage3 = view.findViewById(R.id.applyRefund_deleteImage3_iv);
        submit = view.findViewById(R.id.applyRefund_submit_btn);
        mApplyRefundOrderNumberEt = findViewById(R.id.applyRefund_orderNumber_et);
        mApplyRefundSendTimeTv = findViewById(R.id.applyRefund_sendTime_tv);
        View titleView = view.findViewById(R.id.applyRefund_tilte);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        setTitle();
        changeCommodity.setOnClickListener(this);
        refund.setOnClickListener(this);
        descriptionImage1.setOnClickListener(this);
        descriptionImage2.setOnClickListener(this);
        descriptionImage3.setOnClickListener(this);
        deleteimage1.setOnClickListener(this);
        deleteimage2.setOnClickListener(this);
        deleteimage3.setOnClickListener(this);
        submit.setOnClickListener(this);
        mApplyRefundSendTimeTv.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.applyRefund_changeCommodity_tv) {
            changeCommodity.setBackgroundResource(R.mipmap.button_selected_bg);
            changeCommodity.setTextColor(Color.rgb(255, 255, 255));
            refund.setBackgroundResource(R.drawable.orderrefundstyle);
            refund.setTextColor(Color.rgb(51, 51, 51));
            refundType = 52;
            params.put("rtype", refundType + "");
        } else if (i == R.id.applyRefund_refundCommodity_tv) {
            refund.setBackgroundResource(R.mipmap.button_selected_bg);
            refund.setTextColor(Color.rgb(255, 255, 255));
            changeCommodity.setBackgroundResource(R.drawable.orderrefundstyle);
            changeCommodity.setTextColor(Color.rgb(51, 51, 51));
            refundType = 53;
            params.put("rtype", refundType + "");
        } else if (i == R.id.applyRefund_addImage1_iv) {
            flag = 1;
            chooseImage();
        } else if (i == R.id.applyRefund_addImage2_iv) {
            flag = 2;
            chooseImage();
        } else if (i == R.id.applyRefund_addImage3_iv) {
            flag = 3;
            chooseImage();
        } else if (i == R.id.applyRefund_deleteImage1_iv) {
            deleteImage(deleteimage1, descriptionImage1, "1");

        } else if (i == R.id.applyRefund_deleteImage2_iv) {
            deleteImage(deleteimage2, descriptionImage2, "2");

        } else if (i == R.id.applyRefund_deleteImage3_iv) {
            deleteImage(deleteimage3, descriptionImage3, "3");

        } else if (i == R.id.applyRefund_submit_btn) {
            description = problem.getText().toString().trim();
            if (!TextUtils.isEmpty(description) && refundType != 0) {
                String expressNumber = mApplyRefundOrderNumberEt.getText().toString().trim();
                if (!TextUtils.isEmpty(expressNumber))
                    params.put("express_num",expressNumber);
                setParmas();
                setImageParams();
                LogUtils.e("xzc",params.get("express_time"));
                if (imageList.size() > 0)
                    applyRefundPresenter.postData(params, imageParamsList);
                else
                    applyRefundPresenter.postData(params, null);
            } else if (TextUtils.isEmpty(description)) {
                ToastUtils.showShort("退款理由不能为空");
            } else if (refundType == 0) {
                ToastUtils.showShort("请选择类别");
            }

        } else if (i == R.id.applyRefund_sendTime_tv) {
            PickerViewUtils.initTimePicker(this, new boolean[]{true, true, true, false, false, false}, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    mApplyRefundSendTimeTv.setText(PickerViewUtils.getdayTime(date));
                    sendDate = PickerViewUtils.getdayTime(date);
                    params.put("express_time", sendDate);
                }
            });
        }
    }

    private void deleteImage(ImageView delete, ImageView imageView, String i) {
        delete.setVisibility(View.GONE);
        imageView.setImageResource(R.mipmap.addimage_bg);
        if (imageList.get(i) != null)
            imageList.remove(i);
    }

    private void chooseImage() {
        PermissionUtils.permissonMoreAll(this, new PermissionCall() {
            @Override
            public void success() {
                ChooseImageUtils.chooseImage(ApplyRefundActivity.this, REQUEST_CODE_CHOOSE, 1);
            }

            @Override
            public void fail() {
                ToastUtils.showShort("缺少必要权限");
            }

            @Override
            public void next(Permission permission) {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    private void setImageParams() {
        if (imageList.size() > 0) {
            for (int i = 0; i < imageList.size(); i++) {
                if (imageList.get(i + 1 + "") != null) {
                    imageParamsList.add(i, new File(imagePath.get(i + 1 + "")));
                }
            }
        }
    }

    private void setParmas() {
        params.put("reason", description);
        if (isFail) {
            params.put("orderson", refundOrderBean.getOrderson());
            params.put("total_money", refundOrderBean.getPay_money());
            params.put("goods_name", refundOrderBean.getGoods_name());
        } else {
            params.put("orderson", orderBean.getOrderson());
            params.put("total_money", orderBean.getPay_money());
            params.put("goods_name", orderBean.getGoods_name());
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        showCommodity();
        applyRefundPresenter = new ApplyRefundPresenter(this);
        applyRefundPresenter.attachView(this);
    }


    @Override
    public void showView(String msg) {
        ToastUtils.showShort(msg);
    }

    private void showCommodity() {
        if (isFail) {
            commdityName.setText(refundOrderBean.getGoods_name());
            commodityCount.setText("x" + refundOrderBean.getGoods_number());
            ImageUtils.showImage(this, HttpPath.IMAGEURL + refundOrderBean.getGoods_img(), commodityImage);
            commodityPrice.setText(refundOrderBean.getGoods_price());
        } else {
            commdityName.setText(orderBean.getGoods_name());
            commodityCount.setText("x" + orderBean.getGoods_number());
            ImageUtils.showImage(this, HttpPath.IMAGEURL + orderBean.getGoods_img(), commodityImage);
            commodityPrice.setText(orderBean.getGoods_price());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            switch (flag) {
                case 1:
                    ImageUtils.showImage(this, Matisse.obtainResult(data).get(0), descriptionImage1, 60);
                    deleteimage1.setVisibility(View.VISIBLE);
                    imagePath.put("1", Matisse.obtainPathResult(data).get(0));
                    break;
                case 2:
                    ImageUtils.showImage(this, Matisse.obtainResult(data).get(0), descriptionImage2, 60);
                    deleteimage2.setVisibility(View.VISIBLE);
                    imagePath.put("2", Matisse.obtainPathResult(data).get(0));
                    break;
                case 3:
                    ImageUtils.showImage(this, Matisse.obtainResult(data).get(0), descriptionImage3, 60);
                    deleteimage3.setVisibility(View.VISIBLE);
                    imagePath.put("3", Matisse.obtainPathResult(data).get(0));
                    break;
            }
            getImageFromLocal();
//            setImageParams();
        }
    }

    private void getImageFromLocal() {
        if (imagePath.size() > 0) {
            for (int i = 0; i < imagePath.size(); i++) {
                if (imagePath.get(i + 1 + "") != null) {
                    imageList.put(i + 1 + "", new File(imagePath.get(i + 1 + "")));
                }
            }
        }
    }

    private void setTitle() {
        title.setText("退款");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        applyRefundPresenter.detachView();
        super.onDestroy();
    }
}
