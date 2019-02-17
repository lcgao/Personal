package com.lcgao.personal;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lcgao.common_library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_act_text)
    TextView textView;
    @BindView(R.id.tv_activity_text_title)
    TextView mTitle;

    String title;
    String content;
    boolean isHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initParas(getIntent().getExtras());
        initView();
    }

    @Override
    public void initParas(Bundle paras) {
        if (paras == null) {
            return;
        }
        title = paras.getString("title");
        content = paras.getString("content");
        isHtml = paras.getBoolean("is_html");
    }

    @Override
    public void initView() {
        ButterKnife.bind(TextActivity.this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        mTitle.setText(title);
        if (isHtml) {
            textView.setText(Html.fromHtml(content));
        } else {
            textView.setText(content);
        }
    }
}
