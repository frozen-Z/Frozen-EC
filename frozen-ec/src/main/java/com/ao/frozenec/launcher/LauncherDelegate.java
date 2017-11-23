package com.ao.frozenec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ao.frozenec.R;
import com.ao.frozenec.R2;
import com.ao.frozens.app.AccountManager;
import com.ao.frozens.app.IUserChecker;
import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.timer.BaseTimerTask;
import com.ao.frozens.timer.ITimerListener;
import com.ao.frozens.ui.launcher.Flags;
import com.ao.frozens.ui.launcher.ILauncherListener;
import com.ao.frozens.ui.launcher.OnLauncherFinishTag;
import com.ao.frozens.utils.storage.FrozenPreference;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * com.ao.frozenec.lancher
 * <p>
 * <p>
 * Created by Leo on 2017/11/22.
 */

public class LauncherDelegate extends FrozenDelegate implements ITimerListener {

    private Timer mTimer;
    private int mCount = 5;

    @BindView(R2.id.tv_launchaer_timer)
    TextView mTimerView;

    @OnClick(R2.id.tv_launchaer_timer)
    public void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkShowScroll();
        }
    }

    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {

            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    private void checkShowScroll() {

        if (!FrozenPreference.getAppFlag(Flags.HAS_FIRST_LAUNCH_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    mILauncherListener.onLanuncherFinish(OnLauncherFinishTag.SIGNED);
                }

                @Override
                public void notSignIn() {
                    mILauncherListener.onLanuncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            });
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_lancher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTimer != null) {
                    mTimerView.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkShowScroll();
                        }
                    }
                }
            }
        });
    }
}
