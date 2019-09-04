package com.quind.quind.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quind.quind.AddReminder;
import com.quind.quind.Model.AdapterReminder;
import com.quind.quind.Model.DataReminder;
import com.quind.quind.R;
import com.quind.quind.Model.RvClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */

public class ReminderListFragment extends CardViewlistReminder implements RvClickListener {
    RecyclerView recycler;

    private RecyclerView rv;
    private ArrayList<DataReminder> data;
    private TabLayout tabbawah;
    private int i=0;
    private AdapterReminder adapterReminder;



    public ReminderListFragment(){

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        adapterReminder = new AdapterReminder(this);
//        i = adapterReminder.del();
        rv = view.findViewById(R.id.recyclerlistadd);
        if (i==1){
            ReminderFragment rf = new ReminderFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.reminder_container,rf);
            fragmentTransaction.commit();
        }
        if(data.size()==0){
            ReminderFragmentMain fragmentReminderMain=new ReminderFragmentMain();
            FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.reminder_container,fragmentReminderMain);
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_list, container, false);
        loaddata();


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddReminder.class);
                getActivity().startActivity(intent);
//                ReminderAddFragment rlf = new ReminderAddFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.reminder_list,rlf);
//                fragmentTransaction.commit();
//                tabbawah = view.findViewById(R.id.Tabbawah);
//                tabbawah.setVisibility(View.GONE);

            }
        });
//        fab.getP

        return view;
    }



    @Override
    public void onDeleted(int position) {
        adapterReminder.notifyItemChanged(position);
        ((FragmentActivity) getContext() ).getSupportFragmentManager().beginTransaction()
                .replace(R.id.reminder_container,new ReminderFragment())
                .commit();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loaddata();
        adapterReminder = new AdapterReminder(getActivity(),data,this);

        i = adapterReminder.del();
//        if (i==1){
//            ReminderFragment rf = new ReminderFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.reminder_container,rf);
//            fragmentTransaction.commit();
//        }



        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapterReminder);
        //updateUI();
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

    public static Fragment newInstance() {
        return new ReminderListFragment();
    }

//    private void updateUI(){
//        if(data.size()==0){
//            FragmentReminderMain fragmentReminderMain = new FragmentReminderMain();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.reminder_container,fragmentReminderMain);
//            fragmentTransaction.commit();
//        }
//    }
}