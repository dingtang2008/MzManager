package com.elvis.mzmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.elvis.mzmanager.adapter.CardListAdapter;
import com.elvis.mzmanager.entity.CardEntity;
import com.elvis.mzmanager.entity.CardEntity.RowsBean;
import com.elvis.mzmanager.network.Api;
import com.elvis.mzmanager.network.ApiCallback;
import com.elvis.mzmanager.network.ApiHttpResult;
import com.idogfooding.backbone.RequestCode;
import com.idogfooding.backbone.network.ApiException;
import com.idogfooding.backbone.ui.rv.RVFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;


import static com.elvis.mzmanager.network.Api.RESPONSE_EXPIRED;


@Route({"CardListFragment"})
public class CardListFragment extends RVFragment<RowsBean, CardListAdapter> {

    @Override
    protected void onConfig(Bundle savedInstanceState) {
        super.onConfig(savedInstanceState);
        showToolbar = false;
        Router.injectParams(this);
    }

    @Override
    protected void onSetup(View view, Bundle savedInstanceState) {
        super.onSetup(view, savedInstanceState);
        loadData(true, false);
    }

    @Override
    protected void createAdapter() {
        mAdapter = new CardListAdapter();
    }

    @Override
    protected void cfgAdapter() {
        setLoadMore(false);
        super.cfgAdapter();
        mAdapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
            //todo;
        });
    }


    protected void loadData() {
        //mSwipeRefreshLayout.setRefreshing(false);
//pageSize=100&pageNo=1
        OkGo.<ApiHttpResult<CardEntity>>get(Api.GET_CARDLIST)
                .params("pageSize", 100)
                .params("pageNo", 1)
                .tag(this)
                .execute(new ApiCallback<ApiHttpResult<CardEntity>>(this, true) {
                    @Override
                    public void onSuccessData(Response<ApiHttpResult<CardEntity>> response, ApiHttpResult<CardEntity> data) {

                        mAdapter.setNewData(data.getData().getRows());
                        mAdapter.notifyDataSetChanged();

//                        if (data.getCode() == 0) {
//                            if (data.getData().isEmpty()) {
//                                mAdapter.setEmptyView(getEmptyView());
//                            } else {
//                                mAdapter.setNewData(data.getData());
//                                mAdapter.notifyDataSetChanged();
//                            }
//
//
//                        } else {
//                            mAdapter.setEmptyView(getErrorView());
//                            onLoadApiException(new ApiException(data.getErr_code(), data.getErr_msg()));
//                        }

                    }

                    @Override
                    protected void onApiError
                            (Response<ApiHttpResult<CardEntity>> response,
                             ApiException exception) {
                        if (exception.getCode() == RESPONSE_EXPIRED) {
                            App.getInstance().clearToken();
                            String username = null;
                            String password = null;
                            if (App.getInstance().getLoginUser() != null) {
                                username = App.getInstance().getLoginUser().getUsername();
                                password = App.getInstance().getLoginUser().getPassword();
                            }

                            // 然后跳转到登录页面
                            if (null != fragment) {
                                Router.build("Login")
                                        .with("username", username)
                                        .with("pwd", password)
                                        .requestCode(RequestCode.USER_LOGIN).go(fragment);

                            } else if (null != context) {
                                Router.build("Login")
                                        .requestCode(RequestCode.USER_LOGIN)
                                        .with("username", username)
                                        .with("pwd", password).go(context);
                            } else {
                                super.onApiError(response, exception);
                                Log.e("ApiCallBack", "未授权跳转失败,请传上下文参数");
                            }
                            finishActivity();
                        }
                    }
                });

    }

    @Override
    protected void loadData(boolean refresh, boolean loadMore) {
        super.loadData(refresh, loadMore);
        loadData();
    }


}
