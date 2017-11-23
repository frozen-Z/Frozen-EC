package com.ao.frozens.net.callback;

/**
 * com.ao.frozens.net.callback
 * <p>网络请求错误回调
 * Created by Leo on 2017/11/21.
 */

public interface IError {
    void onError(int code , String msg);
}
