package com.quind.quind.fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quind.quind.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Home.DataUser;
import com.quind.quind.Home.Policy;
import com.quind.quind.Model.AdapterInsurance;
import com.quind.quind.Model.AdapterNews;
import com.quind.quind.Model.HomeDataModel;
import com.quind.quind.Model.HomeNewsDataModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDataFragment extends Fragment {
    private RecyclerView rv,rv2;
    private DataUser dataUser;
    private ArrayList<Policy> policy;
    private ArrayList<HomeDataModel> dataModels;
    private ArrayList<HomeNewsDataModel> datanews;
    public HomeDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_data, container, false);
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("loading");
        progress.setIcon(R.mipmap.ic_launcher);
        progress.setCancelable(false);
        progress.show();
        dataModels = new ArrayList<>();
        datanews = new ArrayList<>();
        policy = new ArrayList<>();
        loaduser();
        loadpolicy();

//        System.out.println("dataUser : " + policy.get(0).getPolicyNumber());
        for (int i=0 ; i<policy.size();i++) {
            dataModels.add(new HomeDataModel(dataUser.getName(), policy.get(i).getInsuranceType()));
        }
        datanews.add(new HomeNewsDataModel("Joker Team"));
        rv = (RecyclerView) v.findViewById(R.id.recycler_home_insurance);
        rv2 = (RecyclerView) v.findViewById(R.id.recycler_home_news);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterInsurance(this.getActivity(),dataModels));


        rv2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv2.setLayoutManager(layoutManager2);
        rv2.setAdapter(new AdapterNews(this.getActivity(),datanews));
        progress.dismiss();
        return v;
    }

    private void loaduser() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datauser",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datauser", null);
        dataUser = new Gson().fromJson(json,DataUser.class);
    }
    private void loadpolicy() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datapolicy", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datapolicy", null);
        Type type = new TypeToken<ArrayList<Policy>>() {}.getType();
        policy = new Gson().fromJson(json, type);
        System.out.println(policy);

        if (policy == null) {
            policy = new ArrayList<>();
        }
    }


//    private void kirimData(int position, Intent intent){
//        Bundle bundle = new Bundle() ;
//        bundle.putSerializable("dataUser", (Serializable) policy.get(position));
//        bundle.putInt("posisi",position);
//        intent.putExtras(bundle);
//    }
}
