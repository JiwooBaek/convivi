package com.example.project_1.api;

import model.addressSearch.AddressSearch;
import model.regioncode.Regioncode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("v2/local/search/address.json")
    Call<AddressSearch> getSearchAddress(
            @Header("Authorization") String token,
            @Query("query") String query
    );

    @GET("/v2/local/geo/coord2regioncode.json")
    Call<Regioncode> getRegioncode(
            @Header("Authorization") String token,
            @Query("x") String x,
            @Query("y") String y
    );

}

