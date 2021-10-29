package com.company.catalogapteka;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    @POST("api/4_1/rs/suggest/mktu")
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    Call<APILogic.TovarsFromServer> listTovars(@Body APILogic.TovarsRequest request, @Header("Authorization") String token);
}
