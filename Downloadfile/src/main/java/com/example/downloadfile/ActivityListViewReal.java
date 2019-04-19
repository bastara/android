package com.example.downloadfile;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
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

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                final String TAG = "ЛогКот";
                String tmp = "";

                switch (parser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "Начало документа");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        if (parser.getDepth() != 14) {
                            break;
                        }
                        Log.d(TAG,
                                "START_TAG: имя тега = " + parser.getName()
                                        + ", уровень = " + parser.getDepth()
                                        + ", число атрибутов = "
                                        + parser.getAttributeCount());
                        tmp = "";
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            tmp = tmp + parser.getAttributeName(i) + " = "
                                    + parser.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.d(TAG, "Атрибуты: " + tmp);
                        break;
                    // конец тега
                    case XmlPullParser.END_TAG:
                        if (parser.getDepth() != 14) {
                            break;
                        }
                        Log.d(TAG, "END_TAG: имя тега = " + parser.getName());
                        break;
                    // содержимое тега
                    case XmlPullParser.TEXT:
                        if (parser.getDepth() != 4) {
                            break;
                        }
                        Log.d(TAG, "текст = " + parser.getText());
                        break;

                    default:
                        break;
                }
                parser.next();
            }
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
