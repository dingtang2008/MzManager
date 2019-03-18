package com.elvis.mzmanager.network;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.chenenyu.router.Router;
import com.elvis.mzmanager.App;
import com.elvis.mzmanager.entity.CardEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.idogfooding.backbone.RequestCode;
import com.idogfooding.backbone.network.ApiException;
import com.idogfooding.backbone.network.BaseCallback;
import com.idogfooding.backbone.network.BoneException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * ApiCallback
 * 接口的回调,包括token的添加,接口错误的解析
 *
 * @author elvis
 */
public class ApiCallback<T> extends BaseCallback<T> {

    public ApiCallback(Context context) {
        super(context);
    }

    public ApiCallback(Fragment fragment) {
        super(fragment);
    }

    public ApiCallback(Context context, boolean showDialog) {
        super(context, showDialog);
    }

    public ApiCallback(Fragment fragment, boolean showDialog) {
        super(fragment, showDialog);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 在header中加入token
        request.headers(Api.TOKEN_KEY, App.getInstance().getUserToken());
    }

    @Override
    protected void onApiError(Response<T> response, ApiException exception) {
        if (exception.isUnauthorized()) {
            // 先清空已经登录保存的用户信息
            App.getInstance().clearUserLogin();
            // 然后跳转到登录页面
            if (null != fragment) {
                Router.build("Login").requestCode(RequestCode.USER_LOGIN).go(fragment);
            } else if (null != context) {
                Router.build("Login").requestCode(RequestCode.USER_LOGIN).go(context);
            } else {
                super.onApiError(response, exception);
                Log.e("ApiCallBack", "未授权跳转失败,请传上下文参数");
            }
        } else {
            super.onApiError(response, exception);
        }
    }

    @Override
    protected Type getResultType() {
        return ApiHttpResult.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T convertResultType(JsonReader jsonReader, Type typeArgument, Type type) {


        ApiHttpResult httpResult = new Gson().fromJson(jsonReader, Void.class == typeArgument ? ApiHttpResult.class : type);
        if (null == httpResult) {
            throw new IllegalArgumentException("数据解析错误");
        } else {
            if (httpResult.isSuccess()) {
                return (T) httpResult;
            } else {
                throw new ApiException(httpResult.getCode(), httpResult.getErrMsg());
            }
        }
    }

    @Override
    public void onCacheSuccess(Response<T> response) {
        if (response == null || response.body() == null) {
        } else {
            onSuccessData(response, response.body());
        }
    }

    @Override
    protected void onSuccessBodyData(Response<T> response, T data) {
        if (data instanceof ApiHttpResult) {
            if (((ApiHttpResult) data).getData() == null) {
                onSysError(response, BoneException.CODE_NO_BODY_DATA_EXCEPTION);
            } else {
                onSuccessData(response, data);
            }
        } else {
            onSysError(response, BoneException.CODE_ERROR_DATA_TYPE_EXCEPTION);
        }
    }

}
