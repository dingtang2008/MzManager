package com.elvis.mzmanager;

import android.text.TextUtils;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.elvis.mzmanager.network.Api;
import com.elvis.mzmanager.user.LoginResult;
import com.elvis.mzmanager.user.User;
import com.idogfooding.backbone.BaseApplication;
import com.idogfooding.backbone.utils.GsonUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * App
 * base app class
 *
 * @author elvis
 */
public class App extends BaseApplication {

    public static final boolean debug = BuildConfig.DEBUG;
    public static final boolean FORCE_UPDATE_DB = debug && false;
    private static App instance;

    /**
     * support a method to get a instance for the outside
     */
    public synchronized static App getInstance() {
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // init okgo
        initOkHttp();
        // init dialog
        initDialog();
        initCrash(false);
    }

    /**
     * 初始化网络日志
     */
    protected void initOkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))
                .build();

        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(okHttpClient);
    }


    // [+] Account
    private String token;
    private User user;
    private String api;
    private String cityName = "宁波";
    private String cityCode = "";

    /**
     * 用户是否登录
     */
    public boolean isUserLogin() {
        return !TextUtils.isEmpty(getUserToken()) && null != getLoginUser() ;
    }



    /**
     * get user account token
     */
    public User getLoginUser() {
        if (null == user) {
            user = GsonUtils.fromJson(getSPInstance().getString("login_user"), User.class);
        }
        return user;
    }

    public String getUserToken() {
        if (TextUtils.isEmpty(token)) {
            token = getSPInstance().getString("login_token");
        }
        if (token == null) {
            token = "";
        }
        return token;
    }

    public void clearUserLogin() {
        // 清空登录信息
        getSPInstance().remove("login_token");
        getSPInstance().remove("login_user");
        token = null;
        user = null;
    }


    public void clearToken(){
        getSPInstance().remove("login_token");
        token = null;
    }
    public void saveUser(User user) {
        this.user = user;
        getSPInstance().put("login_user", user.toJson());
    }

    public boolean saveUserLogin(LoginResult result) {
        if (null == result || StringUtils.isEmpty(result.getToken()) ) {
            ToastUtils.showShort(R.string.msg_data_empty);
            return false;
        }
        token = result.getToken();
        user = result.getUser_info();
        getSPInstance().put("login_user", user.toJson());
        getSPInstance().put("login_token", token);
        return true;
    }

    public boolean saveApi(String api) {
        if (StringUtils.isEmpty(api)) {
            return false;
        }
        this.api = api;
        getSPInstance().put("api", api);
        return true;
    }


    /**
     * 获取服务器地址
     */
    public String getApi() {
        if (StringUtils.isTrimEmpty(api)) {
            api = getSPInstance().getString("api", Api.SERVER_API);
        }
        return api;
    }

    /**
     * 加入忽略版本
     */
    public void setIgnoreVersionCode(int versionCode) {
        getSPInstance().put("settings_ignore_version_code", versionCode);
    }

    public int getIgnoreVersionCode() {
        return getSPInstance().getInt("settings_ignore_version_code", 0);
    }

    /**
     * 消息推送配置
     */
    public void setSettingsPush(boolean enable) {
        getSPInstance().put("settings_push", enable);
    }

    public boolean getSettingsPush() {
        return getSPInstance().getBoolean("settings_push", true);
    }
    // [-]

}
