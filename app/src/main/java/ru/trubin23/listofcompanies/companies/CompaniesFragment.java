package ru.trubin23.listofcompanies.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.trubin23.listofcompanies.R;

/**
 * Created by Andrey on 22.03.2018.
 */

public class CompaniesFragment extends Fragment implements CompaniesContract.View {

    private CompaniesContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.companies_frag, container, false);

        return root;
    }

    @Override
    public void setPresenter(@NonNull CompaniesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
