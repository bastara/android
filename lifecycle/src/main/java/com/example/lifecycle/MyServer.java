package com.example.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyServer implements LifecycleObserver {

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void connect() {
//        Log.d("TAGG", "connect");
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    public void disconnect() {
//        Log.d("TAGG", "disconnect");
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void resumed() {
//        Log.d("TAGG", "resumed");
//    }


    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner source, Lifecycle.Event event) {
        Log.d("TAGG", "ANY " + event);
    }
}
