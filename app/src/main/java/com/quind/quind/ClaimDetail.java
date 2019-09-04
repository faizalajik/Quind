package com.quind.quind;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Claim.Claim;
import com.quind.quind.Claim.DataClaime;
import com.quind.quind.Home.DataUser;
import com.quind.quind.Home.Policy;
import com.quind.quind.Model.AdapterExpandableList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClaimDetail extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private AdapterExpandableList listAdapter;
    private List<String> listHeader;
    private HashMap<String,List<DataClaime>> listHashMap;
    private List<DataClaime> data;
    private DataUser dataUser;
    private ArrayList<Policy> policy;
    private ArrayList<Claim> claim;
    private ProgressDialog progress;
    private int posisi;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_detail);
        progress = new ProgressDialog(this);
        progress.setMessage("loading");
        progress.setIcon(R.mipmap.ic_launcher);
        progress.setCancelable(false);
        progress.show();
        Bundle bundle = getIntent().getExtras();
        posisi = bundle.getInt("posisi") ;
        loadpolicy();
        loaduser();
        loadclaim();
        initData();
        expandableListView = findViewById(R.id.ex_list);
        listAdapter = new AdapterExpandableList(this,listHeader,data);
        expandableListView.setAdapter(listAdapter);

    }

    private void initData() {
        listHeader = new ArrayList<>();
        listHashMap = new HashMap<>();
        data =  new ArrayList<>();

        listHeader.add("Claim Details");
        listHeader.add("Claim Status");

        data.add(new DataClaime(dataUser.getName(),policy.get(posisi).getInsuranceType()
                ,claim.get(posisi).getRequirementsAcceptedAt(),
                claim.get(posisi).getPolicyNumber(),claim.get(posisi).getAmount()));

//        data.add (new DataClaime(dataUser.getName(),policy.get(posisi).getInsuranceType(),"23/10/2018"
//                ,policy.get(posisi).getPolicyNumber(),100000));

        listHashMap.put(listHeader.get(0),data);
        listHashMap.put(listHeader.get(1),data);
        progress.dismiss();

    }
    private void loaduser() {

        SharedPreferences sharedPreferences = getSharedPreferences("datauser",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datauser", null);
        dataUser = new Gson().fromJson(json,DataUser.class);
    }
    private void loadpolicy() {
        SharedPreferences sharedPreferences = getSharedPreferences("datapolicy", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datapolicy", null);
        Type type = new TypeToken<ArrayList<Policy>>() {}.getType();
        policy = new Gson().fromJson(json, type);
        System.out.println(policy);

        if (policy == null) {
            policy = new ArrayList<>();
        }
    }
    private void loadclaim() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataclaim", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataclaim", null);
        Type type = new TypeToken<ArrayList<Claim>>() {}.getType();
        claim = new Gson().fromJson(json, type);
        System.out.println(claim);
    }

}
