package com.example.retrofit2_ex.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.retrofit2_ex.Model.Category;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getAllCategory();
}
