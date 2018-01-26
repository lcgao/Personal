package com.lcgao.personal.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lcgao.personal.R;
import com.lcgao.personal.favourite.express.ExpressSearchActivity;
import com.lcgao.personal.favourite.music.MusicActivity;
import com.lcgao.personal.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcgao on 2017/12/27.
 */

public class FavouriteFragment extends Fragment{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        toolbar.setTitle("Favourite");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @OnClick(R.id.layout_express)
    public void onClickExpressSearch() {
        startActivity(new Intent(getActivity(), ExpressSearchActivity.class));
    }

    @OnClick(R.id.layout_music)
    public void onClickMusic(){
        startActivity(new Intent(getActivity(), MusicActivity.class));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                ToastUtil.s("Settings");
                break;
                default:
                    ToastUtil.s("Default");
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
