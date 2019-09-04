package com.quind.quind.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Model.DataReminder;
import com.quind.quind.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragmentMain extends Fragment{

    ArrayList<DataReminder> data;
    private ReminderListFragment reminderList;
    private ReminderFragment reminder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder_fragment_main,container,false);

        loaddata();

        reminderList = new ReminderListFragment();
        reminder = new ReminderFragment();

        selectFragment();

        return v;

    }

    public void emptySelectFragment(){
        getChildFragmentManager().beginTransaction().replace(R.id.reminder_container, reminder).commit();
    }

    private void selectFragment() {

        if (data.size() > 0) {
            getChildFragmentManager().beginTransaction().replace(R.id.reminder_container, reminderList).commit();
        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.reminder_container, reminder).commit();
        }
    }


    private void loaddata() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datasave", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {}.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loaddata();
        reminderList = new ReminderListFragment();
        selectFragment();
    }
}
