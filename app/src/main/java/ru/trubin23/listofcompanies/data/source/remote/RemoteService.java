package ru.trubin23.listofcompanies.data.source.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.trubin23.listofcompanies.data.Company;

/**
 * Created by Andrey on 26.03.2018.
 */

interface RemoteService {

    @GET("/api_companies/companies")
    Call<List<Company>> getCompanies();

}
