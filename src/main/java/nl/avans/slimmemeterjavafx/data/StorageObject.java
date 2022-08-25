package nl.avans.slimmemeterjavafx.data;

import nl.avans.slimmemeterjavafx.gauges.Gauges;

import java.io.*;
import java.util.ArrayList;

public class StorageObject implements StorageInterface {
    //No location given for load data
    @Override
    public ArrayList<Gauges> loadData() { throw new UnsupportedOperationException(); }

    // Location given and load data and convert to a gauge in list
    @Override
    public ArrayList<Gauges> loadData(String location) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(location);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<Gauges>) objectInputStream.readObject();
    }

    // No location save object to storage
    @Override
    public void saveData(ArrayList<Gauges> gauges) { throw new UnsupportedOperationException(); }

    // Location given for save gauge to storage
    @Override
    public void saveData(String location, ArrayList<Gauges> gauges) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(location));
        objectOutputStream.writeObject(gauges);
    }



}
