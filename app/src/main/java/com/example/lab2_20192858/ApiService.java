package com.example.lab2_20192858;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.lab2_20192858.UserResponse; // Asegúrate de que la ruta sea correcta

public interface ApiService {
    @GET("api/")
    Call<UserResponse> getUserData();
}