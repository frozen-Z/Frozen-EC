package com.ao.frozens.net;

import android.content.Context;

import com.ao.frozens.net.callback.IError;
import com.ao.frozens.net.callback.IFailure;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;
import com.ao.frozens.net.callback.RequsetCallBack;
import com.ao.frozens.net.download.DownloadHandler;
import com.ao.frozens.ui.loader.FrozenLolder;
import com.ao.frozens.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * com.ao.frozens.net
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARMAS = RestCreator.getParmas();
    private final IRequest REQUSET;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;



    public RestClient(String url, WeakHashMap<String, Object> parmas,
                      IRequest request, IFailure failure, ISuccess success,
                      IError error, RequestBody body, LoaderStyle style,
                      Context context,File file,String downloadDir ,
                      String extension ,String name) {
        this.URL = url;
        PARMAS.putAll(parmas);
        this.REQUSET = request;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
        this.FILE = file;
        this.NAME = name;
        this.EXTENSION = extension;
        this.DOWNLOAD_DIR = downloadDir;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void requset(HttpMethod method) {
        final RestService restService = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUSET != null) {
            REQUSET.onRequsetStart();
        }
        if (LOADER_STYLE != null) {
            FrozenLolder.showLolding(CONTEXT, LOADER_STYLE.name());
        }
        switch (method) {
            case GET:
                call = restService.get(URL, PARMAS);
                break;
            case POST:
                call = restService.post(URL, PARMAS);
                break;
            case PUT:
                call = restService.put(URL, PARMAS);
                break;
            case POST_RAW:
                call = restService.postRow(URL, BODY);
                break;
            case PUT_RAW:
                call = restService.putRow(URL, BODY);
                break;
            case DELETE:
                call = restService.delete(URL, PARMAS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = restService.upload(URL,body);
            default:
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }


    }

    private Callback<String> getRequestCallBack() {
        return new RequsetCallBack(REQUSET, FAILURE, SUCCESS, ERROR, LOADER_STYLE);
    }

    public final void get() {
        requset(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null){
            requset(HttpMethod.POST);
        }else {
            if (!PARMAS.isEmpty()){
                throw new RuntimeException("parmas must be null");
            }
            requset(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null){
            requset(HttpMethod.PUT);
        }else {
            if (!PARMAS.isEmpty()){
                throw new RuntimeException("parmas must be null");
            }
            requset(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        requset(HttpMethod.DELETE);
    }
    public final void download(){
        new DownloadHandler(URL,REQUSET,FAILURE,SUCCESS,ERROR,DOWNLOAD_DIR,EXTENSION,NAME).handle();
    }
}
