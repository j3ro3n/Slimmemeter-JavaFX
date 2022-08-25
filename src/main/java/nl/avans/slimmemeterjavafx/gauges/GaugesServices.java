package nl.avans.slimmemeterjavafx.gauges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GaugesServices {

    // List of all gauges in the gauges service
    private ArrayList<Gauges> gauges = new ArrayList<>();

    // Get the fields for the gauge type
    public static HashMap<String, String> getFields(String gaugesType) {
        switch (gaugesType) {
            case "Elektra":
                return Elektra.fields;
            case "Gas":
                return Gas.fields;
            case "Buiten temperatuur":
                return BuitenTemp.fields;
            default:
                return null;
        }
    }

    // Get all current gauges
    public ArrayList<Gauges> getGauges() { return this.gauges;}

    // Set all current gauges
    public void setGauges(ArrayList<Gauges> gauges) { this.gauges = gauges; }

    // Calculate KwH combined in the gauge service
    public double calculateTotalKwh() {
        double totalKwh = 0.0;

        for (Gauges gauges : gauges) {
            totalKwh += gauges.calculateKwh();
        }
        return totalKwh;
    }

    // Add gauge to list
    public void addGauges(Gauges gauges) { this.gauges.add(gauges); }

    //Add gauge
    public Gauges addGauges(String gaugesType, HashMap<String, Integer> data) {
        Gauges newGauges;

        switch (gaugesType){
            case "Elektra":
                newGauges = new Elektra(data.get("readingHoog"), data.get("readingLaag"), data.get("readingZon"));
                break;
            case "Gas":
                newGauges = new Gas(data.get("Gas"));
                break;
            case "Buiten temperatuur":
                newGauges = new BuitenTemp(data.get("BuitenTemp"));
                break;
            default:
                return null;
        }

        this.gauges.add(newGauges);
        return newGauges;
    }
}
