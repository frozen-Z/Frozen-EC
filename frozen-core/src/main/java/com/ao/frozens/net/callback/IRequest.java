package com.ao.frozens.net.callback;

/**
 * com.ao.frozens.net.callback
 * <p>网络请求开始结束回调
 * Created by Leo on 2017/11/21.
 */

public interface IRequest {

    void onRequsetStart();
    void onRequsetEnd();
}
