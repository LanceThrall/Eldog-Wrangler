package com.example.eldogwrangler;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class AsyncSpinnerFiller extends AsyncTask<JSONArray, Void, String[]> {
    JSONArray jName;
    String mode;
    public AsyncSpinnerFiller(JSONArray jsonArray, String string) {
        jName = jsonArray;
        mode = string;
    }
    @Override
    protected String[] doInBackground(JSONArray... jName) {

        String thisMode = getMode();
        if(thisMode.contains("1")) {
            return makeWeaponList();
        }
        if(thisMode.contains("2")) {
            return makeArmorList();
        }
        if(thisMode.contains("3")) {
            return makeHelmList();
        }
        if(thisMode.contains("4")) {
            return makeGauntList();
        }
        if(thisMode.contains("5")) {
            return makeLegList();
        }
        return makeNoList();
    }
    private String[] makeWeaponList() {
        String[] spinnerList = new String[377];
        JSONArray names = getjName();
        spinnerList[0] = " ";
        for(int x = 1; x<377; x++) {
            JSONObject thisName = (JSONObject) names.get(x-1);
            spinnerList[x] = (String) thisName.get("name");
        }
        return spinnerList;
    }
    private String[] makeArmorList() {
        String[] spinnerList = new String[204];
        JSONArray names = getjName();
        spinnerList[0] = " ";
        int armorCount = 0;
        for(int x = 1; x<569; x++) {
            JSONObject thisName = (JSONObject) names.get(x-1);
            String typeCheck = (String) thisName.get("category");
            if(typeCheck.contains("Chest Armor")) {
                spinnerList[armorCount+1] = (String) thisName.get("name");
                armorCount++;
            }
        }
        return spinnerList;
    }
    private String[] makeHelmList() {
        String[] spinnerList = new String[168];
        JSONArray names = getjName();
        spinnerList[0] = " ";
        int armorCount = 0;
        for(int x = 1; x<569; x++) {
            JSONObject thisName = (JSONObject) names.get(x-1);
            String typeCheck = (String) thisName.get("category");
            if(typeCheck.contains("Helm")) {
                spinnerList[armorCount+1] = (String) thisName.get("name");
                armorCount++;
            }
        }
        return spinnerList;
    }
    private String[] makeGauntList() {
        String[] spinnerList = new String[94];
        JSONArray names = getjName();
        spinnerList[0] = " ";
        int armorCount = 0;
        for(int x = 1; x<569; x++) {
            JSONObject thisName = (JSONObject) names.get(x-1);
            String typeCheck = (String) thisName.get("category");
            if(typeCheck.contains("Gauntlets")) {
                spinnerList[armorCount+1] = (String) thisName.get("name");
                armorCount++;
            }
        }
        return spinnerList;
    }
    private String[] makeLegList() {
        String[] spinnerList = new String[105];
        JSONArray names = getjName();
        spinnerList[0] = " ";
        int armorCount = 0;
        for(int x = 1; x<569; x++) {
            JSONObject thisName = (JSONObject) names.get(x-1);
            String typeCheck = (String) thisName.get("category");
            if(typeCheck.contains("Leg Armor")) {
                spinnerList[armorCount+1] = (String) thisName.get("name");
                armorCount++;
            }
        }
        return spinnerList;
    }

    private String[] makeNoList() {
        String[] noList = new String[1];
        return noList;
    }
    private JSONArray getjName() {
        return jName;
    }
    private String getMode() { return mode;}
}
