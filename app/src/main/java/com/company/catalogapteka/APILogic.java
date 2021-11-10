package com.company.catalogapteka;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class APILogic {

    //Переменная типа интерфейса
    public API api;

    public APILogic() {
        //Создаем объект для запроса к серверу
        //Получаем ретрофит объект содержащий ссылку на сервер и с указанным конвертером
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cleaner.dadata.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //получаем что то типа объекта класса интерфейса через который мы будем взаимодействовать с методом интерфейса API
        api = retrofit.create(API.class);
    }

    //Пишем метод получения запроса
    public LiveData<geoDataResponse> getGeoData(String pattern) {
        //Живая дата для списка запросов
        MutableLiveData <geoDataResponse> liveGeoData=new MutableLiveData<>();
        //Временный список,чтобы потом кинуть в живую дату
        List<String> templist=new ArrayList<>();

        //Сам асихронный запрос,где колбеком мы получаем запросы
        api.listTovars(new adressRequest(pattern), "Token 1e8dbca03b87f394ac3b099901ae5ca3f0455298").enqueue(new Callback<geoDataResponse>() {
            //@RequiresApi(api = Build.VERSION_CODES.N)


            @Override
            public void onResponse(Call<geoDataResponse> call, Response<geoDataResponse> response) {
                //Проверяем,пустой ли ответ
                if (response.isSuccessful() && response.body() != null) {
                   // listTovars.setValue(response.body().suggestions));


                    //liveGeoData.setValue(response.body().suggestions.get(0));

                }


            }

            @Override
            public void onFailure(Call<geoDataResponse> call, Throwable t) {

            }
        });

        return liveGeoData;
    }
   public static class adressRequest{
       // int count;
        String address;

        public adressRequest(String query) {
           // this.count = 5;
            this.address = query;
        }
    }
    public  class geoDataResponse{


            List<geoData> suggestions;


        public List<geoData> getSuggestions() {
            return suggestions;
        }

        public  class geoData{

            public String value;
            public String source;
            public String result;
            public String postal_code;
            public String country;
            public String region;
            public String city_area;
            public String city_district;
            public String street;
            public String house;
            public String geo_lat;
            public String geo_lon;
            public int qc_geo;

            public String getGeo_lat() {
                return geo_lat;
            }

            public String getGeo_lon() {
                return geo_lon;
            }

        }

    }
}
