package com.quind.quind.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Home.DataUser;
import com.quind.quind.Home.Policy;
import com.quind.quind.InsuranceDetailActivity;
import com.quind.quind.R;
import com.quind.quind.fragment.HomeDataFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterInsurance extends RecyclerView.Adapter<AdapterInsurance.ViewHolder> {

    HomeDataFragment insurance;
    String name;
    private ArrayList<HomeDataModel> insuranceModels;
    HomeDataModel data;
    private DataUser dataUserUser;
    private Context context;
    private OnItemClickListener mListener;
    private ArrayList<Policy> policy ;

    public AdapterInsurance(Context context, ArrayList<HomeDataModel> data){
        this.context = context;
        this.insuranceModels = data;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TVinsurance;
        TextView TVname;
        ImageView Image;
        View root;


        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            root = view;
            root.setClickable(true);
            root.setOnClickListener(this);
            TVinsurance = (TextView) view.findViewById(R.id.tv_insurance);
            TVname = (TextView) view.findViewById(R.id.tv_name);
            Image = view.findViewById(R.id.img_category);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }


    @Override
    public AdapterInsurance.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_home_data, parent, false);

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final AdapterInsurance.ViewHolder holder, final int position) {
        data = insuranceModels.get(position);
        loaduser();
        loadpolicy();
        String checkImageTitle=data.getTitle();
        if(insuranceModels.get(position).getSubtitle().equals("Cyber Privacy Risk")){

            holder.Image.setImageResource(R.drawable.typerisk_cyberprivacy);
        }
        else if (insuranceModels.get(position).getSubtitle().equals("Mobile & Tablet")){
            holder.Image.setImageResource(R.drawable.typerisk_mobile);
        }else if(insuranceModels.get(position).getSubtitle().equals("Social Media Account")){
            holder.Image.setImageResource(R.drawable.typerisk_mediaaccount);
        }

        holder.TVname.setText(insuranceModels.get(position).getTitle());
        holder.TVinsurance.setText(insuranceModels.get(position).getSubtitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InsuranceDetailActivity.class) ;
//                intent.putExtras("dataUserUser",dataUserUser);
                kirimData(position,intent) ;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return insuranceModels.size();
    }

    private void kirimData(int position, Intent intent){
        Bundle bundle = new Bundle() ;
        bundle.putSerializable("dataPolicy",policy.get(position));
        bundle.putParcelable("dataUserUser", dataUserUser);
        bundle.putInt("posisi",position);
        intent.putExtras(bundle);
    }

    private void loadpolicy() {
        Activity activity = (Activity)context ;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("datapolicy", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datapolicy", null);
        Type type = new TypeToken<ArrayList<Policy>>() {}.getType();
        policy = new Gson().fromJson(json, type);

        if (policy == null) {
            policy = new ArrayList<>();
        }
    }


    private void loaduser() {
        Activity activity = (Activity)context ;

        SharedPreferences sharedPreferences = activity.getSharedPreferences("datauser",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datauser", null);
        dataUserUser = new Gson().fromJson(json,DataUser.class);
    }
}