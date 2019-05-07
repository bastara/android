package com.example.downloadfile;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "rssDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        doWork();
    }

    private void doWork() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GetData.getData("cp1251", "http://www.mobiledevice.ru/rss.xml");
            }
        });
        thread.start();
    }

    public void onClickLV(View view) {
        Intent listIntent = new Intent(this, ActivityListView.class);
        startActivity(listIntent);
    }

    public void onClickLVAdv(View view) {
        Intent listIntent = new Intent(this, ActivityListViewAdv.class);
        startActivity(listIntent);
    }

    public void onClickLVReal(View view) {
        Intent listIntent = new Intent(this, ActivityListViewReal.class);
        startActivity(listIntent);
    }

    public void onClickLogDB(View view) {
        Intent listIntent = new Intent(this, ActivityListLogDB.class);
        startActivity(listIntent);
    }
}
