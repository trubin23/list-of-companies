package ru.trubin23.listofcompanies.data.source;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
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
                        .build();
            }
            return INSTANCE;
        }
    }
}
