package com.example.eldogwrangler;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
/*
public class AsyncApiGrabbler extends AsyncTask<Object, Void, Object> {
    protected JSONObject doInBackground(Object... params) {
        JSONObject rTest = new JSONObject();
        rTest = makeConnection();
        return rTest;
    }
    JSONObject makeConnection() {
        final String TAG = "Connection: ";
        final String RCode = "Responsecode";
        final String sValue = "Vigor";
        URL eldenringApi = null;
        try {
            eldenringApi = new URL("https://eldenring.fanapis.com/api/classes/17f69d71826l0i32gkm3ndn3kywxqj");
            HttpsURLConnection sConnection;
            sConnection = (HttpsURLConnection) eldenringApi.openConnection();
            sConnection.setRequestProperty("User-Agent", "build-planner-app");
            sConnection.setRequestMethod("GET");
            Log.i(RCode, String.valueOf(sConnection.getResponseCode()));
            if (sConnection.getResponseCode() == 200) {
                Log.i(TAG, "success!");
                String inline = "";
                Scanner scanner = new Scanner(eldenringApi.openStream());
                while(scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                JSONObject clss = (JSONObject) data_obj.get("data");
                JSONObject stat = (JSONObject) clss.get("stats");
                Log.i(sValue, (String) stat.get("vigor"));
                sConnection.disconnect();
                return clss;
            }
            else {
                Log.i(TAG, "failure");
                sConnection.disconnect();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
*/










