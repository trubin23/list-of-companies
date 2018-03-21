package ru.trubin23.listofcompanies.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 21.03.2018.
 */

@Database(entities = {Company.class}, version = 1)
public abstract class  CompaniesDatabase extends RoomDatabase {

    private static CompaniesDatabase INSTANCE;

    public abstract CompaniesDao companiesDao();

    private static final Object sLock = new Object();

    public static CompaniesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CompaniesDatabase.class, "WSSC_Test")
                        .build();
            }
            return INSTANCE;
        }
    }
}
