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
    String token = "BQCRJYTiddJfqtt8sDKzIQRL3ep22q7yFtAFrtxpEFbd--WHxqO5qeb9f3KTotMzMBHDNN9D3BMK5HtxPllipCE4yIkQwii-Pq-1Gz3_N-JsEvCWCwC4Pu9PZwI9yeuBSOjX2yveylHc9YDTPqys9YsnPJwUFg4TmQeA3MiNyqPfgq0UsnORkTEL3ZfZ8xb25-_D_xtp1OpasN2WHMWVnf7ztBpMHi5NsxoGsK3XwHFRrxTC73Wju_v8zQynGyxXtruH1ka9FGunfp9Q1mV3NDQrjax3CS9n";
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
