package ru.trubin23.listofcompanies.data.source.remote;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andrey on 26.03.2018.
 */

class ProcessingResponse<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            T body = response.body();
            if (body != null) {
                responseBody(body);
            } else {
                dataNotAvailable();
            }
        } else {
            dataNotAvailable();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dataNotAvailable();
    }

    public void responseBody(@NonNull T body) {

    }

    public void dataNotAvailable() {

    }
}