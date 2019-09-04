package com.quind.quind.LoginUser;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.quind.quind.MainActivity;
import com.quind.quind.PopUpErrorLogin;
import com.quind.quind.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity {
    TextInputLayout logpass, logemail;
    TextInputEditText tielogmail, tielogpass;
    public static Button btnlog ;
    TextView tvlogforpas;
    ApiServices services;
    String jwt,email,password;
    String login = "false";
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginActivity.this.finishAffinity();
                    }
                }).setNegativeButton("No",null).show();
    }

    TextWatcher emailPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            validateEmailPassword();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void validateEmailPassword() {
        if (!tielogmail.getText().toString().equals("") &&

                tielogpass.getText().length() > 0) {
            btnlog.setEnabled(true);
        } else btnlog.setEnabled(false);

    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logpass = findViewById(R.id.til_logpass);
        logemail = findViewById(R.id.til_logemail);
        logpass.setHintTextAppearance(R.style.TextInputLayoutHintText);
        logemail.setHintTextAppearance(R.style.TextInputLayoutHintText);


        btnlog = findViewById(R.id.btn_log);
        tvlogforpas = findViewById(R.id.tv_logforpass);
        tielogmail = findViewById(R.id.edt_logemail);
        tielogpass = findViewById(R.id.edt_logpass);
        tielogmail.addTextChangedListener(emailPasswordTextWatcher);
        tielogpass.addTextChangedListener(emailPasswordTextWatcher);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionLogin();
            }
        });
        tvlogforpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CustomDialogResetPassword cdrp = new CustomDialogResetPassword(LoginActivity.this);
//                cdrp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                cdrp.show();
            }
        });
    }

    private void actionLogin() {

        email = tielogmail.getText().toString();
        password = tielogpass.getText().toString();
        btnlog.setEnabled(false);
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            tielogmail.setError("Email required");


        } else if (password.isEmpty()) {
            tielogpass.setError("Password required");
            tielogpass.requestFocus();

        } else {
            System.out.println("tes");
            final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
            progress.setMessage("loading");
            progress.setIcon(R.mipmap.ic_launcher);
            progress.setCancelable(false);
            progress.show();


            //get inisialisasi retrofit

            ApiServices api = ConfigRetrofit.getInstanceRetrofit();
            //get request
            Call<ResponseLogin> call = api.request_login(email, password);
            //get response
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    System.out.println("baca satu");

                    System.out.println("email : " + email);
                    System.out.println("pass : " + password);
                    progress.dismiss();
                    if (response.code() >= 200 && response.code() <=300)  {
                        System.out.println("masuk");
                        if (response.body().getStatus().equals("Login berhasil!")) {
                            System.out.println("sukses login");
                            jwt = response.body().getJwt();
                            login="true";
                            save();
                            saveJwt();
                            Toast.makeText(LoginActivity.this, response.body().getStatus(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("data",jwt);
                            startActivity(intent);
                        }
                    } else if(response.code() >= 400 && response.code() <=500){

                        Gson gson = new Gson();
                        ResponseLogin responseLogin = new ResponseLogin() ;
                        TypeAdapter<ResponseLogin> adapter = gson.getAdapter(ResponseLogin.class);
                        try {
                            if (response.errorBody() != null)
                                responseLogin =
                                        adapter.fromJson(
                                                response.errorBody().string());
                            if(responseLogin.getStatus().equals("Email salah.")){
                                showDialogLoginFailed("email");
                            }else if(responseLogin.getStatus().equals("Password salah.")){
                                showDialogLoginFailed("password");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("error");
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void showDialogLoginFailed(String kode){

        Intent intent = new Intent(LoginActivity.this, PopUpErrorLogin.class);
        intent.putExtra("kode", kode) ;
        Dialog dialog = new PopUpErrorLogin(LoginActivity.this, intent);
        dialog.show();
    }
    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.apply();
    }
    public void saveJwt() {
        SharedPreferences sharedPreferences = getSharedPreferences("jwt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jwt", jwt);
        editor.apply();
    }
}


