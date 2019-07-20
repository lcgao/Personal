package com.lcgao.personal.profile.recent_read;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.WebActivity;
import com.lcgao.personal.adapter.CommonAdapter;
import com.lcgao.personal.adapter.ViewHolder;
import com.lcgao.personal.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentReadActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_nothing_tip)
    TextView tv_content;
    @BindView(R.id.rv_act_recent_read)
    RecyclerView recyclerView;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;

    CommonAdapter<RecentReadEntity> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_read);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(RecentReadActivity.this);
        toolbar.setTitle("最近浏览");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(RecentReadActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.getItemAnimator().setChangeDuration(0);
        mAdapter = new CommonAdapter<RecentReadEntity>(RecentReadActivity.this, R.layout.item_text_only, new ArrayList<RecentReadEntity>()){

            @Override
            public void convert(ViewHolder holder, final RecentReadEntity entity) {
                if(!TextUtils.isEmpty(entity.getTitle())){
                    holder.setText(R.id.tv_item_text_only_title, entity.getTitle());
                    holder.setVisibility(R.id.tv_item_text_only_title, View.VISIBLE);
                }else {
                    holder.setVisibility(R.id.tv_item_text_only_title, View.GONE);
                }
                if(!TextUtils.isEmpty(entity.getContent())){
                    holder.setText(R.id.tv_item_text_only_content, entity.getContent());
                    holder.setVisibility(R.id.tv_item_text_only_content, View.VISIBLE);
                }else {
                    holder.setVisibility(R.id.tv_item_text_only_content, View.GONE);
                }

                holder.setOnClickListener(R.id.layout_item_text_only, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("title", entity.getTitle());
                        bundle.putString("url", entity.getUrl());
                        bundle.putString("content", entity.getContent());
                        Intent intent = new Intent(RecentReadActivity.this, WebActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(mAdapter);
        List<RecentReadEntity> recentReadEntities =  RecentReadEntity.getAllReadHistory();
        setData(recentReadEntities);
//        for(int i = 0; i < recentReadEntities.size(); i ++){
//            RecentReadEntity entity = recentReadEntities.get(i);
//            tv_content.append("   -" + entity.getTitle() + "\n   -" + entity.getContent() + "\n   -" + entity.getUrl() + "\n");
//        }

    }

    public void setData(List<RecentReadEntity> recentReadEntities) {
        if (recentReadEntities == null || recentReadEntities.size() == 0) {
            llNothing.setVisibility(View.VISIBLE);
            return;
        }
        llNothing.setVisibility(View.GONE);
//        mNewsContent.clear();
//        mNewsContent.addAll(newsContents);
        mAdapter.replaceData(recentReadEntities);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action){
            AlertDialog.Builder builder = new AlertDialog.Builder(RecentReadActivity.this);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RecentReadEntity.deletAllHistory();
                    ToastUtil.s("清除成功");
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认清除全部内容吗？");
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
