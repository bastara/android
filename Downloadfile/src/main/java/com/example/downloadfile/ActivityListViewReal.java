package com.example.downloadfile;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

public class ActivityListViewReal extends Activity {
    private final String TAG = "ЛогКот";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();

        try {
            XmlPullParser parser = getResources().getXml(R.xml.rss_real);

            boolean isItem = false;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("item")) {
                    isItem = true;
                    parser.next();
                    continue;
                }

                if (parser.getEventType() == XmlPullParser.END_TAG
                        && parser.getName().equals("item")) {
                    Log.d(TAG, "Вношу данные БД ");
                    database.insert(DBHelper.NEWS_TABLE, null, cv);
                    isItem = false;
                    parser.next();
                    continue;
                }

                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("title")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "название = " + parser.getText());
                    cv.put("tilte", parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("link")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "ссылка = " + parser.getText());
                    cv.put("link", parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("description")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
//                    Log.d(TAG, "описание = " + getDescription(parser.getText()));
                    Log.d(TAG, "описание = " + parser.getText().replaceAll("\\<.*?\\>", "").replaceAll("\n", " "));
                    cv.put("description", parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("category")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "категория = " + parser.getText());
                    cv.put("category", parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("pubDate")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "дата = " + parser.getText());
                    cv.put("pubData", parser.getText());
                }
                parser.next();
            }

        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
//        Cursor cursor = database.query(DBHelper.NEWS_TABLE, null, null, null, null, null, null);
//        if (!cursor.moveToFirst()) {
//
//            // определяем номера столбцов по имени в выборке
//            int idColIndex = cursor.getColumnIndex("id");
//            int titleColIndex = cursor.getColumnIndex("title");
//            int linkColIndex = cursor.getColumnIndex("link");
//            int descriptionColIndex = cursor.getColumnIndex("description");
//            int categoryColIndex = cursor.getColumnIndex("category");
//            int pubDateColIndex = cursor.getColumnIndex("pubDate");
//
//            do {
//                // получаем значения по номерам столбцов и пишем все в лог
//                Log.d(TAG,
//                        "ID = " + cursor.getInt(idColIndex) +
//                                ", title = " + cursor.getString(titleColIndex) +
//                                ", link = " + cursor.getString(linkColIndex) +
//                                ", description  = " + cursor.getString(descriptionColIndex) +
//                                ", category  = " + cursor.getString(categoryColIndex) +
//                                ", pubDate = " + cursor.getString(pubDateColIndex));
//                // переход на следующую строку
//                // а если следующей нет (текущая - последняя), то false - выходим из цикла
//            } while (cursor.moveToNext());
//        } else
//            Log.d(TAG, "0 rows");
//        //ВСЕГДА ЗАКРЫАТЬ!!!
//        cursor.close();
        dbHelper.close();
    }

    private String getDescription(String text) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.indexOf("<") >= 0 && sb.indexOf("<") >= 0) {
            sb.delete(sb.indexOf("<"), sb.indexOf(">") + 1);
        }
        return sb.toString();
    }
}
