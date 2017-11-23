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
 * <p>
 * <p>
 * Created by Leo on 2017/11/22.
 */

public class SignUpDelegate extends FrozenDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;

    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;

    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;

    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;

    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;

    @OnClick(R2.id.tv_link_sign_in)
    public void onClickLink() {
        start(new SignInDelegate());
    }

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @OnClick(R2.id.btn_sign_up)
    public void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.77.101:8080/RestServer/data/user_profile.json")
                    .parmas("name", mName.getText().toString())
                    .parmas("email", mEmail.getText().toString())
                    .parmas("phone", mPhone.getText().toString())
                    .parmas("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                            FrozenLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }

    private boolean checkForm() {

        boolean pass = true;

        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String rePassword = mRePassword.getText().toString();

        if (name.isEmpty()) {
            pass = false;
            mName.setError("请输入姓名");
        } else {
            mName.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            pass = false;
            mPhone.setError("请输入正确的手机号码");
        } else {
            mPhone.setError(null);
        }

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

        if (rePassword.isEmpty() || !rePassword.equals(password)) {
            pass = false;
            mRePassword.setError("密码输入不一致");
        } else {
            mRePassword.setError(null);
        }
        return pass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
