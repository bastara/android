package com.example.lifecycle;

import android.os.Bundle;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyServer myServer = new MyServer();
//
//        getLifecycle().addObserver(myServer);

		OwnerTest ownerTest = new OwnerTest();

		ownerTest.getLifecycle().addObserver(myServer);

		ownerTest.created();
		Log.d("TAGGG", String.valueOf(ownerTest.lifecycleRegistry.getCurrentState()));
//
		ownerTest.resumed();
	}
}
