package com.ao.frozens.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * com.ao.frozens.net.interceptors
 * <p>基础拦截器
 * Created by Leo on 2017/11/22.
 */

public abstract class BaseInterceptor implements Interceptor {


    protected LinkedHashMap<String, String> getUrlParmas(Chain chain) {
        final LinkedHashMap<String, String> urlParmas = new LinkedHashMap<>();
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        for (int i = 0; i < size; i++) {
            urlParmas.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return urlParmas;
    }

    protected String getUrlParmas(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParmas(Chain chain) {
        final LinkedHashMap<String, String> bodyParmas = new LinkedHashMap<>();
        final FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            bodyParmas.put(formBody.name(i), formBody.value(i));
        }
        return bodyParmas;
    }

    protected String getBodyParmas(Chain chain, String key) {
        return getBodyParmas(chain).get(key);
    }

}
