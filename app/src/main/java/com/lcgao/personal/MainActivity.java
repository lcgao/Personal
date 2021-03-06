package com.lcgao.personal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.favourite.FavouriteFragment;
import com.lcgao.personal.home.HomeFragment;
import com.lcgao.personal.profile.ProfileFragment;
import com.lcgao.personal.util.IMMLeaks;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVOURITE = 1;
    private static final int FRAGMENT_PROFILE = 2;
    @BindView(R.id.navigation_content_main)
    BottomNavigationView mNavigationView;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    FragmentManager fragmentManager;

    private Fragment mFragmentHome;
    private Fragment mFragmentFavourite;
    private Fragment mFragmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                drawer.setFitsSystemWindows(true);
//                drawer.setClipToPadding(false);
//            }
        }
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        showFragment(FRAGMENT_HOME);
                        return true;
                    case R.id.navigation_favourite:
                        showFragment(FRAGMENT_FAVOURITE);
                        return true;
                    case R.id.navigation_profile:
                        showFragment(FRAGMENT_PROFILE);
                        return true;
                }
                return false;
            }
        });
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Time");
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
        showFragment(0);
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {

    }

    private void showFragment(int index) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case FRAGMENT_HOME:
                if (mFragmentHome == null) {
                    mFragmentHome = new HomeFragment();
                    transaction.add(R.id.fragments, mFragmentHome);
                } else {
                    transaction.show(mFragmentHome);
                }
//                toolbar.setTitle("Time");

                break;
            case FRAGMENT_FAVOURITE:
                if (mFragmentFavourite == null) {
                    mFragmentFavourite = new FavouriteFragment();
                    transaction.add(R.id.fragments, mFragmentFavourite);

                } else {
                    transaction.show(mFragmentFavourite);
                }
//                toolbar.setTitle("Favourite");

                break;
            case FRAGMENT_PROFILE:
                if (mFragmentProfile == null) {
                    mFragmentProfile = new ProfileFragment();
                    transaction.add(R.id.fragments, mFragmentProfile);

                } else {
                    transaction.show(mFragmentProfile);
                }
//                toolbar.setTitle("Profile");

                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mFragmentHome != null) {
            transaction.hide(mFragmentHome);
        }
        if (mFragmentFavourite != null) {
            transaction.hide(mFragmentFavourite);
        }
        if (mFragmentProfile != null) {
            transaction.hide(mFragmentProfile);
        }
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    @Override
    public void onBackPressed() {
        fragmentManager.popBackStack();
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    //    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_note) {
//            // Handle the camera action
//        } else if (id == R.id.nav_diary) {
//
//        } else if (id == R.id.nav_essay) {
//
//        } else if (id == R.id.nav_entertainment) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        IMMLeaks.fixFocusedViewLeak(getApplication());
    }
}
