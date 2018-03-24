package ru.trubin23.listofcompanies.companies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.listofcompanies.Injection;
import ru.trubin23.listofcompanies.R;
import ru.trubin23.listofcompanies.util.ActivityUtils;

public class CompaniesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companies_act);

        CompaniesFragment companiesFragment =
                (CompaniesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (companiesFragment == null) {
            companiesFragment = new CompaniesFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), companiesFragment, R.id.contentFrame);
        }

        new CompaniesPresenter(Injection.provideLocalRepository(getApplicationContext()),
                companiesFragment);
    }
}
