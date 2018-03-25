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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mCompanyId = null;

    @NonNull
    @ColumnInfo(name = "name")
    private final String mName;

    @NonNull
    @ColumnInfo(name = "address")
    private final String mAddress;

    @NonNull
    @ColumnInfo(name = "inn")
    private final String mInn;

    public Company(@NonNull String name, @NonNull String address, @NonNull String inn) {
        mName = name;
        mAddress = address;
        mInn = inn;
    }

    public Long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(Long companyId) {
        mCompanyId = companyId;
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

    @Override
    public String toString() {
        return mName;
    }
}
