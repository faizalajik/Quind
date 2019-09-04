package com.quind.quind;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.quind.quind.Model.DataReminder;

public class PopUpDetailReminder extends Dialog {

    ImageView imgCategory ;
    Button btnTutupDetail;
    TextView tvDetailKategori,tvDetailTanggalSatu,tvDetailTanggalDua,tvDetailWaktu,tvDetailPremi,tvDetailNote;
    DataReminder reminder ;
    int posisiEdit = 0 ;
    Context context ;
    String category;

    Intent intent ;
    public PopUpDetailReminder(@NonNull Context context, Intent intent) {
        super(context);
        this.context = context  ;
        this.intent = intent ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up_detail_reminder);
//
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        initKomponen();getDataDetail();

        btnTutupDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        initKomponen();getDataDetail();setLayoutData();
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        int lebar = metrics.widthPixels ;
//        int tinggi = metrics.heightPixels ;
//
//
//        getWindow().setLayout((int)(lebar*.7),(int)(tinggi*.4));

    }
//
    private void initKomponen(){
        tvDetailKategori = findViewById(R.id.tv_kategori_detail) ;
        tvDetailTanggalSatu = findViewById(R.id.tv_detail_tanggal) ;
        tvDetailTanggalDua = findViewById(R.id.tv_tanggal_detail) ;
        tvDetailWaktu = findViewById(R.id.tv_detail_waktu) ;
        tvDetailNote = findViewById(R.id.tv_detail_note) ;
        imgCategory = findViewById(R.id.img_gambar_kategori);
        tvDetailPremi = findViewById(R.id.tv_total_premi_detail);
        btnTutupDetail = findViewById(R.id.btn_close) ;
    }

    private void getDataDetail(){
        Bundle bundle = intent.getExtras();
        reminder = (DataReminder) bundle.getSerializable("data");
        category = reminder.getCategory();
        setLayoutData();
        posisiEdit = bundle.getInt("posisi") ;


    }

    public void setLayoutData(){

        tvDetailKategori.setText(reminder.getCategory());
        if(category.equals("Health Insurance")){
            imgCategory.setImageResource(R.drawable.healt);
        }
        else if (category.equals("Car Insurance")){
            imgCategory.setImageResource(R.drawable.group_3);
        }
        tvDetailTanggalSatu.setText(reminder.getTanggal());
        tvDetailTanggalDua.setText(reminder.getTanggal());
        tvDetailWaktu.setText(reminder.getWaktu());
        tvDetailPremi.setText(reminder.getTotal());
        tvDetailNote.setText(reminder.getNote());
        tvDetailNote.setMovementMethod(new ScrollingMovementMethod());
    }
}
