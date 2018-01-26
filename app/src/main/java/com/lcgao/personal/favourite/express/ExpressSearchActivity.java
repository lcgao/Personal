package com.lcgao.personal.favourite.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.qing.stepviewlib.StepView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;
import com.lcgao.personal.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpressSearchActivity extends BaseActivity {
    @BindView(R.id.et_act_express_search_express_type)
    EditText etExpressName;
    @BindView(R.id.et_act_express_search_express_id)
    EditText etExpressId;
    @BindView(R.id.stepView)
    StepView mStepView;

    private InputMethodManager inputMethodManager;
    private String mExpressType = "jd";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.kuaidi100.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ExpressService mService = retrofit.create(ExpressService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_search);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(ExpressSearchActivity.this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        toolbar.setTitle("快递查询");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);

    }

    @OnClick(R.id.et_act_express_search_express_type)
    public void onClickExpressTypeSelect(View view) {
        Intent intent = new Intent(ExpressSearchActivity.this, ExpressesTypeActivity.class);
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.btn_act_express_search)
    public void onClickExpressSearch(View view) {
        if ((etExpressName.getText() + "").equals("选择快递公司")) {
            ToastUtil.s("请选择快递公司");
            return;
        }
        if (TextUtils.isEmpty(etExpressId.getText())) {
            ToastUtil.s("请填写快递单号");
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(etExpressId.getWindowToken(), 0);

        etExpressId.setFocusable(false);
        etExpressId.setFocusableInTouchMode(true);
        Map<String, String> map = new HashMap<>();
        map.put("type", mExpressType);
        map.put("postid", etExpressId.getText() + "");
        map.put("id", "");
        map.put("valicode", "");
        map.put("temp", "");
        mService.searchExpress(map)
                .clone()
                .enqueue(new Callback<ExpressInfo>() {
                    @Override
                    public void onResponse(Call<ExpressInfo> call, Response<ExpressInfo> response) {
                        ExpressInfo expressInfo = response.body();
                        if (expressInfo == null) {
                            LogUtil.l("response.body()为空");
                            ToastUtil.s("查询失败");
                            mStepView.setVisibility(View.GONE);
                            return;
                        }
                        if (!expressInfo.getStatus().equals("200")) {
                            LogUtil.l(expressInfo.getMessage());
                            ToastUtil.s("查询失败，" + expressInfo.getMessage());
                            mStepView.setVisibility(View.GONE);

                            return;
                        }
                        List<ExpressData> datas = expressInfo.getData();
                        mStepView.setVisibility(View.VISIBLE);

                        mStepView.setDatas(datas);
                        mStepView.setBindViewListener(new StepView.BindViewListener() {
                            @Override
                            public void onBindView(TextView itemMsg, TextView itemDate, Object data) {
                                ExpressData express = (ExpressData) data;
                                itemMsg.setText(express.getContext());
                                itemDate.setText(express.getTime());
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ExpressInfo> call, Throwable t) {
                        ToastUtil.s("查询失败");
                        mStepView.setVisibility(View.GONE);

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String expressName = data.getStringExtra("express_name");
                mExpressType = data.getStringExtra("express_type");
                etExpressName.setText(expressName);
            }
        }
    }
}
