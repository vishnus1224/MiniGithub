package com.vishnus1224.minigithub.generator;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vishnu on 2/6/2016.
 */
public final class WebServiceGenerator {

    //using generate service to get a new web service implementation.
    private WebServiceGenerator(){

    }

    //Creates a web service using retrofit with the input class name and base url.
    public static <T> T createWebService(Class<T> webServiceClass, String baseUrl){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(webServiceClass);
    }

}
