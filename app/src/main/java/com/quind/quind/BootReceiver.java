package com.quind.quind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quind.quind.Model.DataReminder;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BootReceiver extends BroadcastReceiver{
    private static ArrayList<DataReminder> data;
    private AlarmReceiver alarmReceiver = new AlarmReceiver() ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            loaddata(context);
            for (DataReminder d : data
                    ) {
                alarmReceiver.setAlarm(d, context);
            }
            }

        }
    }
    public void loaddata(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("datasave", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {
        }.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }
}
