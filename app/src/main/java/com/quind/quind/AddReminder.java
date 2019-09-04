package com.quind.quind;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Model.DataReminder;
import com.quind.quind.fragment.ReminderListFragment;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class AddReminder extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog timePickerDialog;
    private Spinner spinnerAdd;
    private Button btnUpdate;
    private TextView textTitle, Editdate, Edittime;
    private EditText  Edittotal, Editnote;
    private String category;
    private String total;
    private String tanggal;
    private String waktu;
    private String note;
    public static ArrayList<DataReminder> data = new ArrayList<>();
    private String[] jenisAsuransi = {
            "Cyber and Privacy Risk",
            "Mobile and Tablet",
            "Social Media Account",
    };

    private long todayTime;
    private long timeCompare;
    private long diffTime ;
    ArrayAdapter<String> adapter;

    /*
     var status untuk memberi tanda bahwa akan dilakukan proses edit, yg akan dikirim melalui adapter reminder atau detail reminder,
      dengan mengirim value update. alasan menggunakan modifier static agar dapat diset diluar kelas ini dan membantu mengirim value update
      */
    public static String status = "";
    /*
    dilakukan pengecekan ulang atau memeriksa tanda, apabila statusDetail update maka yg meminta proses edit dari detail reminder
    , jika statusDetail = "" maka yg meminta proses edit adalah dari recycler atau kelas adapter reminder
     */
    public static String statusDetail = "";

    int posisiEdit = 0; // untuk mendapatkan posisi dari si data yang akan diedit supaya dapat me replace data dengan yang baru
    Intent intentEdit = getIntent();// membantu menangkap data yang akan di edit dan dikirim dari adapter reminder

    int number ;
    Random Rnumber ;
    private CheckBox setMonthly;
    private String monthly ;
    AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        loaddata();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        spinnerAdd = (Spinner) findViewById(R.id.selectinsu);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, jenisAsuransi);
        spinnerAdd.setAdapter(adapter);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        initComponent();// casting komponen layout
//        Edittotal.addTextChangedListener(new NumberTextWatcher(Edittotal, "#,##.00"));
        Edittotal.addTextChangedListener(new NumberTextWatcherForThousand(Edittotal));


        Editnote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);

                }
            }
        });

        Edittotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);

                    //sore hari

                }
            }
        });

        Editdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String before=Editdate.getText().toString();
                if(b){
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    showDateDialog();

                }
                else if(!Editdate.getText().toString().equals("")){
                    Editdate.setError(null);
                    Editdate.clearFocus();
                }
            }
        });

        Edittime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    showTimeDialog();


                }
                else if(!Edittime.getText().toString().equals("")){
                    Edittime.setError(null);
                    Edittime.clearFocus();
                }
            }
        });
        //////////////////////

        Editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tambahkan sesuatu
                // Editdate.setFocusableInTouchMode(true);
                // Editdate.setFocusable(true);

                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


//                Edittotal.setFocusable(false);
//                Edittotal.setFocusableInTouchMode(false);
//                Editnote.setFocusable(false);
//                Editnote.setFocusableInTouchMode(false);

                Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);
                Editdate.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                showDateDialog();

//
//                Edittotal.setFocusable(true);
//                Edittotal.setFocusableInTouchMode(true);
//                Editnote.setFocusable(true);
//                Editnote.setFocusableInTouchMode(true);
            }
        });
        Edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                //tambahkan sesuatu
//                //Edittime.setFocusableInTouchMode(true);
//                //Edittime.setFocusable(true);
//
//                Edittotal.setFocusable(false);
//                Edittotal.setFocusableInTouchMode(false);
//                Editnote.setFocusable(false);
//                Editnote.setFocusableInTouchMode(false);

                Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                Edittime.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                showTimeDialog();


//                Edittotal.setFocusable(true);
//                Edittotal.setFocusableInTouchMode(true);
//                Editnote.setFocusable(true);
//                Editnote.setFocusableInTouchMode(true);
            }
        });

        if (status == "update") {
            //proses update atau edit
            btnUpdate.setText("Update");
            textTitle.setText("Edit Reminder");
            getDataEdit();// memanggil metod yang menjalankan proses penangkapan data
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //proses replace data

                  //  todayTime=Calendar.getInstance().getTimeInMillis();


                    ambilData();cekData();
                    //lebih baik pake while biar bisa multiple alert gitu, cuma harus handle focusnya :(

                }
            });
        } else {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ambilData();
                    // todayTime=Calendar.getInstance().getTimeInMillis();

                    cekData();
                    /**
                     * ALARM
                     */
//                        Toast.makeText(getApplicationContext(), "Data Disimpan di Internal", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(AddReminder.this,ReminderListFragment.class);
//                        startActivity(intent);
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.container_remin,rlf);
//                        fragmentTransaction.commit();

                    //AddReminder.this.finish();

                }
            });
        }

    }

    private void initComponent() {
        textTitle=findViewById(R.id.textTitle);
        Editdate =  findViewById(R.id.edit_date);
        Edittotal = findViewById(R.id.edit_total);
        Edittime =  findViewById(R.id.edit_time);
        btnUpdate =  findViewById(R.id.button_update);
        Editnote =  findViewById(R.id.edit_note);
        setMonthly = findViewById(R.id.checkBox) ;
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        timeCompare=0;


        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                //timeCompare=newDate.getTimeInMillis();
                Calendar now = Calendar.getInstance();
                diffTime = newDate.compareTo(now);
                Editdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }


    private void showTimeDialog() {


        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                if (minute < 10 && hourOfDay <10){
                    Edittime.setText("0"+String.valueOf(hourOfDay)
                            +":0"+String.valueOf(minute));
                }
                else if (minute < 10 && hourOfDay >10){
                    Edittime.setText(String.valueOf(hourOfDay)
                            +":0"+String.valueOf(minute));
                }
                else if ( minute > 10 && hourOfDay <10) {
                    Edittime.setText("0"+String.valueOf(hourOfDay)
                            +":"+String.valueOf(minute));
                }
                else {
                    Edittime.setText(String.valueOf(hourOfDay)
                            +":"+String.valueOf(minute));
                }
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("datalist", json);
        editor.apply();

    }
    private void ambilData() {
        category = spinnerAdd.getSelectedItem().toString();
        total = Edittotal.getText().toString();
        tanggal = Editdate.getText().toString();
        waktu = Edittime.getText().toString();
        note = Editnote.getText().toString();
    }

    private void cekData(){
        if (total.isEmpty()) {
            Edittotal.setError("The Amount must be filled");
            Edittotal.requestFocus();
        } else if (tanggal.isEmpty()) {
            Editdate.setError("Date must be filled");
            Editdate.requestFocus();
        }else if (diffTime <0){
            Editdate.setError("Invalid date");
            Editdate.requestFocus();
        }else if(waktu.isEmpty()) {
            Edittime.setError("Time must be filled");
            Edittime.requestFocus();
        } else if (note.isEmpty()) {
            Editnote.setError("Note must be filled");
            Editnote.requestFocus();
        }else {
            addReminder();
            //ini
            finish();
        }
    }

    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {
        }.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }

    public void getDataEdit() {
        //proses penangkapan data yang dikirim dari adapter reminder yang akan di edit, dan prosess set layout
        Bundle bundle = getIntent().getExtras();
        DataReminder reminder = (DataReminder) bundle.getSerializable("data");
        int spinnerPosisi = adapter.getPosition(reminder.getCategory()); // proses mencari posisi data yang ada di spinner dengan value berikut
        spinnerAdd.setSelection(spinnerPosisi);
        Editdate.setText(reminder.getTanggal());
        Edittime.setText(reminder.getWaktu());
        Edittotal.setText(reminder.getTotal());
        Editnote.setText(reminder.getNote());
        if(reminder.getMonthly().equals("true")){
            setMonthly.setChecked(true);
        }
        posisiEdit = bundle.getInt("posisi");

    }
    private int getMakeID(Integer idRandom , DataReminder d, Integer indeks){
        if(idRandom != d.getId() ){
            if(indeks== data.size()-1){
                return idRandom;
            }else {
                getMakeID(idRandom,data.get(indeks+1),indeks+1);
            }


        }else{
            number = Rnumber.nextInt(1000) ;
            getMakeID(number,data.get(0),0) ;
        }
        return number ;
    }

    private void addReminder(){
        if(setMonthly.isChecked()){

            monthly = "true" ;
        }else if(!setMonthly.isChecked()){
            monthly ="false" ;
        }

        if(status.equals("update")){
            data.set(posisiEdit,new DataReminder(number,category, total, tanggal, waktu, note,monthly));
            Toast.makeText(getBaseContext(), "Berhasil Update", Toast.LENGTH_LONG).show();
            status = "" ;
            System.out.println(status);
        }else {
            System.out.println("save");
            Rnumber = new Random() ;
            number = Rnumber.nextInt(1000);

            System.out.println("panjang data : " + data.size());
            if(data.size() != 0){
                getMakeID(number, data.get(0),0) ;
            }
            data.add(new DataReminder(number,category, total, tanggal, waktu, note,monthly));

        }
        System.out.println("Id : " + number);

        if(setMonthly.isChecked()){

            alarmReceiver.setAlarm(new DataReminder(number,category, total, tanggal, waktu, note,monthly),getBaseContext());
        }else if(!setMonthly.isChecked()){
            alarmReceiver.setAlarm(new DataReminder(number,category, total, tanggal, waktu, note,monthly),getBaseContext());
        }

        save();
    }


//    private class NumberTextWatcher implements TextWatcher {
//        private final DecimalFormat df;
//        private final DecimalFormat dfnd;
//        private final EditText et;
//        private boolean hasFractionalPart;
//        private int trailingZeroCount;
//
//
//        public NumberTextWatcher(EditText addedttot, String s) {
//            df = new DecimalFormat(s);
//            df.setDecimalSeparatorAlwaysShown(true);
//            dfnd = new DecimalFormat("#,##.00");
//            this.et = addedttot;
//            hasFractionalPart = false;
//
//        }
//
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            et.removeTextChangedListener(this);
//
//            if (editable != null && !editable.toString().isEmpty()) {
//                try {
//                    int inilen, endlen;
//                    inilen = et.getText().length();
//                    String v = editable.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "").replace("$", "");
//                    Number n = df.parse(v);
//                    int cp = et.getSelectionStart();
//                    if (hasFractionalPart) {
//                        StringBuilder trailingZeros = new StringBuilder();
//                        while (trailingZeroCount-- > 0)
//                            trailingZeros.append('0');
//                        et.setText(df.format(n) + trailingZeros.toString());
//                    } else {
//                        et.setText(dfnd.format(n));
//                    }
//                    et.setText("Rp.".concat(et.getText().toString()));
//                    endlen = et.getText().length();
//                    int sel = (cp + (endlen - inilen));
//                    if (sel > 0 && sel < et.getText().length()) {
//                        et.setSelection(sel);
//                    } else if (trailingZeroCount > -1) {
//                        et.setSelection(et.getText().length() - 3);
//                    } else {
//                        et.setSelection(et.getText().length());
//                    }
//                } catch (NumberFormatException | ParseException e) {
//                    e.printStackTrace();
//
//
//                }
//            }
//            et.addTextChangedListener(this);
//
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            int index = charSequence.toString().indexOf(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()));
//            trailingZeroCount = 0;
//            if (index > -1) {
//                for (index++; index < charSequence.length(); index++) {
//                    if (charSequence.charAt(index) == '0')
//                        trailingZeroCount++;
//                    else {
//                        trailingZeroCount = 0;
//                    }
//                }
//                hasFractionalPart = true;
//            } else {
//                hasFractionalPart = false;
//            }
//
//        }
//
//
//    }



}
