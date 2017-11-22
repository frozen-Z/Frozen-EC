package com.ao.frozens.net.callback;

import android.os.Handler;

import com.ao.frozens.ui.loader.FrozenLolder;
import com.ao.frozens.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * com.ao.frozens.net.callback
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class RequsetCallBack implements Callback<String> {

    private final IRequest REQUSET;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static Handler HANDLER = new Handler();

    public RequsetCallBack(IRequest request, IFailure failure, ISuccess success, IError error,LoaderStyle loaderStyle) {

        this.REQUSET = request;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }

        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUSET != null) {
            REQUSET.onRequsetEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FrozenLolder.stopLolding();
                }
            },1000);
        }
    }
}
