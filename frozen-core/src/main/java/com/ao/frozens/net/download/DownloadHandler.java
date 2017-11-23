package com.ao.frozens.net.download;

import android.os.AsyncTask;

import com.ao.frozens.net.RestCreator;
import com.ao.frozens.net.callback.IError;
import com.ao.frozens.net.callback.IFailure;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * com.ao.frozens.net.download
 * <p>下载处理类
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

        if (REQUSET != null) {
            REQUSET.onRequsetStart();
        }

        RestCreator.getRestService().download(URL, PARMAS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final SaveFileTask saveFileTask = new SaveFileTask(REQUSET, SUCCESS);
                            final ResponseBody body = response.body();
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, body, NAME);

                            if (saveFileTask.isCancelled()) {
                                if (REQUSET != null) {
                                    REQUSET.onRequsetEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });


    }
}
