package com.example.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public class OwnerTest implements LifecycleOwner {


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
