package com.example.ks.astrolabe1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class GetHTTP extends AsyncTask<String, Void, String> {
    final String TAG = "MyLog";

    StringBuilder buf = new StringBuilder();
    StringBuilder response = new StringBuilder();
    @Override
    protected String doInBackground(String... uriPaths) {
        BufferedReader reader = null;
        try {

            String urlStr = "http://84.204.30.84:56565/rest/" + uriPaths[0];
            URL url = new URL(urlStr);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("POST");
            c.setReadTimeout(10000);
            c.connect();

            reader = new BufferedReader(new InputStreamReader(c.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf.toString();
//      TODO  if (reader != null) {
    }
}
