package com.quind.quind.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quind.quind.ChangePassword;
import com.quind.quind.HelpActivity;
import com.quind.quind.Home.DataUser;
import com.quind.quind.PopUpDetailReminder;
import com.quind.quind.PopUpLogout;
import com.quind.quind.PremiHistory;
import com.quind.quind.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView logout,tvNama,tvEmail,tvPremi;
    TextView help;
    TextView tvChangePass;
    private DataUser dataUser;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = v.findViewById(R.id.logout);
        tvNama = v.findViewById(R.id.tv_nama);
        tvEmail =  v.findViewById(R.id.tv_email);
        tvPremi =  v.findViewById(R.id.tv_premi);
        help = v.findViewById(R.id.help);
        loaduser();
        tvNama.setText(dataUser.getName());
        tvEmail.setText(dataUser.getEmail());

        tvPremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PremiHistory.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopUpDetailReminder.class);
                Dialog dialog = new PopUpLogout(getActivity(), intent);
//               startActivityForResult(intent,2);
                dialog.show();
            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        tvChangePass = v.findViewById(R.id.tv_change_pass);
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangePassword.class);
                startActivity(intent);
            }
        });



        return v;
    }
    private void loaduser() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datauser",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datauser", null);
        dataUser = new Gson().fromJson(json,DataUser.class);
    }
}
