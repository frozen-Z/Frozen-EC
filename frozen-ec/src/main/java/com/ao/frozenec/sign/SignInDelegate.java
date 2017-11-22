package com.ao.frozenec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ao.frozenec.R;
import com.ao.frozenec.R2;
import com.ao.frozens.delegates.FrozenDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * com.ao.frozenec.sign
 * <p>
 * <p>
 * Created by Leo on 2017/11/22.
 */

public class SignInDelegate extends FrozenDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.tv_link_sign_up)
    public void onClickLink(){
        start(new SignUpDelegate());
    }

    @OnClick(R2.id.btn_sign_in)
    public void onClickSignIn() {
        if (checkForm()) {
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
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
