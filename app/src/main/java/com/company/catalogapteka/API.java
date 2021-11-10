package com.company.catalogapteka;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    //Указываем изменяемую часть ссылки,куда будем кидать запрос
    //Также указываем параметры,которые просят в документации
    //После аннтотации боди содержится сам запрос
    @POST("api/v1/clean/address")
    @Headers({
        "Content-Type: application/json",
        "X-Secret: f9a7a97111b8b02a4a288d6e9a1375924e58607f"
    })
    Call<APILogic.geoDataResponse> listTovars(@Body APILogic.adressRequest request, @Header("Authorization") String token);
}
