package com.quind.quind.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quind.quind.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Home.Policy;
import com.quind.quind.Model.HomeDataModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentMain extends Fragment {

    private HomeDataFragment homedata;
    private HomeFragment home;
    private ArrayList<Policy> data;
    private ArrayList<HomeDataModel> datahome;
    public HomeFragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_fragment_main, container, false);

        homedata = new HomeDataFragment();
        home = new HomeFragment();
        loaddata();
        selectFragment();

        return v;
    }



    private void selectFragment() {

        if (data.isEmpty()) {
            getChildFragmentManager().beginTransaction().replace(R.id.home_container, home).commit();
        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.home_container, homedata).commit();

        }
    }

    private void loaddata() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datapolicy",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datapolicy", " ");
        Type type = new TypeToken<ArrayList<Policy>>() {}.getType();
        data = new Gson().fromJson(json,type);
    }
}
