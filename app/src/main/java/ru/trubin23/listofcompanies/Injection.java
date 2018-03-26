package ru.trubin23.listofcompanies;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.trubin23.listofcompanies.data.source.Repository;
import ru.trubin23.listofcompanies.data.source.local.LocalRepository;
import ru.trubin23.listofcompanies.data.source.local.WsscTestDatabase;
import ru.trubin23.listofcompanies.data.source.remote.RemoteRepository;
import ru.trubin23.listofcompanies.data.util.InitialData;

/**
 * Created by Andrey on 22.03.2018.
 */

public class Injection {

    public static Repository provideLocalRepository(@NonNull Context context) {
        InitialData initialData = InitialData.getInstance(context);
        WsscTestDatabase database = WsscTestDatabase.getInstance(context);
        return Repository.getInstance(RemoteRepository.getInstance(),
                LocalRepository.getInstance(database.companiesDao(), initialData.getCompanies()));
    }
}
