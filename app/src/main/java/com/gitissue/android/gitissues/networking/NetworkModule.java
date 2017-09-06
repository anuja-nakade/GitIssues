package com.gitissue.android.gitissues.networking;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Anuja on 9/6/17.
 */

@Module
public class NetworkModule {
    @Provides
    @Singleton
    Retrofit provideCall() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/repos/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }

}
