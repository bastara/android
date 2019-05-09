package com.example.workmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MyWorker.TAG, "onCreate " + Thread.currentThread().getName());
        doWork();
    }

    public void doWork() {
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.MINUTES, 5, TimeUnit.MINUTES).build();
        WorkManager.getInstance().enqueueUniquePeriodicWork("Refresh News", ExistingPeriodicWorkPolicy.REPLACE, request);
        LiveData<WorkInfo> info = WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId());
        info.observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                Log.d(MyWorker.TAG, "onChanged " + Thread.currentThread().getName() + " " + workInfo.getState());
            }
        });
    }
}


