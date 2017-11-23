package com.ao.frozenec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ao.frozenec.R;
import com.ao.frozenec.R2;
import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.net.RestClient;
import com.ao.frozens.net.callback.ISuccess;
import com.ao.frozens.utils.log.FrozenLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * com.ao.frozenec.sign
 * <p>登录页面
 * Created by Leo on 2017/11/22.
 */

public class SignInDelegate extends FrozenDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.tv_link_sign_up)
    public void onClickLink() {
        start(new SignUpDelegate());
    }

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    public void onClickSignIn() {
        if (checkForm()) {
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    .url("http://192.168.77.101:8080/RestServer/data/user_profile.json")
                    .parmas("email", mEmail.getText().toString())
                    .parmas("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                            FrozenLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();

        }
    }

    private boolean checkForm() {

        boolean pass = true;
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            pass = false;
            mEmail.setError("请输入正确的邮箱");
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            pass = false;
            mPassword.setError("密码不能小于六位");
        } else {
            mPassword.setError(null);
        }

        return pass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
