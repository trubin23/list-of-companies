package ru.trubin23.listofcompanies.companies;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 23.03.2018.
 */

public class SpinAdapter extends ArrayAdapter<Company> {

    private List<Company> mCompanies;

    SpinAdapter(@NonNull Context context, int resource,
                @NonNull List<Company> companies) {
        super(context, resource);
        mCompanies = companies;
    }

    @Override
    public int getCount(){
        return mCompanies.size();
    }

    @Override
    public Company getItem(int position){
        return mCompanies.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //TextView textView = new TextView(context);
        //textView.setTextColor(Color.BLACK);
        //textView.setText(mCompanies.get(position).getName());

        //TODO: implement to add the name of the company to TextView

        return super.getView(position, convertView, parent);
    }
}
