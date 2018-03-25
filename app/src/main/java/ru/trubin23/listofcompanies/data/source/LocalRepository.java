package ru.trubin23.listofcompanies.data.source;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
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

    private Executor mDiskIO;

    private Handler mMainThreadHandler;

    private LocalRepository(@NonNull CompaniesDao companiesDao) {
        mCompaniesDao = companiesDao;

        mDiskIO = Executors.newSingleThreadExecutor();

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
                mCompaniesDao.insertCompanies(prePopulateData());
            }

            List<Company> initCompanies = mCompaniesDao.getCompanies();
            if (initCompanies.isEmpty()) {
                mMainThreadHandler.post(callback::onDataNotAvailable);
            } else {
                mMainThreadHandler.post(() -> callback.onCompaniesLoaded(initCompanies));
            }
        });
    }

    @NonNull
    private List<Company> prePopulateData(){
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Тестовое имя #1", "ул. первая, д.1", "123456789001"));
        companies.add(new Company("Тестовое имя #2", "ул. вторая, д.2", "123456789002"));
        companies.add(new Company("Тестовое имя #3", "ул. третья, д.3", "123456789003"));
        companies.add(new Company("Тестовое имя #4", "ул. четвертая, д.4", "123456789004"));
        companies.add(new Company("Тестовое имя #5", "ул. пятая, д.5", "123456789005"));
        return companies;
    }
}
