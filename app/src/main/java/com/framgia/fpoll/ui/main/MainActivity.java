package com.framgia.fpoll.ui.main;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityMainBinding;
import com.framgia.fpoll.ui.pollcreation.CreatePollFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

public class MainActivity extends AppCompatActivity
    implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    private MainPresenter mPresenter;
    private ActivityMainBinding mBinding;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mPresenter = new MainPresenter(this);
    }

    @Override
    public void start() {
        Toolbar toolbar = mBinding.toolbarLayout.toolbar;
        setSupportActionBar(toolbar);
        setTitle(R.string.title_home);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.action_open,
                R.string.action_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guide:
                showHelp();
                break;
            case R.id.action_history:
                addFragment(new HistoryFragment(), R.string.title_history);
                break;
            default:
                break;
            case R.id.action_home:
                addFragment(new CreatePollFragment(), R.string.title_home);
                break;
        }
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void addFragment(Fragment fragment, int title) {
        ActivityUtil
            .addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frame_layout);
        setTitle(title);
    }

    @Override
    public void showHelp() {
        Uri helpUri = Uri.parse(Constant.WebUrl.HELP_URL);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.color_teal_500))
            .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.color_teal_800))
            .build().launchUrl(this, helpUri);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }
}
