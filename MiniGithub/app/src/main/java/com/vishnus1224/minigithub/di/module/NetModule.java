package com.vishnus1224.minigithub.di.module;

import com.vishnus1224.minigithub.webservice.RestApi;
import com.vishnus1224.minigithub.webservice.RestApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Provides dependencies for making web service requests.
 * Created by Vishnu on 4/22/2016.
 */
@Module
public class NetModule {

    /**
     * URL for the web service endpoint.
     */
    private String baseURL;

    public NetModule(String baseURL){
        this.baseURL = baseURL;
    }

    /**
     * Build and provide a single OkHttpClient instance throughout the application.
     * @return OkHttpClient instance.
     */
    @Provides @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }

    /**
     * Provide converter factory singleton instance.
     * @return GsonConverterFactory instance.
     */
    @Provides @Singleton
    Converter.Factory provideConvertFactory(){
        return GsonConverterFactory.create();
    }

    /**
     * Provide an instance of Retrofit for interacting with the web service.
     * @param okHttpClient OkHttpClient instance.
     * @param converterFactory Converter Factory instance.
     * @return Single retrofit instance pointing to baseURL.
     */
    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory){
        return new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
    }

    /**
     * Provide an instance of the REST API.
     * @param retrofit Retrofit instance.
     * @return Rest Api Implementation.
     */
    @Provides @Singleton
    RestApi provideRestAPI(Retrofit retrofit){
        return new RestApiImpl(retrofit);
    }

}
