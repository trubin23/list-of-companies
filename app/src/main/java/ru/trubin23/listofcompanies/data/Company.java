package ru.trubin23.listofcompanies.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 21.03.2018.
 */

@Entity(tableName = "companies")
public class Company {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mCompanyId = null;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String mName;

    @ColumnInfo(name = "address")
    @SerializedName("address")
    @Expose
    private String mAddress;

    @ColumnInfo(name = "inn")
    @SerializedName("inn")
    @Expose
    private String mInn;

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

    public void setName(String name) {
        mName = name;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @NonNull
    public String getInn() {
        return mInn;
    }

    public void setInn(String inn) {
        mInn = inn;
    }

    @Override
    public String toString() {
        return mName;
    }
}
