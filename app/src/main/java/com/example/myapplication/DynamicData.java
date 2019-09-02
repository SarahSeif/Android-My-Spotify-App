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
    String token = "BQCAhErziK3Kyju9CpKcAz1SiKQve6asCSypktNT1Wxbm3TGNFoO8AI6NSEQd12i6wjQvWyAaBxVR8grT3Uj02DFPAnYmwolLDwa6k4jixLHGNaQAdtDjRqLTtj0V0Um-C7YwYiYuz9f1IpfuIywPGyY2qioQkAjl0RLgBJKSydXIVOQiIRhTkwFA-k-a_o_N-aAfLsq4jqkWS3ve0_7lH4OFrExZ7nLgRw9oXEacQkqOgrrBbuzgSR1U9pPJE6oJ6q_ct352ndSFlnEGlpiB5bpmWrVUAcs";
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
            PlayListModel response = gson.fromJson(data, PlayListModel.class);

            for(int i = 0; i< response.getTracks().getSongs().size() ; i++) {
                if (response.getTracks().getSongs().get(i).getPreview_url() == null){
                    response.getTracks().getSongs().remove(i);
                }
            }

       myPlayListactivity.updateData(response.getTracks().getSongs());

        }
    }
}
