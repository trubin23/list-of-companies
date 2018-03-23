package ru.trubin23.listofcompanies.companies;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;
import ru.trubin23.listofcompanies.data.source.CompaniesDataSource;
import ru.trubin23.listofcompanies.data.source.LocalRepository;

/**
 * Created by Andrey on 22.03.2018.
 */

public class CompaniesPresenter implements CompaniesContract.Presenter {

    private static final String TAG = CompaniesPresenter.class.getSimpleName();

    private LocalRepository mLocalRepository;

    private CompaniesContract.View mView;

    CompaniesPresenter(@NonNull LocalRepository localRepository,
                       @NonNull CompaniesContract.View view) {
        mLocalRepository = localRepository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCompanies();
    }

    private void loadCompanies() {
        mLocalRepository.getCompanies(new CompaniesDataSource.LoadCompaniesCallback() {
            @Override
            public void onCompaniesLoaded(@NonNull List<Company> companies) {
                mView.showCompanies(companies);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(TAG, "getCompanies: data not available");
            }
        });
    }
}
