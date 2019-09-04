package com.quind.quind;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.quind.quind.LoginUser.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class PopUpLogout extends Dialog {

    private Button btnYes,btnNo;
    private TextView tvtitle,tvKeterangan;
    private Context context;
    private Intent intent ;
    private SharedPreferences sharedPreferences,a,b,c,d,e;
    private SharedPreferences.Editor editor, aa,bb,cc;
    public PopUpLogout(@NonNull Context context,Intent intent) {
        super(context);
        this.context = context;
        this.intent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up_logout);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        initKomponen();
    }

    public void initKomponen(){
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        tvtitle = findViewById(R.id.tv_title);
        tvKeterangan = findViewById(R.id.tv_keterangan);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleardata();
                Intent intent = new Intent(context,LoginActivity.class);
                getContext().startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    public void cleardata() {
        sharedPreferences = context.getSharedPreferences("login", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        a = context.getSharedPreferences("datasave", MODE_PRIVATE);
        a.edit().clear().apply();
        b = context.getSharedPreferences("datapolicy", MODE_PRIVATE);
        b.edit().clear().apply();
        c = context.getSharedPreferences("datauser", MODE_PRIVATE);
        c.edit().clear().apply();
        d = context.getSharedPreferences("jwt", MODE_PRIVATE);
        d.edit().clear().apply();
        e = context.getSharedPreferences("load", MODE_PRIVATE);
        e.edit().clear().apply();
//        aa.clear();
//        aa.apply();
//        bb.clear();
//        bb.apply();
//        cc.clear();
//        cc.apply();

    }
}
