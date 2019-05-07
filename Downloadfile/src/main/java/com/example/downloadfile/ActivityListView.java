package com.example.downloadfile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();

        try {
            XmlPullParser parser = getResources().getXml(R.xml.rss);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("contact")) {
                    list.add(parser.getAttributeValue(0) + " "
                            + parser.getAttributeValue(1) + "\n"
                            + parser.getAttributeValue(2));
                }
                parser.next();
            }
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}