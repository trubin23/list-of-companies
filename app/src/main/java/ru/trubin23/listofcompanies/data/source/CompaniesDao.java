package ru.trubin23.listofcompanies.data.source;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 21.03.2018.
 */

@Dao
interface CompaniesDao {

    @Query("SELECT * FROM companies")
    List<Company> getCompanies();

    @Query("SELECT * FROM companies WHERE company_id = :companyId")
    Company getCompany(String companyId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompanies(List<Company> companies);
}
