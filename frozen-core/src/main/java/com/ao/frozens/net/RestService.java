package com.ao.frozens.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * com.ao.frozens.net
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url , @QueryMap Map<String ,Object> parmas);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url , @FieldMap Map<String ,Object> parmas);

    @POST
    Call<String> postRow(@Url String url , @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url , @FieldMap Map<String ,Object> parmas);

    @PUT
    Call<String> putRow(@Url String url , @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url , @QueryMap Map<String ,Object> parmas);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url , @QueryMap Map<String,Object> parmas);

    @Multipart
    @POST
    Call<String> upload(@Url String url , @Part MultipartBody.Part file);



}
