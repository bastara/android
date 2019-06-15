package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int max = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create PendingIntent
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

// Create Notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher)
.setSmallIcon(R.drawable.ic_android)
.setContentTitle("Title")
.setContentText("Notification text")
.setAutoCancel(true)
.setContentIntent(resultPendingIntent);

        Notification notification = builder.build();

// Show Notification
        final NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);


// show notification with indeterminate progressbar
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("Some operation")
                .setContentText("Preparing")
                .setProgress(max, 0, true);

        notificationManager.notify(1, builder.build());


        final NotificationCompat.Builder finalBuilder = builder;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int progress = 0;

                while (progress < max) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress += 10;

                    // show notification with current progress
                    finalBuilder.setProgress(max, progress, false)
                                .setContentText(progress + " of " + max);
                    notificationManager.notify(1, finalBuilder.build());

                }

                // show notification without progressbar
                finalBuilder.setProgress(0, 10, false)
                            .setContentText("Completed");
                notificationManager.notify(1, finalBuilder.build());
            }
        }).start();


    }
}
