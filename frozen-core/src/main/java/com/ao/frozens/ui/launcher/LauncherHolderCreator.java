package com.ao.frozens.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * com.ao.frozens.ui.launcher
 * <p>启动页面轮播图第三方辅助类
 * Created by Leo on 2017/11/22.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
