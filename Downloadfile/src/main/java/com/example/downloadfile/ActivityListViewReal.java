package com.example.downloadfile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

public class ActivityListViewReal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        try {
            XmlPullParser parser = getResources().getXml(R.xml.rss_real);

            boolean isItem = false;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                final String TAG = "ЛогКот";
                String tmp = "";
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("item")) {
                    isItem = true;
                    parser.next();
                    continue;
                }

                if (parser.getEventType() == XmlPullParser.END_TAG
                        && parser.getName().equals("item")) {
                    isItem = false;
                    parser.next();
                    continue;
                }

                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("title")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "название = " + parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("link")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "ссылка = " + parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("description")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {

                    Log.d(TAG, "описание = " + getDescription(parser.getText()));
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("category")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "категория = " + parser.getText());
                }
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("pubDate")
                        && parser.next() == XmlPullParser.TEXT
                        && isItem) {
                    Log.d(TAG, "дата = " + parser.getText());
                }
                parser.next();
            }
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private String getDescription(String text) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.indexOf("<") >= 0 && sb.indexOf("<") >= 0) {
            sb.delete(sb.indexOf("<"), sb.indexOf(">")+1);
        }




        return sb.toString();
    }
}
