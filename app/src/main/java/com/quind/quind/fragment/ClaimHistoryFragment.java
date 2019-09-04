package com.quind.quind.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Claim.Claim;
import com.quind.quind.Model.AdapterClaimHistory;
import com.quind.quind.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClaimHistoryFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<Claim> claims;
    public ClaimHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_claim_history, container, false);
        loaddata();
        rv = (RecyclerView) v.findViewById(R.id.listhistoryclaim);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterClaimHistory(this.getActivity(),claims));

        return v;
    }
    private void loaddata() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dataclaim",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataclaim", " ");
        Type type = new TypeToken<ArrayList<Claim>>() {}.getType();
        claims = new Gson().fromJson(json,type);
        System.out.println(claims);
    }

}
