package com.elvis.mzmanager;

import android.Manifest;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.idogfooding.backbone.ui.BaseActivity;
import com.idogfooding.backbone.widget.CountDownTimerWithPause;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Splash
 *
 * @author Charles
 */
@Route(value = {"Splash"})
public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @Override
    protected int getLayoutId() {
        return R.layout.splash;
    }

    // countDownTimer
    CountDownTimerWithPause countDownTimer;

    @Override
    protected void onSetup(Bundle savedInstanceState) {
        super.onSetup(savedInstanceState);

        BarUtils.setStatusBarVisibility(this, false);
    }
    @Override
    protected void onConfig(Bundle savedInstanceState) {
        return;
    }

    protected void initViews() {

        loadImage(ivSplash, R.mipmap.ic_splash, R.mipmap.ic_splash, R.mipmap.ic_splash);
        //loadImage(ivSplashSlogan, R.mipmap.splash_slogan, R.mipmap.splash_slogan, R.mipmap.splash_slogan);


        //强制用户授权
        askForPermissions(Manifest.permission.INTERNET);
    }

    @Override
    protected void afterPermissionGranted() {
        // tvSkip.setVisibility(View.VISIBLE);
        // init counter down timer
        countDownTimer = new CountDownTimerWithPause(DateUtils.SECOND_IN_MILLIS * 2, DateUtils.SECOND_IN_MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!isFinishing()) {
                    tvSkip.setText(getString(R.string.skip_wait, (millisUntilFinished / 1000)));
                }
            }

            @Override
            public void onFinish() {
                tvSkip.setText(R.string.jumping);
                checkAccountLogin();
            }
        };
        countDownTimer.start();
    }

    @OnClick({R.id.tv_skip})
    public void onSkipClick(View view) {
        // requestForPermission();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        checkAccountLogin();
    }

    private void checkAccountLogin() {
        Router.build("Home").go(this);
        finish();
    }

    @Override
    public void onDestroy() {
        if (null != countDownTimer) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
            initViews();
    }

}