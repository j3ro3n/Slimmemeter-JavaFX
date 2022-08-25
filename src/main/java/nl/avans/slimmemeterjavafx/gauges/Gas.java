package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class Gas implements Gauges {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("gar", "Gas:");
    }};

    public final String type = "Gas";

    private int reading;

    public Gas(int reading) {
        this.setReading(reading);
    }

    public int getReading() { return this.reading; }

    public void setReading(int reading) { this.reading = reading; }

    @Override
    public HashMap<String, String> getFields() { return Gas.fields; }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("gas", this.reading);

        return data;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.reading = data.getOrDefault("gas", 0);
    }

    @Override
    public double calculateKwh() { return this.reading; }

    @Override
    public String toString() {
        return String.format("Gas (gas m2: %d)", reading);
    }
}

