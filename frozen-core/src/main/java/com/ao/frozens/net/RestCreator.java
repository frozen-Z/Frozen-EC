package com.ao.frozens.net;

import com.ao.frozens.app.ConfigTyoe;
import com.ao.frozens.app.Frozen;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * com.ao.frozens.net
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class RestCreator {

    public static final class ParmasHolder{
        public static final WeakHashMap<String , Object> PARMAS= new WeakHashMap<>();
    }

    public static WeakHashMap<String ,Object> getParmas(){
        return ParmasHolder.PARMAS;
    }


    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Frozen.getConfigurations().get(ConfigTyoe.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .build();
    }


    private static final class OkhttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHoledr {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }

    public static RestService getRestService(){
        return RestServiceHoledr.REST_SERVICE;
    }
}

