package com.example.downloadfile;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class ActivityListLogDB extends Activity {

    private final String TAG = "ЛогКот";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Log.d(TAG, "--- Rows in newsTable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = database.query("newsss", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int linkColIndex = c.getColumnIndex("link");
            int descriptionColIndex = c.getColumnIndex("description");
            int categoryColIndex = c.getColumnIndex("category");
            int pubDateColIndex = c.getColumnIndex("pubDate");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", title = " + c.getString(titleColIndex)+
                                ", link = " + c.getString(linkColIndex)+
                                ", description = " + c.getString(descriptionColIndex)+
                                ", category = " + c.getString(categoryColIndex)+
                                ", pubDate = " + c.getString(pubDateColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        c.close();

    }
}
