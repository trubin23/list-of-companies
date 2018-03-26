package ru.trubin23.listofcompanies.companies;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;
import ru.trubin23.listofcompanies.data.source.LoadCompaniesCallback;
import ru.trubin23.listofcompanies.data.source.Repository;

/**
 * Created by Andrey on 22.03.2018.
 */

public class CompaniesPresenter implements CompaniesContract.Presenter {

    private static final String TAG = CompaniesPresenter.class.getSimpleName();

    private Repository mRepository;

    private CompaniesContract.View mView;

    private boolean mForceUpdate = true;

    CompaniesPresenter(@NonNull Repository repository,
                       @NonNull CompaniesContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCompanies();
        mForceUpdate = false;
    }

    private void loadCompanies() {
        mRepository.getCompanies(mForceUpdate, new LoadCompaniesCallback() {
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

    @Override
    public void selectedCompany(@NonNull Company company) {
        mView.setAddress(company.getAddress());
        mView.setInn(company.getInn());
    }
}
