package com.example.testforcookdev.api;

import com.example.testforcookdev.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {


    public interface APIHelperCallback<T> {
        void onResponse(T response);

        void onError();
    }

    private static APIHelper instance;
    private APIService apiService;

    private APIHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static APIHelper getInstance() {
        if (instance == null)
            instance = new APIHelper();
        return instance;
    }


    public void getRecipes(int category, final APIHelperCallback<List<Recipe>> callback) {
        Call<List<Recipe>> call = apiService.getCooks(category);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                callback.onError();
            }
        });
    }


}
