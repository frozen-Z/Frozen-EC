package com.ao.frozens.net.interceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import com.ao.frozens.utils.FileUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * com.ao.frozens.net.interceptors
 * <p>
 * <p>
 * Created by Leo on 2017/11/22.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;

    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }

    private Response getResponse(Chain chain ,String json){
        return  new Response.Builder()
                .code(200)
                .addHeader("Content_Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }


    private Response dubugResponse(Chain chain, @RawRes int rawId){

        final String json = FileUtils.getRawFile(rawId);
        return getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        Log.e("--main--", "DebugInterceptor 类的 intercept 方法 调用");
        if (url.contains(DEBUG_URL)){

            return dubugResponse(chain,DEBUG_RAW_ID);
        }else {
            return chain.proceed(chain.request());
        }
    }
}
