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
    public AsyncSpinnerFiller(JSONArray weaponBlock) {
        jName = weaponBlock;
        Log.i(TAG, "jName set equal to jSOn");
    }
    @Override
    protected String[] doInBackground(JSONArray... jName) {
        String[] spinnerList = new String[376];
        JSONArray names = getjName();
        Log.i(TAG, names.toJSONString());
        spinnerList[0] = " ";
        for(int x = 1; x<376; x++) {
            JSONObject thisName = (JSONObject) names.get(x);
            spinnerList[x] = (String) thisName.get("name");
            Log.i(TAG, spinnerList[x]);
        }
        return spinnerList;
    }
    private JSONArray getjName() {
        return jName;
    }
}
