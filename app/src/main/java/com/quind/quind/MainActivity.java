package com.quind.quind;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quind.quind.Claim.Api;
import com.quind.quind.Claim.Claim;
import com.quind.quind.Claim.ConfigRetrofitClaim;
import com.quind.quind.Claim.ResponseClaim;
import com.quind.quind.Home.ApiServices;
import com.quind.quind.Home.ConfigRetrofit;
import com.quind.quind.Home.DataUser;
import com.quind.quind.Home.Policy;
import com.quind.quind.Home.User;
import com.quind.quind.Home.UserPolicy;
import com.quind.quind.fragment.ClaimFragment;

import com.quind.quind.fragment.HomeFragmentMain;
import com.quind.quind.fragment.ProfileFragment;
import com.quind.quind.fragment.ReminderFragmentMain;
import com.quind.quind.fragment.ReserveFragment;
import com.quind.quind.fragment.ReservedFragment;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ViewPager pageatas;
    private BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;
    private TextView title;
    private DataUser dataUser;
    private User user;
    private List<Policy> Policy;
    private ArrayList<String> category;
    private String jwt, login = "false", load = "1";
    private ProgressDialog progress;
    private ResponseClaim responseClaim;
    private List<Claim> dataClaim;
<<<<<<< Updated upstream
=======
    private List<Payment> payments;

>>>>>>> Stashed changes
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finishAffinity();
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loaddata();
        //  loadlogin();
        loadstatus();
        pageatas = findViewById(R.id.idtab);
//        Bundle pindah = getIntent().getExtras();
//        login = pindah.getString("login");
        title = (TextView) findViewById(R.id.textTitle);
        title.setText("Home");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav);
        removeShiftMode(bottomNavigationView);
        buttom();

        Bundle bundle = getIntent().getExtras();
        String jwt = bundle.getString("data");


        if (load.equals("1")) {
            progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("loading");
            progress.setIcon(R.mipmap.ic_launcher);
            progress.setCancelable(false);
            progress.show();

            if (jwt != null) {
                saveJwt(jwt);
            }
            loadApi(jwt);
            loadClaim(jwt);
            loadApiPolicy(jwt);
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.con, new HomeFragmentMain());
            transaction.commit();
        }


    }

<<<<<<< Updated upstream
=======
    private void loadPremi(String jwt) {
        ApiPremi api = ConfigRetrofitPremi.getInstanceRetrofit();
        Call<ResponsePremi> call = api.dataPremi(jwt);
        call.enqueue(new Callback<ResponsePremi>() {
            @Override
            public void onResponse(Call<ResponsePremi> call, Response<ResponsePremi> response) {
                payments = response.body().getPayments();
                savePremi();
            }

            @Override
            public void onFailure(Call<ResponsePremi> call, Throwable t) {

            }
        });
    }

>>>>>>> Stashed changes
    private void loadApiPolicy(String jwt) {
        ApiServices api = ConfigRetrofit.getInstanceRetrofit();
        Call<UserPolicy> call = api.userPolicy(jwt);
        call.enqueue(new Callback<UserPolicy>() {
            @Override
            public void onResponse(Call<UserPolicy> call, Response<UserPolicy> response) {
                Policy = response.body().getPolicies();
                savePolicy();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.con, new HomeFragmentMain());
                transaction.commit();
                load = "2";
                save();
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<UserPolicy> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadApi(String jwt) {
        ApiServices api = ConfigRetrofit.getInstanceRetrofit();
        Call<User> call = api.dataUser(jwt);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                dataUser = user.getUser();
                saveUser();

//                String nama = dataUser.getName();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadClaim(String jwt) {
        Api api = ConfigRetrofitClaim.getInstanceRetrofit();
        Call<ResponseClaim> call = api.dataClaim(jwt);
        call.enqueue(new Callback<ResponseClaim>() {
            @Override
            public void onResponse(Call<ResponseClaim> call, Response<ResponseClaim> response) {
                dataClaim = response.body().getClaims();
                saveClaim();
            }

            @Override
            public void onFailure(Call<ResponseClaim> call, Throwable t) {

            }
        });
    }

    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    public void buttom() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home2:
                        title.setText("Home");
                        fragment = new HomeFragmentMain();
                        break;
                    case R.id.claim:
                        title.setText("Claime Report");
                        fragment = new ClaimFragment();
                        break;
                    case R.id.reminder:
                        title.setText("Premi Reminder");
                        fragment = new ReminderFragmentMain();
                        break;
                    case R.id.reserve:
                        title.setText("Reserve");
                        fragment = new ReserveFragment();
                        break;
                    case R.id.profile:
                        title.setText("Profile");
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.con, fragment)
                        .commit();

                return true;
            }
        });
    }

    public void saveUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("datauser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataUser);
        editor.putString("datauser", json);
        editor.apply();

    }

    public void saveJwt(String jwt) {
        SharedPreferences sharedPreferences = getSharedPreferences("dataJwt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jwt", jwt);
        editor.apply();

    }

    public void savePolicy() {
        SharedPreferences sharedPreferences = getSharedPreferences("datapolicy", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Policy);
        editor.putString("datapolicy", json);
        editor.apply();
    }
<<<<<<< Updated upstream
=======

    public void savePremi() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataPremi", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(payments);
        editor.putString("dataPremi", json);
        editor.apply();
    }

>>>>>>> Stashed changes
    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE);
        jwt = sharedPreferences.getString("jwt", null);
    }

    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("load", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("load", load);
        editor.apply();
    }

    public void loadstatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("load", MODE_PRIVATE);
        load = sharedPreferences.getString("load", "1");
    }

    public void saveClaim() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataclaim", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataClaim);
        editor.putString("dataclaim", json);
        editor.apply();

    }


}
