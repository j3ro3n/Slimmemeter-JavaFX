package nl.avans.slimmemeterjavafx.data;

import nl.avans.slimmemeterjavafx.gauges.Gauges;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StorageInterface {

    //Get data from MariaDB P1 database
    ArrayList<Gauges> loadData() throws Exception;

    //Get data from a directory location on device
    ArrayList<Gauges> loadData(String location) throws Exception;

    //Insert list of gauges to the vat database
    void saveData(ArrayList<Gauges> gauges) throws Exception;

    //Save list of gauges to a filepath on device
    void saveData(String location, ArrayList<Gauges> gauges) throws Exception;

}
