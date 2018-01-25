package com.lcgao.personal.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcgao.personal.R;
import com.lcgao.personal.favourite.express.ExpressSearchActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcgao on 2017/12/27.
 */

public class FavouriteFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
    }

    @OnClick(R.id.layout_express)
    public void onClickSearch() {
        startActivity(new Intent(getActivity(), ExpressSearchActivity.class));
    }
}
