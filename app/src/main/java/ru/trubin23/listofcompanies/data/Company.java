package ru.trubin23.listofcompanies.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andrey on 21.03.2018.
 */

@Entity(tableName = "companies")
public class Company {

    @PrimaryKey
    @ColumnInfo(name = "company_id")
    private final int mCompanyId;

    @NonNull
    @ColumnInfo(name = "name")
    private final String mName;

    @NonNull
    @ColumnInfo(name = "address")
    private final String mAddress;

    @NonNull
    @ColumnInfo(name = "inn")
    private final String mInn;

    public Company(int companyId, @NonNull String name,
                   @NonNull String address, @NonNull String inn) {
        mCompanyId = companyId;
        mName = name;
        mAddress = address;
        mInn = inn;
    }

    public int getCompanyId() {
        return mCompanyId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    @NonNull
    public String getInn() {
        return mInn;
    }
}
