package ru.trubin23.listofcompanies.companies;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

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
    MaterialSpinner mSpinner;

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

        mSpinner.setOnItemSelectedListener((view, position, id, item) ->
                mPresenter.selectedCompany((Company) item));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull CompaniesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setAddress(@NonNull String address) {
        String addressText = String.format("<b>%s:</b> %s",
                getResources().getString(R.string.company_address), address);
        mCompanyAddress.setText(Html.fromHtml(addressText));
    }

    @Override
    public void setInn(@NonNull String inn) {
        String innText = String.format("<b>%s:</b> %s",
                getResources().getString(R.string.company_inn), inn);
        mCompanyInn.setText(Html.fromHtml(innText));
    }

    @Override
    public void showCompanies(@NonNull List<Company> companies) {
        mSpinner.setAdapter(new MaterialSpinnerAdapter<Company>(getContext(), companies) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimensionPixelSize(R.dimen.basic_text_size));
                return textView;
            }
        });

        Company company = (Company) mSpinner.getItems().get(mSpinner.getSelectedIndex());
        mPresenter.selectedCompany(company);
    }
}
