package com.example.testparser;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String TAG = "ATOM-----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            doWork();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doWork() throws XmlPullParserException, IOException {
//        URL url = new URL(src);
//        InputStream inputStream = url.openConnection().getInputStream();

        ContentValues cv = new ContentValues();

        XmlPullParser parser = getResources().getXml(R.xml.atom1);

        boolean isEntry = false;
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("entry")) {
                isEntry = true;
                parser.next();
                continue;
            }


            if (parser.getEventType() == XmlPullParser.END_TAG
                    && parser.getName()
                             .equals("entry")) {
//                Log.d(TAG, "Вношу данные БД ");
                Log.d(TAG, "________________________________");
//                cv.put(COLUMN_URL, src);
//                if (cv.get(COLUMN_LINK_NEWS) != null) {
//                    long rowID = database.insert(TABLE_NEWS, null, cv);
//                    Log.d(TAG, "НОМЕР ЗАПИСИ = " + rowID);
//                    isEntry = false;
//                }
                parser.next();
                continue;
            }


            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("title")
                    && parser.next() == XmlPullParser.TEXT
                    && isEntry) {
                Log.d(TAG, "title      " + parser.getText());
//                cv.put(COLUMN_TITLE, parser.getText());
            }


            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .contains("link")


                    && isEntry) {

                for (int i = 0; i < parser.getAttributeCount(); i++) {
                    Log.d(TAG, "link      " + parser.getAttributeValue(i));
                    if (parser.getAttributeName(i)
                              .equals("href")) {
                        Log.d(TAG, "link!!!!!!!!!!!!      ");
                    }
                }

            }


            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .contains("link")

                    && isEntry) {
                String tmpStr = parser.getText();
//                Log.d(TAG, "link      " + parser.getText());

                // Проверкак обновления новостей
//                    if (tmpStr.equals("http://2tura.ru/2лл019/05/10/%d0%b2%d0%b8%d0%b4-%d0%bd%d0%b0-%d0%b4%d0%be%d0%bb%d0%b8%d0%bd%d1%83-%d0%bd%d0%be%d1%80%d0%b2%d0%b5%d0%b3%d0%b8%d1%8f/")) {
//                        tmpStr = null;
//                        cv.put(COLUMN_LINK_NEWS, tmpStr);
//                        continue;
//                    }
//                    cursor = database.query(TABLE_NEWS, null, COLUMN_LINK_NEWS + "=?", new String[]{tmpStr}, null, null, null);
//                cursor = dbRequest.getCursorCheckSite(tmpStr);
//                if (cursor.moveToFirst()) {
//                    Log.d(TAG, "Данная новость уже добавлена " + tmpStr);
//                    continue;
//                }
//                cv.put(COLUMN_LINK_NEWS, tmpStr);
            }

            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("summary")
                    && parser.next() == XmlPullParser.TEXT
                    && isEntry) {
//                cv.put(Contract.Entry.COLUMN_DESCRIPTION, parser.getText()
//                                                                .replaceAll("\\<.*?\\>", "")
//                                                                .replaceAll("\n", " "));
                Log.d(TAG, "summary   " + parser.getText()
                                                .replaceAll("\\<.*?\\>", "")
                                                .replaceAll("\n", " "));
            }

            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("content")
                    && parser.next() == XmlPullParser.TEXT
                    && isEntry) {
                Log.d(TAG, "content   " + parser.getText()
                                                .replaceAll("\\<.*?\\>", "")
                                                .replaceAll("\n", " "));
//                cv.put(COLUMN_DESCRIPTION, parser.getText().replaceAll("\\<.*?\\>", "").replaceAll("\n", " "));
            }
            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("category")
                    && isEntry) {
                Log.d(TAG, "category  " + parser.getAttributeValue(0));
//                cv.put(COLUMN_CATEGORY, parser.getAttributeValue(0));
            }
            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName()
                             .equals("updated")
                    && parser.next() == XmlPullParser.TEXT
                    && isEntry) {
                Log.d(TAG, "updated    " + parser.getText());
//                cv.put(COLUMN_PUB_DATE, parser.getText());

//                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd - hh:mm:ss");
//                    String result =  sdf.format(this.getPubDate());
            }
            parser.next();
        }
    }
}
