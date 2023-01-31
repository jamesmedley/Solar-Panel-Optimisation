package solarproject;

import java.util.HashMap;

public class Dictionary {

    private final HashMap<String, double[]> DICTIONARY; //This object of HashMap holds data and is abstracted by the Dictionary class

    public Dictionary() {
        this.DICTIONARY = new HashMap<String, double[]>();
    }

    public void add(String key, double value[]) { //Adds a key and value to a hashmap
        DICTIONARY.put(key, value);
    }

    public double[] get(String key) { //Returns data from a key from the hashmap
        return DICTIONARY.get(key);
    }

    public void remove(String key) { //Removes data using a key
        DICTIONARY.remove(key);
    }

    public void clear() { //Clears the hashmap
        DICTIONARY.clear();
    }
}
