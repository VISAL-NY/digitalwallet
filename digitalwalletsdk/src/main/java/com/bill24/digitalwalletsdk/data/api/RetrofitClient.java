package com.bill24.digitalwalletsdk.data.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://1f14adeb-5ea2-4646-897c-4b3a6581c657.mock.pstmn.io/";
    private static RetrofitClient instance=null;
    private ApiClient apiClient;





//    private RetrofitClient(){
//        // Create an OkHttpClient with a logging interceptor
//        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(loggingInterceptor);
//
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(BaseUrl.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build()).build();
//        apiClient=retrofit.create(ApiClient.class);
//    }
//
//    public static RetrofitClient getInstance(){
//        if(instance==null){
//            instance=new RetrofitClient();
//        }
//        return instance;
//    }
//
//    public ApiClient getApiClient(){
//        return apiClient;
//    }


    private  RetrofitClient(){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient=retrofit.create(ApiClient.class);
    }

    public static RetrofitClient getInstance() {
        if(instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }

    public ApiClient apiClient(){
        return apiClient;
    }
}