package com.example.eldogwrangler;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set class spinner
        Spinner spinner = (Spinner) findViewById(R.id.cspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //set spinners
        JSONArray weaponBlock = new JSONArray();
        String[] spinnerList = new String[377];

        JSONArray armorBlock = new JSONArray();
        String[] armSpinnerList = new String[204];
        String[] helmSpinnerList = new String[168];
        String[] gauntSpinnerList = new String[94];
        String[] legSpinnerList = new String[105];

        JSONArray talismanBlock = new JSONArray();
        String[] talSpinnerList = new String[88];

        JSONArray spellBlock = new JSONArray();
        String[] spelSpinnerList = new String [170];
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB/*HONEYCOMB = 11*/){
                //get weapon list
                weaponBlock = new AsyncGearGrabber("1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                spinnerList = new AsyncSpinnerFiller(weaponBlock, "1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();

                //get armor list
                armorBlock = new AsyncGearGrabber("2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                armSpinnerList = new AsyncSpinnerFiller(armorBlock, "2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                helmSpinnerList = new AsyncSpinnerFiller(armorBlock, "3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                gauntSpinnerList = new AsyncSpinnerFiller(armorBlock, "4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                legSpinnerList = new AsyncSpinnerFiller(armorBlock, "5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();

                //get talisman list
                talismanBlock = new AsyncGearGrabber("3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                talSpinnerList = new AsyncSpinnerFiller(talismanBlock, "6").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();

                //get spell list
                spellBlock = new AsyncGearGrabber("4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                spelSpinnerList = new AsyncSpinnerFiller(spellBlock, "7").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
            }
            else {
                weaponBlock = new AsyncGearGrabber("1").execute().get();
                spinnerList = new AsyncSpinnerFiller(weaponBlock, "1").execute().get();
                armorBlock = new AsyncGearGrabber("2").execute().get();
                armSpinnerList = new AsyncSpinnerFiller(armorBlock, "2").execute().get();
                helmSpinnerList = new AsyncSpinnerFiller(armorBlock, "3").execute().get();
                gauntSpinnerList = new AsyncSpinnerFiller(armorBlock, "4").execute().get();
                legSpinnerList = new AsyncSpinnerFiller(armorBlock, "5").execute().get();
                talismanBlock = new AsyncGearGrabber("3").execute().get();
                talSpinnerList = new AsyncSpinnerFiller(talismanBlock, "6").execute().get();
                spellBlock = new AsyncGearGrabber("4").execute().get();
                spelSpinnerList = new AsyncSpinnerFiller(spellBlock, "7").execute().get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //set weapon spinners
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
        wSpinnerR2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        wSpinnerR3.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        wSpinnerL1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        wSpinnerL2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        wSpinnerL3.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //set armor spinners
        Spinner aSpinner1 = (Spinner) findViewById(R.id.spina1);
        Spinner aSpinner2 = (Spinner) findViewById(R.id.spina2);
        Spinner aSpinner3 = (Spinner) findViewById(R.id.spina3);
        Spinner aSpinner4 = (Spinner) findViewById(R.id.spina4);
        ArrayAdapter<String> armorSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, armSpinnerList);
        armorSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> helmetSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, helmSpinnerList);
        helmetSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> gauntSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gauntSpinnerList);
        gauntSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> legSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, legSpinnerList);
        legSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpinner1.setAdapter(helmetSpinnerArray);
        aSpinner2.setAdapter(armorSpinnerArray);
        aSpinner3.setAdapter(gauntSpinnerArray);
        aSpinner4.setAdapter(legSpinnerArray);

        //set talisman spinners
        Spinner tSpinner1 = (Spinner) findViewById(R.id.tspin1);
        Spinner tSpinner2 = (Spinner) findViewById(R.id.tspin2);
        Spinner tSpinner3 = (Spinner) findViewById(R.id.tspin3);
        Spinner tSpinner4 = (Spinner) findViewById(R.id.tspin4);
        ArrayAdapter<String> talismanSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, talSpinnerList);
        talismanSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tSpinner1.setAdapter(talismanSpinnerArray);
        tSpinner2.setAdapter(talismanSpinnerArray);
        tSpinner3.setAdapter(talismanSpinnerArray);
        tSpinner4.setAdapter(talismanSpinnerArray);

        //set spell spinners
        Spinner sSpinner1 = (Spinner) findViewById(R.id.sspin1);
        Spinner sSpinner2 = (Spinner) findViewById(R.id.sspin2);
        Spinner sSpinner3 = (Spinner) findViewById(R.id.sspin3);
        Spinner sSpinner4 = (Spinner) findViewById(R.id.sspin4);
        Spinner sSpinner5 = (Spinner) findViewById(R.id.sspin5);
        Spinner sSpinner6 = (Spinner) findViewById(R.id.sspin6);
        Spinner sSpinner7 = (Spinner) findViewById(R.id.sspin7);
        Spinner sSpinner8 = (Spinner) findViewById(R.id.sspin8);
        Spinner sSpinner9 = (Spinner) findViewById(R.id.sspin9);
        Spinner sSpinner10 = (Spinner) findViewById(R.id.sspin10);
        ArrayAdapter<String> spellSpinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spelSpinnerList);
        spellSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpinner1.setAdapter(spellSpinnerArray);
        sSpinner2.setAdapter(spellSpinnerArray);
        sSpinner3.setAdapter(spellSpinnerArray);
        sSpinner4.setAdapter(spellSpinnerArray);
        sSpinner5.setAdapter(spellSpinnerArray);
        sSpinner6.setAdapter(spellSpinnerArray);
        sSpinner7.setAdapter(spellSpinnerArray);
        sSpinner8.setAdapter(spellSpinnerArray);
        sSpinner9.setAdapter(spellSpinnerArray);
        sSpinner10.setAdapter(spellSpinnerArray);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cname = (String) parent.getItemAtPosition(position);
        Log.i(TAG, cname);
        Log.i(TAG, String.valueOf(parent));
        //set spinId so stat reqs can display to correct textview
        int spinID = 0;
        String pId = String.valueOf(parent);
        if(pId.contains("spinr1")) {
            spinID = 1;
        }
        if(pId.contains("spinr2")) {
            spinID = 2;
        }
        if(pId.contains("spinr3")) {
            spinID = 3;
        }
        if(pId.contains("spinl1")) {
            spinID = 4;
        }
        if(pId.contains("spinl2")) {
            spinID = 5;
        }
        if(pId.contains("spinl3")) {
            spinID = 6;
        }

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
                JSONArray emptyStatReq = null;
                setStatReqs(emptyStatReq, spinID);
            }
            else {
                Log.i(TAG, "searching gear reqs");
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB/*HONEYCOMB = 11*/) {
                        JSONArray statReqs = new AsyncGearManager(cname).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            setStatReqs(statReqs, spinID);
                        }
                    } else {
                        JSONArray statReqs = new AsyncGearManager(cname).execute().get();
                        setStatReqs(statReqs, spinID);
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
                if(vigor.getValue()<99) {
                    vigor.increment();
                    final TextView vigorBox = findViewById(R.id.vigBox);
                    vigorBox.setText(Integer.toString(vigor.getValue()));
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
                if(mind.getValue()<99) {
                    mind.increment();
                    final TextView mindBox = findViewById(R.id.minBox);
                    mindBox.setText(Integer.toString(mind.getValue()));
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
                if(endurance.getValue()<99) {
                    endurance.increment();
                    final TextView enduranceBox = findViewById(R.id.endBox);
                    enduranceBox.setText(Integer.toString(endurance.getValue()));
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
                if(strength.getValue()<99) {
                    strength.increment();
                    final TextView strengthBox = findViewById(R.id.strBox);
                    strengthBox.setText(Integer.toString(strength.getValue()));
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
                if(dexterity.getValue()<99) {
                    dexterity.increment();
                    final TextView dexterityBox = findViewById(R.id.dexBox);
                    dexterityBox.setText(Integer.toString(dexterity.getValue()));
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
                if(intelligence.getValue()<99) {
                    intelligence.increment();
                    final TextView intelligenceBox = findViewById(R.id.intelBox);
                    intelligenceBox.setText(Integer.toString(intelligence.getValue()));
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
                if(faith.getValue()<99) {
                    faith.increment();
                    final TextView faithBox = findViewById(R.id.faiBox);
                    faithBox.setText(Integer.toString(faith.getValue()));
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
                if(arcane.getValue()<99) {
                    arcane.increment();
                    final TextView arcaneBox = findViewById(R.id.arcBox);
                    arcaneBox.setText(Integer.toString(arcane.getValue()));
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

    public void setStatReqs(JSONArray statReqs, Integer spinID) {
        Log.i(TAG, "Setting Stat Reqs");
        TextView strR1 = null;
        if(spinID==1) {
             strR1 = findViewById(R.id.strR1);
        }
        if(spinID==2) {
            strR1 = findViewById(R.id.strR2);
        }
        if(spinID==3) {
            strR1 = findViewById(R.id.strR3);
        }
        if(spinID==4) {
            strR1 = findViewById(R.id.strL1);
        }
        if(spinID==5) {
            strR1 = findViewById(R.id.strL2);
        }
        if(spinID==6) {
            strR1 = findViewById(R.id.strL3);
        }
        strR1.setText(" ");
        String reqs = null;
        if (statReqs == null) {
            strR1.setText(" ");
        }
        else {
            for (int x = 0; x < statReqs.size(); x++) {
                if (x == 0) {
                    reqs = " ";
                }
                JSONObject thisReq = (JSONObject) statReqs.get(x);
                if (thisReq.get("name").equals("Str")) {
                    reqs = reqs + " Str: " + String.valueOf(thisReq.get("amount"));
                    strR1.setText(reqs);
                }
                if (thisReq.get("name").equals("Dex")) {
                    reqs = reqs + " Dex: " + String.valueOf(thisReq.get("amount"));
                    strR1.setText(reqs);
                }
                if (thisReq.get("name").equals("Int")) {
                    if ((Long) thisReq.get("amount") >= 1) {
                        reqs = reqs + " Int: " + String.valueOf(thisReq.get("amount"));
                        strR1.setText(reqs);
                    }
                    //some api entries have int with a blank value and an unnamed requirement with the int value
                    else {
                        Log.i(TAG, "blank int req");
                        thisReq = (JSONObject) statReqs.get(x + 1);
                        reqs = reqs + " Int: " + String.valueOf(thisReq.get("amount"));
                        strR1.setText(reqs);
                    }
                }
                if (thisReq.get("name").equals("Fai")) {
                    reqs = reqs + " Fai: " + String.valueOf(thisReq.get("amount"));
                    strR1.setText(reqs);
                }
                if (thisReq.get("name").equals("Arc")) {
                    reqs = reqs + " Arc: " + String.valueOf(thisReq.get("amount"));
                    strR1.setText(reqs);
                }
            }
        }
    }

}


