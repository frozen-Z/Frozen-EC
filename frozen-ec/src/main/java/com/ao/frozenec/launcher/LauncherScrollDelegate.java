package com.ao.frozenec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ao.frozenec.R;
import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.ui.launcher.LauncherHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * com.ao.frozenec.launcher
 * <p>
 * <p>
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

    }
}
