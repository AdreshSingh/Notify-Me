package com.example.notifyme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    String description = "Test notification";
    private static final String CHANNEL_ID = "MY_NOTICE";
    Notification.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =  findViewById(R.id.callMe);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, AlertDetailsActivity.class); // to open which activity after clicking NOTICE

        PendingIntent pendingIntent = PendingIntent.getService(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, description, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);

            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("My NOTICE")
                    .setContentTitle("i am giving notice ")
                    .setSmallIcon(R.drawable.message)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notice_me))
                    .setWhen(System.currentTimeMillis())
                    .setShowWhen(true)
                    .setContentIntent(pendingIntent);
        } else {

            builder = new Notification.Builder(this)
                    .setContentTitle("My NOTICE")
                    .setContentTitle("i am giving notice ")
                    .setSmallIcon(R.drawable.message)
                    .setWhen(System.currentTimeMillis())
                    .setShowWhen(true)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notice_me))
                    .setContentIntent(pendingIntent);
        }




        btn.setOnClickListener(view ->{

            // can't get this to work may be in future

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                Notification.Builder builder = new Notification.Builder(this,CHANNEL_ID)
//                        .setSmallIcon(R.drawable.ic_launcher_foreground)
//                        .setContentTitle("My NOTICE")
//                        .setContentTitle("i am giving notice ")
//                        .setPriority(Notification.PRIORITY_DEFAULT);
//
//            builder.build();
//            }
            notificationManager.notify(1234, builder.build());

        });
    }
}