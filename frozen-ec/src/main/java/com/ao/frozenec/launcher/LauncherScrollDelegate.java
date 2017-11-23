package com.ao.frozenec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ao.frozenec.R;
import com.ao.frozens.app.AccountManager;
import com.ao.frozens.app.IUserChecker;
import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.ui.launcher.Flags;
import com.ao.frozens.ui.launcher.ILauncherListener;
import com.ao.frozens.ui.launcher.LauncherHolderCreator;
import com.ao.frozens.ui.launcher.OnLauncherFinishTag;
import com.ao.frozens.utils.storage.FrozenPreference;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * com.ao.frozenec.launcher
 * <p>启动（轮播图）页面
 * Created by Leo on 2017/11/22.
 */

public class LauncherScrollDelegate extends FrozenDelegate implements OnItemClickListener {

    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ConvenientBanner<Integer> mConvenientBanner;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focusl})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }

    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {

            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {

        if (position == INTEGERS.size() - 1) {
            FrozenPreference.setAppFlag(Flags.HAS_FIRST_LAUNCH_APP.name(), true);
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

}
