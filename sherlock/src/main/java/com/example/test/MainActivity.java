package com.example.test;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static final private int CHOOSE_THIEF = 0;

    public void onClick(View v) {
        Intent questionIntent = new Intent(MainActivity.this, SwitchList.class);
        startActivityForResult(questionIntent, CHOOSE_THIEF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView infoTextView = findViewById(R.id.textViewInfo);

        if (requestCode == CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                String thiefname = data.getStringExtra(SwitchList.THIEF);
                infoTextView.setText(thiefname);
            } else {
                infoTextView.setText(""); // стираем текст
            }
        }
    }

}
