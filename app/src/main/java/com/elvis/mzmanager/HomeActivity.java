package com.elvis.mzmanager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.idogfooding.backbone.ui.BaseActivity;

/**
 * Home
 *
 * @author elvis
 */
@Route(value = {"Home"}, interceptors = {"Login"})
public class HomeActivity extends BaseActivity {

    @Override
    protected void onSetup(Bundle savedInstanceState) {
        super.onSetup(savedInstanceState);

        if (NetworkUtils.isConnected()) {
            //todo
        } else {
            ToastUtils.showShort(R.string.msg_network_unavailable);
        }
    }

    @Override
    protected void onConfig(Bundle savedInstanceState) {
        return;
    }

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("卡片列表");

        replaceFragment((Fragment) Router.build("CardListFragment").getFragment(this));
    }


}
