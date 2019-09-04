package com.quind.quind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quind.quind.Home.DataUser;
import com.quind.quind.LoginUser.ApiServices;
import com.quind.quind.LoginUser.ConfigRetrofit;
import com.quind.quind.LoginUser.LoginActivity;
import com.quind.quind.LoginUser.Response;
import com.quind.quind.LoginUser.ResponseLogin;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.quind.quind.LoginUser.User;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class ChangePassword extends AppCompatActivity {


    Button btnChangePassword;
    private DataUser dataUser ;
    private String jwt;
    TextInputEditText tieOldPassword, tieNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btnChangePassword = findViewById(R.id.btn_change_pass) ;
        tieNewPassword = findViewById(R.id.edt_new_pass) ;
        tieOldPassword = findViewById(R.id.edt_old_pass);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaduser();
                String oldPassword = tieOldPassword.getText().toString() ;
                String newPassword = tieNewPassword.getText().toString() ;
                ApiServices api = ConfigRetrofit.getInstanceRetrofit();
                //get request
                loadJwt();
                retrofit2.Call<Response> call = api.updatePassword(jwt,oldPassword, newPassword);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(retrofit2.Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.code() >= 200 && response.code() <=300)  {
                            Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else if(response.code() >= 400 && response.code() <=500){

                            Gson gson = new Gson();
                            Response responseChange = new Response() ;
                            TypeAdapter<Response> adapter = gson.getAdapter(Response.class);
                            try {
                                if (response.errorBody() != null)
                                    responseChange =
                                            adapter.fromJson(
                                                    response.errorBody().string());
                                if(responseChange.getStatus().equals("Failed")){
                                    Toast.makeText(getBaseContext(), "Password lama tidak sesuai",Toast.LENGTH_SHORT).show();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("error");
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Response> call, Throwable t) {

                    }
                });
            }
        });
    }
    private void loaduser() {

        SharedPreferences sharedPreferences =  getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datauser", null);
        dataUser = new Gson().fromJson(json,DataUser.class);
    }
    public void loadJwt() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataJwt", MODE_PRIVATE);
        jwt = sharedPreferences.getString("jwt","false");
    }
}
