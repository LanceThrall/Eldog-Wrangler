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
public class AsyncGearGrabber extends AsyncTask<String, Void, JSONArray> {
    private String mode;
    public AsyncGearGrabber(String string) {
        mode = string;
    }
    @Override
    protected JSONArray doInBackground(String... string) {
    JSONArray wBlock = new JSONArray();
    wBlock = getWeapons(getMode());
    return wBlock;
    }

    JSONArray getWeapons(String mode) {
        JSONArray gear = new JSONArray();
        if(mode.contains("1")) {
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
        }
        if(mode.contains("2")) {
            for(int x= 0; x<=5; x++) {
                try {
                    URL wUrl = new URL("https://eldenring.fanapis.com/api/armors?limit=100&page=" + x);
                    HttpsURLConnection wConnection;
                    wConnection = (HttpsURLConnection) wUrl.openConnection();
                    wConnection.setRequestProperty("User-Agent", "build-planner-app");
                    wConnection.setRequestMethod("GET");
                    if (wConnection.getResponseCode() == 200) {
                        String inline = " ";
                        Scanner scanner = new Scanner(wUrl.openStream());
                        while (scanner.hasNext()) {
                            inline += scanner.nextLine();
                        }
                        scanner.close();
                        JSONParser parse = new JSONParser();
                        JSONObject data_obj = (JSONObject) parse.parse(inline);
                        JSONArray armors = (JSONArray) data_obj.get("data");
                        gear.addAll(armors);
                        wConnection.disconnect();
                        if(x==5) {
                            Log.i(TAG, "all armor combined");
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
        }
        if(mode.contains("3")) {
                try {
                    URL wUrl = new URL("https://eldenring.fanapis.com/api/talismans?limit=100");
                    HttpsURLConnection wConnection;
                    wConnection = (HttpsURLConnection) wUrl.openConnection();
                    wConnection.setRequestProperty("User-Agent", "build-planner-app");
                    wConnection.setRequestMethod("GET");
                    if (wConnection.getResponseCode() == 200) {
                        String inline = " ";
                        Scanner scanner = new Scanner(wUrl.openStream());
                        while (scanner.hasNext()) {
                            inline += scanner.nextLine();
                        }
                        scanner.close();
                        JSONParser parse = new JSONParser();
                        JSONObject data_obj = (JSONObject) parse.parse(inline);
                        JSONArray talismans = (JSONArray) data_obj.get("data");
                        gear.addAll(talismans);
                        wConnection.disconnect();
                        return gear;
                    } else {
                        Log.i(TAG, "failure");
                        wConnection.disconnect();
                    }
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        if(mode.contains("4")) {
            try {
                URL wUrl = new URL("https://eldenring.fanapis.com/api/sorceries?limit=100");
                HttpsURLConnection wConnection;
                wConnection = (HttpsURLConnection) wUrl.openConnection();
                wConnection.setRequestProperty("User-Agent", "build-planner-app");
                wConnection.setRequestMethod("GET");
                if (wConnection.getResponseCode() == 200) {
                    String inline = " ";
                    Scanner scanner = new Scanner(wUrl.openStream());
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    scanner.close();
                    JSONParser parse = new JSONParser();
                    JSONObject data_obj = (JSONObject) parse.parse(inline);
                    JSONArray spells = (JSONArray) data_obj.get("data");
                    gear.addAll(spells);
                    wConnection.disconnect();
                } else {
                    Log.i(TAG, "failure");
                    wConnection.disconnect();
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                URL iUrl = new URL("https://eldenring.fanapis.com/api/incantations?limit=100");
                HttpsURLConnection iConnection;
                iConnection = (HttpsURLConnection) iUrl.openConnection();
                iConnection.setRequestProperty("User-Agent", "build-planner-app");
                iConnection.setRequestMethod("GET");
                if (iConnection.getResponseCode() == 200) {
                    Scanner iScanner = new Scanner(iUrl.openStream());
                    String inline = " ";
                    while (iScanner.hasNext()) {
                        inline += iScanner.nextLine();
                    }
                    iScanner.close();
                    JSONParser iParse = new JSONParser();
                    JSONObject iData_obj = (JSONObject) iParse.parse(inline);
                    JSONArray incantations = (JSONArray) iData_obj.get("data");
                    gear.addAll(incantations);
                    iConnection.disconnect();
                    return gear;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return gear;
    }
    private String getMode() {
        return mode;
    }

}
