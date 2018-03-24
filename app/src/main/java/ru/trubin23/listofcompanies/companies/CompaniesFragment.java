package ru.trubin23.listofcompanies.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private static final String COMPANY_ID = "COMPANY_ID";
    private static final int INVALID_COMPANY_ID = -1;
    private int mLastCompanyId;

    private CompaniesContract.Presenter mPresenter;

    private ArrayAdapter<Company> mAdapter;

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

        mAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item);
        mCompaniesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Company company = (Company) parent.getSelectedItem();
                mPresenter.selectedCompany(company);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mCompaniesSpinner.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLastCompanyId = INVALID_COMPANY_ID;
        if (savedInstanceState != null) {
            mLastCompanyId = savedInstanceState.getInt(COMPANY_ID, INVALID_COMPANY_ID);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int currentCompanyId = INVALID_COMPANY_ID;
        if (mCompaniesSpinner.getSelectedItem() != null) {
            Company company = (Company) mCompaniesSpinner.getSelectedItem();
            currentCompanyId = company.getCompanyId();
        }

        outState.putInt(COMPANY_ID, currentCompanyId);
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
        mAdapter.clear();
        mAdapter.addAll(companies);

        if (mLastCompanyId == INVALID_COMPANY_ID) {
            return;
        }

        for (int i = 0; i < mCompaniesSpinner.getCount(); i++) {
            Company company = (Company) mCompaniesSpinner.getItemAtPosition(i);
            if (company.getCompanyId() == mLastCompanyId) {
                mCompaniesSpinner.setSelection(i);
                break;
            }
        }
    }
}
