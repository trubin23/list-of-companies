package ru.trubin23.listofcompanies.data.source;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 21.03.2018.
 */

@Database(entities = {Company.class}, version = 1)
public abstract class WsscTestDatabase extends RoomDatabase {

    private static WsscTestDatabase INSTANCE;

    public abstract CompaniesDao companiesDao();

    private static final Object sLock = new Object();

    public static WsscTestDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WsscTestDatabase.class, "WSSC_Test")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                LocalRepository.ioThread(() -> {
                                    List<Company> companies = prePopulateData();
                                    getInstance(context).companiesDao()
                                            .insertCompanies(companies);
                                });
                            }
                        })
                        .build();
            }
            return INSTANCE;
        }
    }

    @NonNull
    private static List<Company> prePopulateData(){
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(123, "Тестовое имя #1", "ул. первая, д.1", "123456789001"));
        companies.add(new Company(234, "Тестовое имя #2", "ул. вторая, д.2", "123456789002"));
        companies.add(new Company(345, "Тестовое имя #3", "ул. третья, д.3", "123456789003"));
        companies.add(new Company(456, "Тестовое имя #4", "ул. четвертая, д.4", "123456789004"));
        companies.add(new Company(567, "Тестовое имя #5", "ул. пятая, д.5", "123456789005"));
        return companies;
    }
}
