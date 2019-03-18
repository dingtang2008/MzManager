package com.elvis.mzmanager.user;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.elvis.mzmanager.App;
import com.elvis.mzmanager.R;
import com.elvis.mzmanager.network.Api;
import com.idogfooding.backbone.ui.BaseActivity;
import com.kongzue.dialog.v2.TipDialog;
import com.kongzue.dialog.v2.WaitDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import butterknife.BindView;

/**
 * 登录
 *
 * @author elvis
 */
@Route({"Login"})
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @InjectParam
    String username;
    @InjectParam
    String pwd;

    @Override
    protected int getLayoutId() {
        return R.layout.login;
    }

    @Override
    protected void onSetup(Bundle savedInstanceState) {
        super.onSetup(savedInstanceState);

        initViews();
    }


    private void initViews() {
        // init editor action
        etUsername.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                etPwd.requestFocus();
            }
            return true;
        });

        etPwd.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptLogin(textView);
                return true;
            }
            return false;
        });
        if (username != null) {
            etUsername.setText(username);
        }
        if (pwd != null) {
            etPwd.setText(pwd);
        }
    }


    /**
     * 登录
     *
     * @param v
     */
    public void attemptLogin(View v) {
        String username = etUsername.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        if (!RegexUtils.isMobileExact(username)) {
            etUsername.requestFocus();
            showTipDialog(getString(R.string.msg_phone_format_error));
            return;
        }

        if (StringUtils.isEmpty(pwd)) {
            etPwd.requestFocus();
            showTipDialog("密码不能为空");
            return;
        }
        realLogin(username, pwd);

    }

    public void realLogin(String username, String pwd) {
        HttpParams params = new HttpParams();
        params.put("username", username);
        params.put("password", pwd);


        OkGo.<String>post(Api.USER_LOGIN)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        WaitDialog.show(LoginActivity.this, getString(R.string.msg_loading));
                    }

                    @Override
                    public void onSuccess(Response<String> response) {


                        //error code
                        //Response{protocol=http/1.1, code=200, message=, url=http://sim.519liuliang.com/pages/login-newadmin.html?loginabnormal=1}
                        //Response{protocol=http/1.1, code=200, message=, url=http://sim.519liuliang.com/pages/login-newadmin.html?loginabnormal=2}

                        //ok
                        //Response{protocol=http/1.1, code=200, message=, url=http://sim.519liuliang.com/pages/agentIndex.html;jsessionid=42E11F36239CB1DC152571C28BF187D4}
                        String responseString = response.getRawResponse().toString();

                        String result = responseString.substring(responseString.lastIndexOf("=") + 1, responseString.length() - 1);

                        if ("1".equals(result)) {
                            //username error
                            showMessageDialog("账号错误");
                        } else if ("2".equals(result)) {
                            //password error
                            showMessageDialog("密码错误");
                        } else if (result.length() > 10) {
                            //login success
                            LoginResult loginResult = new LoginResult();
                            loginResult.setToken(result);

                            User mUser = new User();
                            mUser.setUsername(username);
                            mUser.setPassword(pwd);
                            loginResult.setUser_info(mUser);
                            saveLoginResult(loginResult);


                        }else {
                            // already login and not expired


                            Router.build("Home").go(LoginActivity.this);
                            finish();

                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        showMessageDialog(response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        WaitDialog.dismiss();

                    }
                });


    }

    /**
     * 登录成功，处理登录结果
     */
    private void saveLoginResult(final LoginResult result) {
        boolean saveUserLogin = App.getInstance().saveUserLogin(result);
        if (saveUserLogin) {
            showTipDialog(getString(R.string.msg_login_success), TipDialog.TYPE_FINISH);
            Router.build("Home").go(this);
            finish();
        }
    }


}
