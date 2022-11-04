package com.example.eldogwrangler;

public class Stat {

    private int value;
    private String name;

    //stat object created on class change, sets value to class value (cValue)
    public Stat(int cValue, String string) {
        value = cValue;
        name = string;
    }

    public void increment() {
        value++;
    }
    public void decrement() {
        value--;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(Integer integer) {
        value = integer;
    }
}
