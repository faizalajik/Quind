package com.quind.quind;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


import com.quind.quind.Model.DataReminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlarmReceiver extends BroadcastReceiver {
//    MediaPlayer player;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationInten = new Intent(context, MainActivity.class);
        notificationInten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(notificationInten);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);


        String note = intent.getStringExtra("note") ;
        System.out.println(note);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);
        Notification notification = nBuilder.setContentTitle("Alarm Bayar Premi")
                .setContentText(note)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
        Toast.makeText(context, "Alarm aktif!", Toast.LENGTH_LONG).show();
//        player = MediaPlayer.create(context, R.raw.alarm);
//        player.start();
    }

    public void setAlarm(DataReminder dataReminder, Context context) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String string = dataReminder.getTanggal() + " " + dataReminder.getWaktu();


        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = format.parse(string);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        System.out.println(calendar.getTimeInMillis());

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("note", dataReminder.getNote()) ;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, dataReminder.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(dataReminder.getMonthly().equals("false")){
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }else if (dataReminder.getMonthly().equals("true")){
            if (calendar.getTime().getMonth() == Calendar.JANUARY || calendar.getTime().getMonth() == Calendar.MARCH || calendar.getTime().getMonth() == Calendar.MAY || calendar.getTime().getMonth() == Calendar.JULY
                    || calendar.getTime().getMonth() == Calendar.AUGUST || calendar.getTime().getMonth() == Calendar.OCTOBER || calendar.getTime().getMonth() == Calendar.DECEMBER){
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 31, pendingIntent);
            }
            if (calendar.getTime().getMonth() == Calendar.APRIL || calendar.getTime().getMonth() == Calendar.JUNE || calendar.getTime().getMonth() == Calendar.SEPTEMBER
                    || calendar.getTime().getMonth() == Calendar.NOVEMBER){
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 30, pendingIntent);
            }


            if  (calendar.getTime().getMonth() == Calendar.FEBRUARY){//for feburary month)
                GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
                if(cal.isLeapYear(calendar.getTime().getYear())){//for leap year feburary month
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 29, pendingIntent);
                }
                else{ //for non leap year feburary month
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 28, pendingIntent);
                }
            }
        }

        //set ulang alarm ketika di restar
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


    }
    public void cancelAlarm(Context context, int ID) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        Intent intent = new Intent(context, AlarmReceiver.class) ;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }


}
