package nl.avans.slimmemeterjavafx.gauges;

import java.util.*;
import java.util.function.Predicate;

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
            case "BuitenTemp":
                return BuitenTemp.fields;
            default:
                return null;
        }
    }

    // Get all current gauges
    public ArrayList<Gauges> getGauges() { return this.gauges;}

    // Set all current gauges
    public void setGauges(ArrayList<Gauges> gauges) { this.gauges = gauges; }

    // Calculate all KwH combined in the gauge service
    public double calculateTotalKwh() {
        double totalKwh = 0.0;

        for (Gauges gauges : gauges) {
            totalKwh += gauges.calculateKwh();
        }
        return totalKwh;
    }
    // Calculate buitentemperatuur gemiddeld over de dag in the gauge service
    public double calculateAverageBuitenTemp() {
        double averageBuitenTemp = 0.0;

        ArrayList<Gauges> buitenTemp = new ArrayList<>();

        for (Gauges buitentemp : gauges) {
            if (buitentemp.toString().contains("BuitenTemp")) {
                buitenTemp.add(buitentemp);
            }
        }

        int n = buitenTemp.size();

         for (Gauges buitentemp : buitenTemp) {
             averageBuitenTemp += buitentemp.calculateBuitenTemp();
        }

        return averageBuitenTemp/n;

    }

    // Calculate gas per dag in the gauge service
    public double calculateTotalGas() {
        double totalGas = 0.0;

        for (Gauges gauges : gauges) {
            totalGas += gauges.calculateGas();
        }
        return totalGas;
    }

    // Add gauge to list
    public void addGauges(Gauges gauges) { this.gauges.add(gauges); }
    //public void addBuitenTemp(BuitenTemp buitenTemp) { this.buitenTemp.add(buitenTemp); }

    //Add gauge
    public Gauges addGauges(String gaugesType, HashMap<String, Integer> data) {
        Gauges newGauges;

        switch (gaugesType){
            case "Elektra":
                newGauges = new Elektra(data.get("readingHoog"), data.get("readingLaag"), data.get("readingZon"));
                break;
            case "Gas":
                newGauges = new Gas(data.get("gas"));
                break;
            case "BuitenTemp":
                newGauges = new BuitenTemp(data.get("buitenTemp"));
                break;
            default:
                return null;
        }

        this.gauges.add(newGauges);
        return newGauges;
    }
}
