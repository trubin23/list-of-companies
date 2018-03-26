package ru.trubin23.listofcompanies;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.trubin23.listofcompanies.data.source.LocalRepository;
import ru.trubin23.listofcompanies.data.source.WsscTestDatabase;
import ru.trubin23.listofcompanies.data.util.InitialData;

/**
 * Created by Andrey on 22.03.2018.
 */

public class Injection {

    public static LocalRepository provideLocalRepository(@NonNull Context context) {
        InitialData initialData = InitialData.getInstance(context);
        WsscTestDatabase database = WsscTestDatabase.getInstance(context);
        return LocalRepository.getInstance(
                database.companiesDao(), initialData.getCompanies());
    }
}
