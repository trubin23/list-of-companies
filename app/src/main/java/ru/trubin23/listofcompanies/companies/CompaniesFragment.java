package ru.trubin23.listofcompanies.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.listofcompanies.R;
import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 22.03.2018.
 */

public class CompaniesFragment extends Fragment implements CompaniesContract.View {

    private CompaniesContract.Presenter mPresenter;

    @BindView(R.id.companies_spinner)
    Spinner mCompaniesSpinner;

    @BindView(R.id.company_address)
    TextView mCompanyAddress;

    @BindView(R.id.company_inn)
    TextView mCompanyInn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.companies_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void setPresenter(@NonNull CompaniesContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void setAddress(@NonNull String address) {
        mCompanyAddress.setText(address);
    }

    @Override
    public void setInn(@NonNull String inn) {
        mCompanyInn.setText(inn);
    }

    @Override
    public void showCompanies(@NonNull List<Company> companies) {

    }
}
