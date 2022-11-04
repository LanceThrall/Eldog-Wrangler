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
public class AsyncWeaponManager extends AsyncTask<String, Void, JSONArray> {
    @Override
    protected JSONArray doInBackground(String... mode) {
    JSONArray wBlock = new JSONArray();
    wBlock = getWeapons();
    return wBlock;
    }
    JSONArray getWeapons() {
        JSONArray gear = new JSONArray();
        for(int x= 0; x<=3; x++) {
            try {
                URL wUrl = new URL("https://eldenring.fanapis.com/api/weapons?limit=100&page=" + x);
                HttpsURLConnection wConnection;
                wConnection = (HttpsURLConnection) wUrl.openConnection();
                wConnection.setRequestProperty("User-Agent", "build-planner-app");
                wConnection.setRequestMethod("GET");
                if (wConnection.getResponseCode() == 200) {
                    Log.i(TAG, "success!");
                    String inline = "";
                    Scanner scanner = new Scanner(wUrl.openStream());
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    scanner.close();
                    JSONParser parse = new JSONParser();
                    JSONObject data_obj = (JSONObject) parse.parse(inline);
                    JSONArray weapons = (JSONArray) data_obj.get("data");
                    gear.addAll(weapons);
                    wConnection.disconnect();
                    if(x==3) {
                        try {
                            URL sUrl = new URL("https://eldenring.fanapis.com/api/shields?limit=100");
                            HttpsURLConnection sConnection;
                            sConnection = (HttpsURLConnection) sUrl.openConnection();
                            sConnection.setRequestProperty("User-Agent", "build-planner-app");
                            sConnection.setRequestMethod("GET");
                            if (sConnection.getResponseCode() == 200) {
                                String sInline = " ";
                                Scanner sScanner = new Scanner(sUrl.openStream());
                                while (sScanner.hasNext()) {
                                    sInline += sScanner.nextLine();
                                }
                                sScanner.close();
                                JSONParser sParse = new JSONParser();
                                JSONObject shield_obj = (JSONObject) sParse.parse(sInline);
                                JSONArray shields = (JSONArray) shield_obj.get("data");
                                gear.addAll(shields);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Log.i(TAG, "json succesfully got and combined");
                        return gear;
                    }
                }
                else {
                    Log.i(TAG, "failure");
                    wConnection.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return gear;
    }

}
