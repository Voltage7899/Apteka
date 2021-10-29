package com.company.catalogapteka;

import android.os.Build;

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

public class APILogic {

    //Переменная типа интерфейса
    private API api;

    public APILogic() {
        //Создаем объект для запроса к серверу
        //Получаем ретрофит объект содержащий ссылку на сервер и с указанным конвертером
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://suggestions.dadata.ru/suggestions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //получаем что то типа объекта класса интерфейса через который мы будем взаимодействовать с методом интерфейса API
        api = retrofit.create(API.class);
    }
    public LiveData<List<String>> getTovarsIntended(String pattern) {
        MutableLiveData<List<String>> listTovars = new MutableLiveData<>();

        api.listTovars(new TovarsRequest(pattern), "Token 1e8dbca03b87f394ac3b099901ae5ca3f0455298").enqueue(new Callback<TovarsFromServer>() {
            //@RequiresApi(api = Build.VERSION_CODES.N)


            @Override
            public void onResponse(Call<TovarsFromServer> call, Response<TovarsFromServer> response) {
                if (response.isSuccessful() && response.body() != null) {
                   // listTovars.setValue(response.body().suggestions));
                     for(TovarsFromServer.Tovars tovars:response.body().suggestions){
                         List<String> templist=new ArrayList<>();
                         templist.add(tovars.value);
                         listTovars.setValue(templist);
                     }
                }
            }

            @Override
            public void onFailure(Call<TovarsFromServer> call, Throwable t) {

            }
        });

        return listTovars;
    }
    static class TovarsRequest{
        int count;
        String query;

        public TovarsRequest(String query) {
            this.count = 5;
            this.query = query;
        }
    }
    static class TovarsFromServer{

        static class Tovars{
            String value;


            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }




        }

        List<Tovars> suggestions;

        public List<Tovars> getSuggestions() {
            return suggestions;
        }

        public void setSuggestions(List<Tovars> suggestions) {
            this.suggestions = suggestions;
        }
    }
}
