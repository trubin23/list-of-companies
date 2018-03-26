package ru.trubin23.listofcompanies.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;
import ru.trubin23.listofcompanies.data.source.local.LocalRepository;
import ru.trubin23.listofcompanies.data.source.remote.RemoteRepository;

/**
 * Created by Andrey on 26.03.2018.
 */

public class Repository {

    private static Repository INSTANCE;

    private RemoteRepository mRemoteRepository;
    private LocalRepository mLocalRepository;

    private Repository(@NonNull RemoteRepository remoteRepository,
                       @NonNull LocalRepository localRepository) {
        mRemoteRepository = remoteRepository;
        mLocalRepository = localRepository;
    }

    public static Repository getInstance(@NonNull RemoteRepository remoteRepository,
                                         @NonNull LocalRepository localRepository) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteRepository, localRepository);
        }
        return INSTANCE;
    }


    public void getCompanies(boolean forceUpdate,
                             @NonNull LoadCompaniesCallback loadCompaniesCallback) {
        if (forceUpdate) {
            getRemoteCompanies(loadCompaniesCallback);
        } else {
            mLocalRepository.getCompanies(loadCompaniesCallback);
        }
    }

    private void getRemoteCompanies(@NonNull LoadCompaniesCallback loadCompaniesCallback) {
        mRemoteRepository.getCompanies(new LoadCompaniesCallback() {
            @Override
            public void onCompaniesLoaded(@NonNull List<Company> companies) {
                mLocalRepository.insertCompanies(companies);
                loadCompaniesCallback.onCompaniesLoaded(companies);
            }

            @Override
            public void onDataNotAvailable() {
                mLocalRepository.getCompanies(loadCompaniesCallback);
            }
        });
    }
}
