package com.ao.frozen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ao.frozens.delegates.FrozenDelegate;
import com.ao.frozens.net.RestClient;
import com.ao.frozens.net.callback.IError;
import com.ao.frozens.net.callback.IFailure;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;

/**
 * com.ao.frozen
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class ExampleDelegate extends FrozenDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRequsetClient();
    }

    private void testRequsetClient() {

        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .onRequset(new IRequest() {
                    @Override
                    public void onRequsetStart() {

                    }

                    @Override
                    public void onRequsetEnd() {

                    }
                })
                .build()
                .get();
    }
}
