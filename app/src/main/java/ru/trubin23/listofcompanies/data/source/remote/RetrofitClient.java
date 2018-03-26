package ru.trubin23.listofcompanies.data.source.remote;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 26.03.2018.
 */

class RetrofitClient {

    private static final String BASE_URL = "https://trubin23.ru";

    private static RemoteService sRemoteService;

    @NonNull
    private static RemoteService getRemoteService() {
        if (sRemoteService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sRemoteService = retrofit.create(RemoteService.class);
        }
        return sRemoteService;
    }

    static void getCompanies(@NonNull Callback<List<Company>> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.getCompanies().enqueue(callback);
    }
}
