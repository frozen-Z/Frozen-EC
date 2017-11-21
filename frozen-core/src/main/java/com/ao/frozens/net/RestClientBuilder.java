package com.ao.frozens.net;

import android.content.Context;

import com.ao.frozens.net.callback.IError;
import com.ao.frozens.net.callback.IFailure;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;
import com.ao.frozens.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * com.ao.frozens.net
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARMAS = RestCreator.getParmas();
    private IRequest mIRequest;
    private IFailure mIFailure;
    private ISuccess mISuccess;
    private IError mIError;
    private String mDownloadDir;
    private String mName;
    private String mExtension;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mStyle;
    private File mFile;

    RestClientBuilder() {
    }


    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder parmas(WeakHashMap<String, Object> parmas) {
        PARMAS.putAll(parmas);
        return this;
    }

    public final RestClientBuilder parmas(String key, Object value) {
        PARMAS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset-utf-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequset(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder file(String path) {
        mFile = new File(path);
        return this;
    }

    public final RestClientBuilder file(File file) {
        mFile = file;
        return this;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style) {

        this.mContext = context;
        this.mStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder downloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARMAS, mIRequest, mIFailure, mISuccess, mIError, mBody, mStyle, mContext, mFile, mDownloadDir, mExtension, mName);

    }

}
