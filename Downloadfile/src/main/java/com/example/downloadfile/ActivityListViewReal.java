package com.example.downloadfile;

import android.app.Activity;
import android.content.ContentValues;
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
                    long rowID= database.insert("newsss", null, cv);
                    Log.d(TAG, "НОМЕР ЗАПИСИ = " + rowID);
                    isItem = false;
                    parser.next();
                    continue;
                }

                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("title")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "название = " + parser.getText());
                    cv.put("title", parser.getText());
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
                    cv.put("description", parser.getText().replaceAll("\\<.*?\\>", "").replaceAll("\n", " "));
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
                    cv.put("pubDate", parser.getText());
                }
                parser.next();
            }

        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
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
