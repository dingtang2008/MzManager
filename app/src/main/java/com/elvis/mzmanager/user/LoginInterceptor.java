package com.elvis.mzmanager.user;

import com.chenenyu.router.IRouter;
import com.chenenyu.router.RouteResponse;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Interceptor;
import com.elvis.mzmanager.App;
import com.elvis.mzmanager.network.Api;
import com.idogfooding.backbone.RequestCode;
import com.idogfooding.backbone.route.BaseRouteInterceptor;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * LoginInterceptor
 *
 * @author elvis
 */
@Interceptor("Login")
public class LoginInterceptor extends BaseRouteInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        if (App.getInstance().isUserLogin()) {
            return chain.process();
        } else {
            // 未登录
            IRouter iRouter = Router.build("Login")
                    .with("routeRequest", chain.getRequest())
                    .requestCode(RequestCode.USER_LOGIN);
            routerGo(iRouter, chain);
            return chain.intercept();
        }
    }




}
