package com.ao.frozens.net.download;

import com.ao.frozens.net.RestCreator;
import com.ao.frozens.net.callback.IError;
import com.ao.frozens.net.callback.IFailure;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * com.ao.frozens.net.download
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARMAS = RestCreator.getParmas();
    private final IRequest REQUSET;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           IRequest request,
                           IFailure failure,
                           ISuccess success,
                           IError error,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUSET = request;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handle() {

        if (REQUSET != null){
            REQUSET.onRequsetStart();
        }

        RestCreator.getRestService().download(URL,PARMAS)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        final SaveFileTask saveFileTask = new SaveFileTask(REQUSET,SUCCESS);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


    }
}
