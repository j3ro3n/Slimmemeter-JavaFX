package nl.avans.slimmemeterjavafx.gauges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GaugesServices {

    // List of all gauges in the gauges service
    private ArrayList<Gauges> gauges = new ArrayList<>();

    // Get the fields for the shape type
    public static HashMap<String, String> getFields(String gaugesType) {
        switch (gaugesType) {
            case "Elektra hoog":
                return ElektraHoog.fields;
            case "Elektra laag":
                return ElektraLaag.fields;
            case "Elektra huidig":
                return ElektraHuidig.fields;
            case "Elektra zonnepanelen":
                return ElektraZon.fields;
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
            case "Elektra hoog":
                newGauges = new ElektraHoog(data.get("reading"));
                break;
            case "Elektra laag":
                newGauges = new ElektraLaag(data.get("reading"));
                break;
            case "Elektra huidig":
                newGauges = new ElektraHuidig(data.get("reading"));
                break;
            case "Elektra zonnepanelen":
                newGauges = new ElektraZon(data.get("reading"));
                break;
            case "Gas":
                newGauges = new Gas(data.get("reading"));
                break;
            case "Buiten temperatuur":
                newGauges = new BuitenTemp(data.get("reading"));
                break;
            default:
                return null;
        }

        this.gauges.add(newGauges);
        return newGauges;
    }
}
