package ru.trubin23.listofcompanies.data.source;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 22.03.2018.
 */

public class LocalRepository {

    private static LocalRepository INSTANCE;
    private CompaniesDao mCompaniesDao;

    private LocalRepository(@NonNull CompaniesDao companiesDao) {
        mCompaniesDao = companiesDao;
    }

    public static LocalRepository getInstance(@NonNull CompaniesDao companiesDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(companiesDao);
        }
        return INSTANCE;
    }
}
