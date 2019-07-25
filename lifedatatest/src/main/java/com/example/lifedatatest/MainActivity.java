package com.example.lifedatatest;

import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {

	TextView textView;

	private DataController dataController = DataController.getInstance();
//	private DataController dataController = new DataController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = findViewById(R.id.textTest);

		dataController.setValue();

		LiveData<String> liveData = DataController.getInstance().getData();
//		LiveData<String> liveData = dataController.getData();

		liveData.observe(this, new Observer<String>() {
			@Override
			public void onChanged(@Nullable String value) {
				textView.setText(value);
			}
		});

		dataController.runCycle ();
	}
}
