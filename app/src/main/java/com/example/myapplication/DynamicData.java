package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DynamicData extends AsyncTask<String,Void,String> {
    PlaylistActivity myPlayListactivity;
    String token = "BQCCn3KauYaez9hu_Zsu_IoQ6Do0SEZsO3s1KUFi96YwOVbsrI8n3R9P63q9svsRYdEAW4V75F34-PX_GmaWLRAC1yqg28pvH5aFF6tW8v2dV2a3Dm3HMnU8kjadLX736-DYMBKYaprK5wYp6bByZIW3xN3zOpcaPyfGtuiJMcVINrDCskdsaY46cxhP12TgbrJI5K4Gk6RtwtDJcOzelV-yY6KQXYayBx5yIyNIY1NgrmqjXhzkNAnsL3CzPv7C6l3-uAKa0J9zNhE6eE7Q0OFkTa5Po5I4";
    public DynamicData(PlaylistActivity activity) {
        this.myPlayListactivity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //token
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization","Bearer "+ token);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Accept","application/json");

            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }

            BufferedReader buffrd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuffer ss = new StringBuffer();
            String line;
            while ((line = buffrd.readLine()) != null) {
                ss.append(line);
            }
            result = ss.toString();
            buffrd.close();
            conn.disconnect();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String data) {
        if (data != null) {
            Gson gson = new Gson();
// data => json object
            // data => data.items
            //JSONObject obj = new JSONObject(data);

            PlayListModel response = gson.fromJson(data, PlayListModel.class);

       myPlayListactivity.updateData(response.getTracks().getSongs());

        }
    }
//private static HttpURLConnection connection;
}
