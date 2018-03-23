package ru.trubin23.listofcompanies.companies;

import android.support.annotation.NonNull;

import ru.trubin23.listofcompanies.data.source.LocalRepository;

/**
 * Created by Andrey on 22.03.2018.
 */

public class CompaniesPresenter implements CompaniesContract.Presenter {

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
        mLocalRepository.getCompanies();
    }
}
