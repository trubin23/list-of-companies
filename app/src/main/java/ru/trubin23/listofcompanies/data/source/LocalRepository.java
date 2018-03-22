package ru.trubin23.listofcompanies.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;

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

    @NonNull
    public List<Company> getCompanies(){
        return mCompaniesDao.getCompanies();
    }

    @NonNull
    public Company getCompany(@NonNull String companyId){
        return mCompaniesDao.getCompany(companyId);
    }

    public void insertCompanies(@NonNull List<Company> companies){
        mCompaniesDao.insertCompanies(companies);
    }
}
