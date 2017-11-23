package com.ao.frozen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.ao.frozenec.launcher.LauncherDelegate;
import com.ao.frozenec.sign.ISignListener;
import com.ao.frozens.activitys.ProxyActivity;
import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.ui.launcher.ILauncherListener;
import com.ao.frozens.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener , ILauncherListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public FrozenDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功 ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功 ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLanuncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                break;
            case NOT_SIGNED:
                break;
            default:
                break;
        }
    }
}
