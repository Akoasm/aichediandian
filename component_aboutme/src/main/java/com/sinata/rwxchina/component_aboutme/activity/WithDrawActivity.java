package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.bean.BankCardBean;
import com.sinata.rwxchina.component_aboutme.bean.WithDrawBean;
import com.sinata.rwxchina.component_aboutme.contract.WithDrawContract;
import com.sinata.rwxchina.component_aboutme.presenter.WithDrawPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：余额提现
 * @modifyRecord:修改记录
 */

public class WithDrawActivity extends BaseActivity implements WithDrawContract.View {
    private TextView bank, balance_tv, addbank, title, withdrawDetail;
    private EditText withDrawMoney;
    private ImageView back;
    private Button commit;
    private WithDrawPresenter withDrawPresenter;
    private Map<String, String> params;
    private WithDrawBean withDrawBean;
    private boolean isEmptyBank = true;
    private BankCardBean bankCardBean;
    private String balance;
    private View statusBar;

    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        bank = view.findViewById(R.id.withdraw_bank_tv);
        balance_tv = view.findViewById(R.id.withdraw_balance_tv);
        withDrawMoney = view.findViewById(R.id.withdrawMoney_et);
        commit = view.findViewById(R.id.withdraw_sure_btn);
        View titleView = findViewById(R.id.withdraw_title);
        back = titleView.findViewById(R.id.back);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        addbank = titleView.findViewById(R.id.rightIcon_tv);
        withdrawDetail = view.findViewById(R.id.withdrawDetail_tv);
        statusBar = view.findViewById(R.id.home_title_fakeview);

    }

    @Override
    public void setListener() {
        withDrawMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditTextUtils.decimalLimit(s, 2, withDrawMoney);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SpannableString spannableString = new SpannableString("请输入提现金额");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(20,true);
        spannableString.setSpan(span,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        withDrawMoney.setHint(spannableString);
        withDrawMoney.setOnClickListener(this);
        withdrawDetail.setOnClickListener(this);
        commit.setOnClickListener(this);
        back.setOnClickListener(this);
        addbank.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.withdraw_sure_btn) {
            checkParams();
        } else if (i == R.id.rightIcon_tv) {
            startActivityForResult(new Intent(this, AddBankCardActivity.class), 101);
        } else if (i == R.id.withdraw_bank_tv) {
            if (!isEmptyBank) {
                startActivityForResult(new Intent(this, BankCardActivity.class), 102);
            }
        } else if (i == R.id.withdrawDetail_tv) {
            startActivity(WithDrawDetailActivity.class);
        } else if (i == R.id.back) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 102:
                bankCardBean = (BankCardBean) data.getExtras().getSerializable("bankCard");
                bank.setText(bankCardBean.getBank_name() + "(" + bankCardBean.getLast_num() + ")");
                setParams(bankCardBean);
                isEmptyBank = false;
                break;
            case 101:
                withDrawPresenter.getData();
                break;
        }
    }

    private void setTitle() {
        title.setText("余额提现");
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_add_black);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        addbank.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        withDrawPresenter = new WithDrawPresenter(this);
        withDrawPresenter.attachView(this);
        getStatusBar(statusBar, ScreenUtils.getStatusHeight(this));
    }

    public void setParams(BankCardBean bankCardBean) {
        if (params == null)
            params = new HashMap<>();
        else
            params.clear();
        params.put("bank_name", bankCardBean.getBank_name());
        params.put("card_number", bankCardBean.getCard_number());
        params.put("user_name", bankCardBean.getUser_name());
        params.put("id", bankCardBean.getId());
    }

    @Override
    protected void onStart() {
        withDrawPresenter.getData();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
            initParms(bundle);
        super.onResume();
    }

    public void checkParams() {
        String withdrawmoney = withDrawMoney.getText().toString().trim();
        String balance = this.balance;
        if (!TextUtils.isEmpty(withdrawmoney) && !balance.equals("0.00")) {
            Double withdrawCrash = EditTextUtils.round(parseDouble(withdrawmoney), 2);
            Double balancemoney = EditTextUtils.round(parseDouble(balance), 2);
            if (withdrawCrash <= balancemoney && balancemoney > 0 && !TextUtils.isEmpty(withDrawBean.getBank().getBank_name())) {
                params.put("money", withdrawmoney);
                commitData();
            } else if (TextUtils.isEmpty(withDrawBean.getBank().getBank_name())){
                ToastUtils.showShort("请先添加银行卡");
            }else {
                ToastUtils.showShort("提现金额不能大于余额");
            }
        } else if (TextUtils.isEmpty(withdrawmoney)) {
            ToastUtils.showShort("请输入提现金额");
        } else {
            ToastUtils.showShort("无可提现金额");
        }
    }

    private void commitData() {
        withDrawPresenter.setParams(new WithDrawPresenter.Params() {
            @Override
            public Map<String, String> addParams() {
                return params;
            }
        });
        withDrawPresenter.commitData();
    }

    @Override
    public void showView(WithDrawBean withDrawBean) {
        this.withDrawBean = withDrawBean;
        balance_tv.setText(withDrawBean.getBalance());
        balance = withDrawBean.getBalance();
        if (withDrawBean.getBank().getCard_number()!=null) {
            bank.setText(withDrawBean.getBank().getBank_name() + "(" + withDrawBean.getBank().getLast_num() + ")");
            BankCardBean bankCardBean = new BankCardBean(withDrawBean.getBank().getId(), withDrawBean.getBank().getBank_name(), withDrawBean.getBank().getLast_num(), withDrawBean.getBank().getUser_name(), withDrawBean.getBank().getUid(), withDrawBean.getBank().getCard_number());
            setParams(bankCardBean);
            isEmptyBank = false;
        } else {
            bank.setText("请先添加银行卡");
            isEmptyBank = true;
        }
    }

    @Override
    public void withDrawSuccess(String msg) {
        ToastUtils.showShort(msg);
        withDrawPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        if (withDrawPresenter != null)
            withDrawPresenter.detachView();
        super.onDestroy();
    }

    /**
     * 设置假状态栏高度
     */
    private void getStatusBar(View view, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(WithDrawActivity.this, null);
    }

}
