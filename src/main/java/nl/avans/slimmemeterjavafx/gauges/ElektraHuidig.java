package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class ElektraHuidig implements Gauges {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("reading", "Reading:");
    }};

    public final String type = "Elektra huidig";

    private int reading;

    public ElektraHuidig(int reading) {
        this.setReading(reading);
    }

    public int getReading() { return this.reading; }

    public void setReading(int reading) { this.reading = reading; }

    @Override
    public HashMap<String, String> getFields() { return ElektraHuidig.fields; }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("reading", this.reading);

        return data;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.reading = data.getOrDefault("reading", 0);
    }

    @Override
    public double calculateKwh() { return this.reading; }

    @Override
    public String toString() {
        return String.format("Elektra huidig (reading: %d", reading);
    }
}

