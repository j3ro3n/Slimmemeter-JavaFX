package nl.avans.slimmemeterjavafx.gauges;

import java.io.Serializable;
import java.util.HashMap;

public interface Gauges extends Serializable {
    // Store type of gauges
    String getType();

    //Calculate the KwH of the gauge
    double calculateKwh();

    //Calculate the buiten temperatuur of the gauge
    double calculateGas();

    //Calculate the buiten temperatuur of the gauge
    double calculateBuitenTemp();

    // Convert the gauge in to text for saving to a textfile
    String toString();

    // Receives the fields of the gauge
    HashMap<String, String> getFields();

    // Get the data for the gauge
    HashMap<String, Integer> getData();

    // Set the data for the gauge
    void setData(HashMap<String, Integer> data);

}
