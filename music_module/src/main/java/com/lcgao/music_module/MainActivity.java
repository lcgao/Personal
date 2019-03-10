package com.lcgao.music_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcgao.common_library.util.RouterUtil;
import com.lcgao.music_module.music.view.PlayMusicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = RouterUtil.MODULE_MUSIC_MAIN_ACTIVITY_URL)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_MY_MUSIC = 0;
    private static final int FRAGMENT_DISCOVER = 1;
    private static final int FRAGMENT_VIDEO = 2;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ll_layout_play_bar)
    LinearLayout mLlPlayBar;

    FragmentManager fragmentManager;

    private Fragment mFragmentMyMusic;
    private Fragment mFragmentDiscover;
    private Fragment mFragmentVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("发现");

        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                rotate(fab);
                Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_my_music);
        showFragment(FRAGMENT_MY_MUSIC);
    }

    /**
     * 不停顿地旋转
     *
     * @param view
     */
    private void rotate(View view) {
        if (view == null) {
            return;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        view.setAnimation(rotateAnimation);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_music) {
            showFragment(FRAGMENT_MY_MUSIC);
        } else if (id == R.id.nav_discover) {
            showFragment(FRAGMENT_DISCOVER);
        } else if (id == R.id.nav_video) {
            showFragment(FRAGMENT_VIDEO);
        } else if (id == R.id.nav_settings) {
            //TODO settings
        } else if (id == R.id.nav_share) {
            //TODO share
        } else if (id == R.id.nav_send) {
            //TODO send
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(int index) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case FRAGMENT_MY_MUSIC:
                if (mFragmentMyMusic == null) {
                    mFragmentMyMusic = new MyMusicFragment();
                    transaction.add(R.id.fragments, mFragmentMyMusic);
                } else {
                    transaction.show(mFragmentMyMusic);
                }
                toolbar.setTitle("我的音乐");

                break;
            case FRAGMENT_DISCOVER:
                if (mFragmentDiscover == null) {
                    mFragmentDiscover = new DiscoverFragment();
                    transaction.add(R.id.fragments, mFragmentDiscover);

                } else {
                    transaction.show(mFragmentDiscover);
                }
                toolbar.setTitle("发现");

                break;
            case FRAGMENT_VIDEO:
                if (mFragmentVideo == null) {
                    mFragmentVideo = new VideoFragment();
                    transaction.add(R.id.fragments, mFragmentVideo);

                } else {
                    transaction.show(mFragmentVideo);
                }
                toolbar.setTitle("视频");

                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mFragmentMyMusic != null) {
            transaction.hide(mFragmentMyMusic);
        }
        if (mFragmentDiscover != null) {
            transaction.hide(mFragmentDiscover);
        }
        if (mFragmentVideo != null) {
            transaction.hide(mFragmentVideo);
        }
    }

    @OnClick(R.id.ll_layout_play_bar)
    public void onClickPlayBar(){
        Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
        startActivity(intent);
    }
}
