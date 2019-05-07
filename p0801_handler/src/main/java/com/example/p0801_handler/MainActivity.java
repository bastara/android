package com.example.p0801_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    TextView tvTest;
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.tvTest);

        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    void someMethod() {
        tvTest.setText("Count = " + cnt++);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onDestroy() {
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    static class MyHandler extends Handler {

        WeakReference<MainActivity> wrActivity;

        public MyHandler(MainActivity activity) {
            wrActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = wrActivity.get();
            if (activity != null)
                activity.someMethod();
        }
    }
}