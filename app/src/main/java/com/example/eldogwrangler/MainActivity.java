package com.example.eldogwrangler;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //app crash button
        final Button networkButton = findViewById(R.id.killButton);
        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int death = 1;
                death = death/0;
            }
        });

        //set class spinner
        Spinner spinner = (Spinner) findViewById(R.id.cspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //set weapon spinners
        JSONArray weaponBlock = new JSONArray();
        String[] spinnerList = new String[376];
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB/*HONEYCOMB = 11*/){
                weaponBlock = new AsyncWeaponManager().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                Log.i(TAG, weaponBlock.toJSONString());
                spinnerList = new AsyncSpinnerFiller(weaponBlock).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
            }
            else {
                weaponBlock = new AsyncWeaponManager().execute().get();
                spinnerList = new AsyncSpinnerFiller(weaponBlock).execute().get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Spinner wSpinnerR1 = (Spinner) findViewById(R.id.spinr1);
        Spinner wSpinnerR2 = (Spinner) findViewById(R.id.spinr2);
        Spinner wSpinnerR3 = (Spinner) findViewById(R.id.spinr3);
        Spinner wSpinnerL1 = (Spinner) findViewById(R.id.spinl1);
        Spinner wSpinnerL2 = (Spinner) findViewById(R.id.spinl2);
        Spinner wSpinnerL3 = (Spinner) findViewById(R.id.spinl3);
        ArrayAdapter<String> weaponSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        weaponSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wSpinnerR1.setAdapter(weaponSpinnerArray);
        wSpinnerR2.setAdapter(weaponSpinnerArray);
        wSpinnerR3.setAdapter(weaponSpinnerArray);
        wSpinnerL1.setAdapter(weaponSpinnerArray);
        wSpinnerL2.setAdapter(weaponSpinnerArray);
        wSpinnerL3.setAdapter(weaponSpinnerArray);
        wSpinnerR1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cname = (String) parent.getItemAtPosition(position);
        Log.i(TAG, cname);
        if(cname.contains("Vagabond")||cname.contains("Warrior")||cname.contains("Hero")||cname.contains("Bandit")||cname.contains("Astrologer")||cname.contains("prophet")||
                cname.contains("Samurai")||cname.contains("Confessor")||cname.contains("Prisoner")||cname.contains("Wretch")) {
            try {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB/*HONEYCOMB = 11*/){
                    JSONObject statBlock = new AsyncStatManager(cname).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                    setStats(statBlock);
                }
                else {
                    JSONObject statBlock = new AsyncStatManager(cname).execute().get();
                    setStats(statBlock);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            if(cname.equals(" ")) {
                Log.i(TAG, "nothing selected");
            }
            else {
                Log.i(TAG, "searching gear reqs");
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB/*HONEYCOMB = 11*/) {
                        JSONArray statReqs = new AsyncGearManager(cname).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                        setStatReqs(statReqs);
                    } else {
                        JSONArray statReqs = new AsyncGearManager(cname).execute().get();
                        setStatReqs(statReqs);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setStats(JSONObject statBlock) {
        int lVal = Integer.valueOf((String) statBlock.get("level"));
        Stat level = new Stat(lVal, "level");
        int vVal = Integer.valueOf((String) statBlock.get("vigor"));
        Stat vigor = new Stat(vVal, "vigor");
        int mVal = Integer.valueOf((String) statBlock.get("mind"));
        Stat mind = new Stat(mVal, "mind");
        int eVal = Integer.valueOf((String) statBlock.get("endurance"));
        Stat endurance = new Stat(eVal, "endurance");
        int sVal = Integer.valueOf((String) statBlock.get("strength"));
        Stat strength = new Stat(sVal, "strength");
        int dVal = Integer.valueOf((String) statBlock.get("dexterity"));
        Stat dexterity = new Stat(dVal, "dexterity");
        int iVal = Integer.valueOf((String) statBlock.get("intelligence"));
        Stat intelligence = new Stat(iVal, "intelligence");
        int fVal = Integer.valueOf((String) statBlock.get("faith"));
        Stat faith = new Stat(fVal, "faith");
        int aVal = Integer.valueOf((String) statBlock.get("arcane"));
        Stat arcane = new Stat(aVal, "arcane");
        displayStats(level, vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane);
        setStatButtons(level, vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane,
                lVal, vVal, mVal, eVal, sVal, dVal, iVal, fVal, aVal);
    }


    public void displayStats(Stat level, Stat vigor, Stat mind, Stat endurance, Stat strength, Stat dexterity,
                             Stat intelligence, Stat faith, Stat arcane) {
        final TextView vigorBox = findViewById(R.id.vigBox);
        vigorBox.setText(Integer.toString(vigor.getValue()));

        final TextView mindBox = findViewById(R.id.minBox);
        mindBox.setText(Integer.toString(mind.getValue()));

        final TextView enduranceBox = findViewById(R.id.endBox);
        enduranceBox.setText(Integer.toString(endurance.getValue()));

        final TextView strengthBox = findViewById(R.id.strBox);
        strengthBox.setText(Integer.toString(strength.getValue()));

        final TextView dexterityBox = findViewById(R.id.dexBox);
        dexterityBox.setText(Integer.toString(dexterity.getValue()));

        final TextView intelligenceBox = findViewById(R.id.intelBox);
        intelligenceBox.setText(Integer.toString(intelligence.getValue()));

        final TextView faithBox = findViewById(R.id.faiBox);
        faithBox.setText(Integer.toString(faith.getValue()));

        final TextView arcaneBox = findViewById(R.id.arcBox);
        arcaneBox.setText(Integer.toString(arcane.getValue()));

        final TextView levelBox = findViewById(R.id.level);
        levelBox.setText(Integer.toString(level.getValue()));
    }
    public void setStatButtons(Stat level, Stat vigor, Stat mind, Stat endurance, Stat strength, Stat dexterity,
                               Stat intelligence, Stat faith, Stat arcane, Integer lVal, Integer vVal, Integer mVal,
                               Integer eVal, Integer sVal,Integer dVal, Integer iVal, Integer fVal, Integer aVal) {
        final Button button = findViewById(R.id.Up1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vigor.increment();
                final TextView vigorBox = findViewById(R.id.vigBox);
                vigorBox.setText(Integer.toString(vigor.getValue()));
                if(vigor.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button2 = findViewById(R.id.Down1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vigor.getValue() > vVal) {
                    vigor.decrement();
                    level.decrement();
                }
                final TextView vigorBox = findViewById(R.id.vigBox);
                vigorBox.setText(Integer.toString(vigor.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button3 = findViewById(R.id.Up2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mind.increment();
                final TextView mindBox = findViewById(R.id.minBox);
                mindBox.setText(Integer.toString(mind.getValue()));
                if(mind.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button4 = findViewById(R.id.Down2);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mind.getValue() > mVal) {
                    mind.decrement();
                    level.decrement();
                }
                final TextView mindBox = findViewById(R.id.minBox);
                mindBox.setText(Integer.toString(mind.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button5 = findViewById(R.id.Up3);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endurance.increment();
                final TextView enduranceBox = findViewById(R.id.endBox);
                enduranceBox.setText(Integer.toString(endurance.getValue()));
                if(endurance.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button6 = findViewById(R.id.Down3);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(endurance.getValue() > eVal) {
                    endurance.decrement();
                    level.decrement();
                }
                final TextView enduranceBox = findViewById(R.id.endBox);
                enduranceBox.setText(Integer.toString(endurance.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button7 = findViewById(R.id.Up4);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strength.increment();
                final TextView strengthBox = findViewById(R.id.strBox);
                strengthBox.setText(Integer.toString(strength.getValue()));
                if(strength.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button8 = findViewById(R.id.Down4);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strength.getValue() > sVal) {
                    strength.decrement();
                    level.decrement();
                }
                final TextView strengthBox = findViewById(R.id.strBox);
                strengthBox.setText(Integer.toString(strength.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button9 = findViewById(R.id.Up5);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dexterity.increment();
                final TextView dexterityBox = findViewById(R.id.dexBox);
                dexterityBox.setText(Integer.toString(dexterity.getValue()));
                if(dexterity.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button10 = findViewById(R.id.Down5);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dexterity.getValue() > dVal) {
                    dexterity.decrement();
                    level.decrement();
                }
                final TextView dexterityBox = findViewById(R.id.dexBox);
                dexterityBox.setText(Integer.toString(dexterity.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button11 = findViewById(R.id.Up6);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intelligence.increment();
                final TextView intelligenceBox = findViewById(R.id.intelBox);
                intelligenceBox.setText(Integer.toString(intelligence.getValue()));
                if(intelligence.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button12 = findViewById(R.id.Down6);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intelligence.getValue() > iVal) {
                    intelligence.decrement();
                    level.decrement();
                }
                final TextView intelligenceBox = findViewById(R.id.intelBox);
                intelligenceBox.setText(Integer.toString(intelligence.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button13 = findViewById(R.id.Up7);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faith.increment();
                final TextView faithBox = findViewById(R.id.faiBox);
                faithBox.setText(Integer.toString(faith.getValue()));
                if(faith.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button14 = findViewById(R.id.Down7);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(faith.getValue() > fVal) {
                    faith.decrement();
                    level.decrement();
                }
                final TextView faithBox = findViewById(R.id.faiBox);
                faithBox.setText(Integer.toString(faith.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
        final Button button15 = findViewById(R.id.Up8);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arcane.increment();
                final TextView arcaneBox = findViewById(R.id.arcBox);
                arcaneBox.setText(Integer.toString(arcane.getValue()));
                if(arcane.getValue()<99) {
                    level.increment();
                    final TextView levelBox = findViewById(R.id.level);
                    levelBox.setText(Integer.toString(level.getValue()));
                }
            }
        });
        final Button button16 = findViewById(R.id.Down8);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arcane.getValue() > aVal) {
                    arcane.decrement();
                    level.decrement();
                }
                final TextView arcaneBox = findViewById(R.id.arcBox);
                arcaneBox.setText(Integer.toString(arcane.getValue()));
                final TextView levelBox = findViewById(R.id.level);
                levelBox.setText(Integer.toString(level.getValue()));
            }
        });
    }


    public void setStatReqs(JSONArray statReqs) {
        Log.i(TAG, "Setting Stat Reqs");
        final TextView strR1 = findViewById(R.id.strR1);
        final TextView dexR1 = findViewById(R.id.dexR1);
        final TextView intelR1 = findViewById(R.id.intelR1);
        final TextView faiR1 = findViewById(R.id.faiR1);
        final TextView arcR1 = findViewById(R.id.arcR1);
        for(int x=0; x<statReqs.size(); x++) {
            JSONObject thisReq = (JSONObject) statReqs.get(x);
            if(thisReq.get("name").equals("Str")) {
                strR1.setText(String.valueOf(thisReq.get("amount")));
            }
            if(thisReq.get("name").equals("Dex")) {
                dexR1.setText(String.valueOf(thisReq.get("amount")));
            }
            if(thisReq.get("name").equals("Int")) {
                intelR1.setText(String.valueOf(thisReq.get("amount")));
            }

            //some api entries have int with a blank value and an unnamed requirement with the int value
            if(thisReq.get("name").equals(" ")) {
                intelR1.setText(String.valueOf(thisReq.get("amount")));
            }

            if(thisReq.get("name").equals("Fai")) {
                faiR1.setText(String.valueOf(thisReq.get("amount")));
            }
            if(thisReq.get("name").equals("Arc")) {
                arcR1.setText(String.valueOf(thisReq.get("amount")));
            }
        }
    }

}


