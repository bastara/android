package com.example.downloadfile;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class GetData {
    private static final String TAG = "!RTE";

    static public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://google.comm"); //You can replace it with your name
            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    static public String getData(String _encoding, String _url) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(_url).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
              
            connection.setReadTimeout(3250);
            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                Log.d(TAG, "");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                int count = 0;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("/n");
                    count++;
                    Log.d(TAG, count + " " + sb.toString());
                }
                Log.d(TAG, " " + count);
                Log.d(TAG, " " + sb.toString());
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "Error4";
    }

    //TODO закрыть соединения.
}