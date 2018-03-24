package ru.trubin23.listofcompanies.data.source;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 22.03.2018.
 */

public class LocalRepository implements CompaniesDataSource {

    private static LocalRepository INSTANCE;

    private CompaniesDao mCompaniesDao;

    private static final Executor mDiskIO = Executors.newSingleThreadExecutor();

    private Handler mMainThreadHandler;

    private LocalRepository(@NonNull CompaniesDao companiesDao) {
        mCompaniesDao = companiesDao;

        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public static LocalRepository getInstance(@NonNull CompaniesDao companiesDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(companiesDao);
        }
        return INSTANCE;
    }

    @Override
    public void getCompanies(@NonNull LoadCompaniesCallback callback) {
        mDiskIO.execute(() -> {
            List<Company> companies = mCompaniesDao.getCompanies();
            if (companies.isEmpty()) {
                mMainThreadHandler.post(callback::onDataNotAvailable);
            } else {
                mMainThreadHandler.post(() -> callback.onCompaniesLoaded(companies));
            }
        });
    }

    static void ioThread(Runnable runnable){
        mDiskIO.execute(runnable);
    }
}
