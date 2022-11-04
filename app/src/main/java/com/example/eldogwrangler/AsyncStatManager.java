package com.example.eldogwrangler;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class AsyncStatManager extends AsyncTask<String, Void, JSONObject> {

    private String name;

    public AsyncStatManager(String cname) {
        name = cname;

    }

    protected JSONObject doInBackground(String... cname) {
        JSONObject statBlock = new JSONObject();
        Log.i(TAG, "doinbackground");
        String keyName = getName();
        Log.i(TAG, keyName);
        statBlock = findStats(keyName);
        return statBlock;
    }
    JSONObject findStats(String keyName) {
        Log.i(TAG, "findstats running");
        JSONObject result = new JSONObject();
        try {
            URL classUrl = null;
            if (keyName.contains("Vagabond")) {
                Log.i(TAG, "v selected");
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69db959fl0i32hhpwdbjko0bj57");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Warrior")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69b2dd76l0i32gljr3f62pkzhjo");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Hero")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69d71826l0i32gkm3ndn3kywxqj");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Bandit")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69d43011l0i32gojnjjs9mcg0k");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Astrologer")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69874f7bl0i32gmqaffmbfral8f");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Prophet")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69932fefl0i32hlnekz682ixnvc");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Samurai")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f699f7f4cl0i32huaj53vkdxeh7b");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Prisoner")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69d1f5f1l0i32grxfonsq64huhl");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Confessor")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69774a83l0i32hjagb1nwcs1hne");
                result = makeConnection(classUrl);
                return result;
            }
            if (keyName.contains("Wretch")) {
                classUrl = new URL("https://eldenring.fanapis.com/api/classes/17f69d4ac46l0i32hju3peo5nijzbj");
                result = makeConnection(classUrl);
                return result;
            } else {
                return null;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    JSONObject makeConnection(URL classUrl) {
        JSONObject clss = new JSONObject();
           try {
               HttpsURLConnection cConnection;
               cConnection = (HttpsURLConnection) classUrl.openConnection();
               cConnection.setRequestProperty("User-Agent", "build-planner-app");
               cConnection.setRequestMethod("GET");
               if (cConnection.getResponseCode() == 200) {
                   Log.i(TAG, "success!");
                   String inline = "";
                   Scanner scanner = new Scanner(classUrl.openStream());
                   while (scanner.hasNext()) {
                       inline += scanner.nextLine();
                   }
                   scanner.close();
                   JSONParser parse = new JSONParser();
                   JSONObject data_obj = (JSONObject) parse.parse(inline);
                   JSONObject bigCLss = (JSONObject) data_obj.get("data");
                   clss = (JSONObject) bigCLss.get("stats");
                   Log.i(TAG, (String) clss.get("vigor"));
                   cConnection.disconnect();
                   return clss;
               }
               else {
                   Log.i(TAG, "failure");
                   cConnection.disconnect();
               }
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException | ParseException e) {
               e.printStackTrace();
           }
        return null;
    }
    private String getName() {
        return name;
    }


}
