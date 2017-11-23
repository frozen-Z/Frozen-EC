package com.ao.frozens.app;

/**
 * com.ao.frozens.app
 * <p>判断用户是否登录回调接口
 * Created by Leo on 2017/11/23.
 */

public interface IUserChecker {

    void onSignIn();
    void notSignIn();
}
