package com.fab.currencycalculator.dependency_injection;


import com.fab.currencycalculator.data.CurrencyApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String baseUrl = "https://api.binance.com/api/v3/";
    public static final int TIMEOUT = 30;

    @Singleton
    @Provides
    Retrofit provideRetrofit (OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient (HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideLoggin () {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    CurrencyApi auth (Retrofit retrofit) {
        return retrofit.create(CurrencyApi.class);
    }


}
