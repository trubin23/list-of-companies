package ru.trubin23.listofcompanies.data.source;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 22.03.2018.
 */

public class LocalRepository implements CompaniesDataSource {

    private static LocalRepository INSTANCE;

    private CompaniesDao mCompaniesDao;

    private static final Executor mDiskIO = Executors.newSingleThreadExecutor();

    private LocalRepository(@NonNull CompaniesDao companiesDao) {
        mCompaniesDao = companiesDao;
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
                callback.onDataNotAvailable();
            } else {
                callback.onCompaniesLoaded(companies);
            }
        });
    }

    @Override
    public void getCompany(@NonNull String id, @NonNull GetCompanyCallback callback) {
        mDiskIO.execute(() -> {
            Company task = mCompaniesDao.getCompanyById(id);
            if (task == null) {
                callback.onDataNotAvailable();
            } else {
                callback.onCompanyLoaded(task);
            }
        });
    }

    static void ioThread(Runnable runnable){
        mDiskIO.execute(runnable);
    }
}
