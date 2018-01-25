package com.lcgao.personal.favourite.express;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lcgao.personal.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

public class ExpressesTypeActivity extends BaseActivity {
    @BindView(R.id.il_act_express)
    IndexableLayout ilExpress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ExpressTypeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expresses);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(ExpressesTypeActivity.this);
        toolbar.setTitle("快递公司");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);

        mAdapter = new ExpressTypeAdapter(ExpressesTypeActivity.this);
//        mAdapter.setDatas();
        ilExpress.setLayoutManager(new LinearLayoutManager(ExpressesTypeActivity.this));
        ilExpress.setOverlayStyle_MaterialDesign(getResources().getColor(R.color.themecolor));
        ilExpress.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        ilExpress.setAdapter(mAdapter);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        String json = getJson(ExpressesTypeActivity.this, "express_type.json");
//                LogUtil.d(json);
        String[] datas = json.replace("{", "")
                .replace("}", "")
                .replaceAll("\"", "")
                .replaceAll(" ", "")
                .split(",");
        List<ExpressType> expressTypes = new ArrayList<>();
        for (String data : datas) {
            String[] express = data.split(":");
            ExpressType expressType = new ExpressType(express[0], express[1]);
            LogUtil.d(expressType.toString());
            expressTypes.add(expressType);
        }
        mAdapter.setDatas(expressTypes);
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<ExpressType>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, ExpressType entity) {
                Intent intent = new Intent();
                intent.putExtra("express_name", entity.getExpressName());
                intent.putExtra("express_type", entity.getExpressType());
                setResult(-1, intent);
                finish();
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    private String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
