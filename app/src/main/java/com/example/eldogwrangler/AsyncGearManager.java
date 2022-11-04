package com.example.eldogwrangler;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class AsyncGearManager extends AsyncTask<String, Void, JSONArray> {
    String name;
    public AsyncGearManager(String cname) {
        name = cname;
    }
    @Override
    protected JSONArray doInBackground(String... strings) {
        JSONArray statReqs = new JSONArray();
        String gName = sortName(getName());
        statReqs = getStats(gName);
        return statReqs;
    }

    private String sortName(String string) {
        String initName = string;
        if (initName.contains(" ")) {
            initName.replaceAll(" ", "%20");
        }
        return initName;
    }
    private JSONArray getStats(String string) {
        JSONArray statReqs = new JSONArray();
        String name = string;
        String urlName;
        try {
            if (name.contains("Buckler")||name.contains("hield")||name.contains("Shell")||name.contains("Plate")) {
                urlName = "https://eldenring.fanapis.com/api/shields?name=";
            }
            else {
                urlName = "https://eldenring.fanapis.com/api/weapons?name=";
            }
            URL gUrl = new URL(urlName + name);
            HttpsURLConnection gConnection;
            gConnection = (HttpsURLConnection) gUrl.openConnection();
            gConnection.setRequestProperty("User-Agent", "build-planner-app");
            gConnection.setRequestMethod("GET");
            if(gConnection.getResponseCode()==200){
                String inline = "";
                Scanner scanner = new Scanner(gUrl.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                JSONArray gData = (JSONArray) data_obj.get("data");
                if(gData.size()==1) {
                    JSONObject thisItem =(JSONObject) gData.get(0);
                    statReqs = (JSONArray) thisItem.get("requiredAttributes");
                    return statReqs;
                }
                else {
                    for(int x=0; x<gData.size(); x++) {
                        JSONObject thisItem = (JSONObject) gData.get(x);
                        String thisName = (String) thisItem.get("name");
                        if(name.equals(thisName)) {
                            statReqs = (JSONArray) thisItem.get("requiredAttributes");
                            return statReqs;
                        }
                    }

                }
            }
            else {
                Log.i(TAG, "failure");
                gConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return statReqs;
    }
    private String getName() {
        return name;
    }
}