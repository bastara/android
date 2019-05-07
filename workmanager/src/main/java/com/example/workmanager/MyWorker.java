package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ExampleWorker extends Worker {

    private static String TAG = "ExampleWorker";

    public static OneTimeWorkRequest createRequest() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();

        return new OneTimeWorkRequest.Builder(ExampleWorker.class)
                .setConstraints(constraints)
                .build();
    }

    public ExampleWorker(@NonNull Context context,
                         @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "start work");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException ex) {
            Log.d(TAG, "end work with failure");
            return Result.failure();
        }
        Log.d(TAG, "end work with success");
        return Result.success();
    }
}
