package ru.trubin23.listofcompanies.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 23.03.2018.
 */

public interface CompaniesDataSource {

    interface LoadCompaniesCallback{

        void onCompaniesLoaded(@NonNull List<Company> companies);

        void onDataNotAvailable();
    }

    void getCompanies(@NonNull LoadCompaniesCallback callback);
}
