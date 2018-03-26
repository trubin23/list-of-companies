package ru.trubin23.listofcompanies.data.source.remote;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.trubin23.listofcompanies.data.Company;
import ru.trubin23.listofcompanies.data.source.LoadCompaniesCallback;

/**
 * Created by Andrey on 26.03.2018.
 */

public class RemoteRepository {

    private static RemoteRepository INSTANCE;

    private static final int THREAD_COUNT = 3;

    private final Executor mNetworkIO;

    private RemoteRepository(@NonNull Executor networkIO) {
        mNetworkIO = networkIO;
    }

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteRepository(
                            Executors.newFixedThreadPool(THREAD_COUNT));
                }
            }
        }
        return INSTANCE;
    }

    public void getCompanies(@NonNull LoadCompaniesCallback callback) {
        mNetworkIO.execute(() ->
                RetrofitClient.getCompanies(new ProcessingResponse<List<Company>>() {
                    @Override
                    public void responseBody(@NonNull List<Company> body) {
                        callback.onCompaniesLoaded(body);
                    }

                    @Override
                    public void dataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                })
        );
    }
}
