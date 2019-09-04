package com.quind.quind;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.quind.quind.LoginUser.LoginActivity;


public class PopUpErrorLogin extends Dialog{
    Context context ;
    Intent intent ;
    TextView tvJudul, tvIsiPesan , tvOk ;
    public static  int enebaledLogin ;
    public PopUpErrorLogin(@NonNull Context context, Intent intent) {
        super(context);
        this.context = context ;
        this.intent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_email_error);
        initComponent();
        setIsiPesan();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginActivity.btnlog.setEnabled(true);
                dismiss();
            }
        });
    }
    private void initComponent(){
        tvJudul = findViewById(R.id.tv_judul) ;
        tvIsiPesan = findViewById(R.id.tv_isi_pesan) ;
        tvOk = findViewById(R.id.btn_ok) ;
    }

    private void setIsiPesan(){
        String kode = intent.getStringExtra("kode");
        if(kode.equals("email")){
            tvIsiPesan.setText("That email doesn’t seem registered in\n" +
                    "Quind. Did you want to try again?");
        }else if(kode.equals("password")){
            tvIsiPesan.setText("Password error. Check your password\n" +
                    "and try again");
        }
    }

}
