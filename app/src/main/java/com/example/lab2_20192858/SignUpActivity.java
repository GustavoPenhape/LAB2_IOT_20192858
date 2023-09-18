package com.example.lab2_20192858;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.annotation.Nullable;

public class SignUpActivity extends AppCompatActivity {

    private ApiService apiService;
    private String firstName;
    private String lastName;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toast.makeText(this, "Estás en SignUp Activity", Toast.LENGTH_SHORT).show();
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<UserResponse> call = apiService.getUserData();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API_SUCCESS", "La API devolvió una respuesta exitosa");

                    if (response.body() != null) {
                        Log.d("API_DATA", "Datos: " + response.body().toString());

                        UserResponse.Result result = response.body().getResults()[0];
                        firstName = result.getName().getFirst();
                        lastName = result.getName().getLast();
                        String email = result.getEmail();
                        String password = result.getLogin().getPassword();
                        imageUrl = result.getPicture().getLarge();
                        Log.d("API_IMAGE", "URL de la imagen: " + imageUrl);
                        Log.d("API_PASSWORD", "Contraseña extraída: " + password);
                        Log.d("API_NAMES", "Nombres extraídos: " + firstName + ", " + lastName);
                        Button btnNombre = findViewById(R.id.nameButton);
                        Button btnApellido = findViewById(R.id.LastnameButton);
                        Button btnEmail = findViewById(R.id.EmailButton);
                        EditText btnPassword = findViewById(R.id.PasswordButton); // Si es un EditText
                        btnNombre.setText(firstName);
                        btnApellido.setText(lastName);
                        btnEmail.setText(email);
                        btnPassword.setText(password);

                        btnNombre.setVisibility(View.VISIBLE);
                        btnApellido.setVisibility(View.VISIBLE);
                        btnPassword.setVisibility(View.VISIBLE);
                    } else {
                        Log.d("API_ERROR", "El cuerpo de la respuesta es nulo");
                    }
                } else {
                    Log.d("API_ERROR", "Error en la respuesta de la API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("API_FAILURE", "Fallo en la llamada a la API", t);
            }
        });
        Button btnCreateAccount = findViewById(R.id.createAccountButton); // Asegúrate de que el ID coincida
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                intent.putExtra("username", firstName + " " + lastName);
                intent.putExtra("imageUrl", imageUrl);

                startActivity(intent);
            }
        });
    }
}

