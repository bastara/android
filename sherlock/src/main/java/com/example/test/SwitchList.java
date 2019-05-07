package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SwitchList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

    }

    public final static String THIEF = "com.example.sherlock.THIEF";

    public void onRadioClick(View v) {
        Intent answerIntent = new Intent();

        switch (v.getId()) {
            case R.id.radioDog:
                answerIntent.putExtra(THIEF, "Сраный пёсик");
                break;
            case R.id.radioCrow:
                answerIntent.putExtra(THIEF, "Ворона");
                break;
            case R.id.radioCat:
                answerIntent.putExtra(THIEF, "Лошадь Пржевальского");
                break;

            default:
                break;
        }

        setResult(RESULT_OK, answerIntent);
        finish();
    }
}
