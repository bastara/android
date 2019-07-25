package com.example.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public class OwnerTest implements LifecycleOwner {

	LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

	@NonNull
	@Override
	public Lifecycle getLifecycle() {
		return lifecycleRegistry;
	}

//    https://habr.com/ru/post/332718/

	public void created() {
		lifecycleRegistry.markState(Lifecycle.State.STARTED);
	}

	public void resumed() {
		lifecycleRegistry.markState(Lifecycle.State.RESUMED);
	}



}
