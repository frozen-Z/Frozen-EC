package com.ao.frozens.app;

import com.ao.frozens.utils.storage.FrozenPreference;

/**
 * com.ao.frozens.app
 * <p>用户信息管理类
 * Created by Leo on 2017/11/23.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    public static void setSignState(boolean state) {
        FrozenPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean getSignState() {
        return FrozenPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker iUserChecker) {
        if (getSignState()) {
            iUserChecker.onSignIn();
        } else {
            iUserChecker.notSignIn();
        }
    }

}
