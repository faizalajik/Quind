package com.quind.quind.LoginUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quind.quind.R;
import com.quind.quind.MainActivity;



public class SplashScreenActivity extends AppCompatActivity {
    String login="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loaddata();
                if (login.equals("false")) {
                    Intent pindah = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(pindah);
                    finish();
                }
                else{
                    Intent pindah = new Intent(SplashScreenActivity.this, MainActivity.class);
                    pindah.putExtra("splash",login);
                    startActivity(pindah);
                    finish();
                }

            }
        }, 2000);

    }
    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        login = sharedPreferences.getString("login","false");
    }

}
