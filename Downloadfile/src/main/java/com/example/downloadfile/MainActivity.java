package com.example.downloadfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "!REFRESH TIME";

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



}
