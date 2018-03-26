package ru.trubin23.listofcompanies.data.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ru.trubin23.listofcompanies.R;
import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 26.03.2018.
 */

public class InitialData {

    private static final String TAG = Company.class.getSimpleName();

    private static InitialData INSTANCE;

    private List<Company> mCompanies;

    private InitialData(@NonNull Context context) {
        mCompanies = initializeData(context);
    }

    public static InitialData getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new InitialData(context);
        }
        return INSTANCE;
    }

    @NonNull
    public List<Company> getCompanies() {
        return mCompanies;
    }

    @NonNull
    private List<Company> initializeData(@NonNull Context context) {
        String json = "";

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.companies);

            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            json = new String(b);
        } catch (IOException e) {
            Log.e(TAG, "error in reading source data about companies", e);
        }

        return jsonToListCompanies(json);
    }

    @NonNull
    private List<Company> jsonToListCompanies(@NonNull String json) {
        Gson gson = new Gson();
        List<Company> companies = gson.fromJson(json,
                new TypeToken<List<Company>>() {
                }.getType());

        if (companies == null){
            companies = new ArrayList<>();
        }

        return companies;
    }
}