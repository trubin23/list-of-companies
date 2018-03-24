package ru.trubin23.listofcompanies.companies;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.listofcompanies.BasePresenter;
import ru.trubin23.listofcompanies.BaseView;
import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 22.03.2018.
 */

public interface CompaniesContract {

    interface View extends BaseView<Presenter> {
        void setAddress(@NonNull String address);

        void setInn(@NonNull String inn);

        void showCompanies(@NonNull List<Company> companies);
    }

    interface Presenter extends BasePresenter {
        void selectedCompany(@NonNull Company company);
    }
}
