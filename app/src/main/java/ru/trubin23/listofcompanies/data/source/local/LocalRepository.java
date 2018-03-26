package ru.trubin23.listofcompanies.data.source.local;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.trubin23.listofcompanies.data.Company;
import ru.trubin23.listofcompanies.data.source.LoadCompaniesCallback;

/**
 * Created by Andrey on 22.03.2018.
 */

public class LocalRepository {

    private static LocalRepository INSTANCE;

    private CompaniesDao mCompaniesDao;

    private List<Company> mInitialCompanies;

    private Executor mDiskIO;

    private Handler mMainThreadHandler;

    private LocalRepository(@NonNull CompaniesDao companiesDao,
                            @NonNull List<Company> initialCompanies) {
        mCompaniesDao = companiesDao;

        mInitialCompanies = initialCompanies;

        mDiskIO = Executors.newSingleThreadExecutor();

        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public static LocalRepository getInstance(@NonNull CompaniesDao companiesDao,
                                              @NonNull List<Company> initialCompanies) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(companiesDao, initialCompanies);
        }
        return INSTANCE;
    }

    public void getCompanies(@NonNull LoadCompaniesCallback callback) {
        mDiskIO.execute(() -> {
            List<Company> companies = mCompaniesDao.getCompanies();
            if (companies.isEmpty()) {
                mCompaniesDao.insertCompanies(mInitialCompanies);
            }

            List<Company> initCompanies = mCompaniesDao.getCompanies();
            if (initCompanies.isEmpty()) {
                mMainThreadHandler.post(callback::onDataNotAvailable);
            } else {
                mMainThreadHandler.post(() -> callback.onCompaniesLoaded(initCompanies));
            }
        });
    }

    public void insertCompanies(@NonNull List<Company> companies){
        mDiskIO.execute(() -> mCompaniesDao.insertCompanies(companies));
    }
}
