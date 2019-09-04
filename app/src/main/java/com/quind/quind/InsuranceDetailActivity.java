package com.quind.quind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quind.quind.Home.DataUser;
import com.quind.quind.Home.Policy;


public class InsuranceDetailActivity extends AppCompatActivity {

    ImageView imgBack ;
    TextView tvNoPolicy, tvBalace, tvNama, tvJenisKelamin,tvTipeAsuransi,tvDueDate ;
    private DataUser dataUserUser;
    private Policy dataPolicy ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_detail);
        initComponent();
        getDataDetail();setLayoutData();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initComponent(){
        tvNoPolicy = findViewById(R.id.tv_polish_number) ;
        tvBalace = findViewById(R.id.tv_idr) ;
        tvNama = findViewById(R.id.tv_nama) ;
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin) ;
        tvTipeAsuransi = findViewById(R.id.tv_tipe_incurance) ;
        tvDueDate = findViewById(R.id.tv_date);
        imgBack = findViewById(R.id.img_back) ;
    }
    private void getDataDetail(){
        Intent intent = getIntent() ;
        dataUserUser = (DataUser) intent.getParcelableExtra("dataUserUser");
        dataPolicy = (Policy) intent.getSerializableExtra("dataPolicy");
    }
    private void setLayoutData(){
        tvNoPolicy.setText(dataPolicy.getPolicyNumber());
        tvBalace.setText("IDR  "+ String.valueOf(dataPolicy.getBalance()));
        tvNama.setText(dataUserUser.getName()) ;
        String jenisKelamin = dataUserUser.getGender() ;
        if(jenisKelamin.equals("L")){
            tvJenisKelamin.setText("Male");
        }else if(jenisKelamin.equals("P")){
            tvJenisKelamin.setText("Female");
        }
        tvTipeAsuransi.setText(dataPolicy.getInsuranceType());
        tvDueDate.setText(String.valueOf(dataPolicy.getPaymentDueDate()));
     }
}
