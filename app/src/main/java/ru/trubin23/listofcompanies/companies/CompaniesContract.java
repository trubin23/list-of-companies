package ru.trubin23.listofcompanies.companies;

import android.support.annotation.NonNull;

import ru.trubin23.listofcompanies.BasePresenter;
import ru.trubin23.listofcompanies.BaseView;

/**
 * Created by Andrey on 22.03.2018.
 */

public interface CompaniesContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
