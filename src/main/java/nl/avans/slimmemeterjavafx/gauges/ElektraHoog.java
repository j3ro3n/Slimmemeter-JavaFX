package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class ElektraHoog implements Gauges {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("reading", "Reading:");
    }};

    public final String type = "Elektra Hoog";

    private int reading;

    public ElektraHoog(int reading) {
        this.setreading(reading);
    }

    public int getreading() { return this.reading; }

    public void setreading(int reading) { this.reading = reading; }

    @Override
    public HashMap<String, String> getFields() { return ElektraHoog.fields; }

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
        return String.format("Elektra hoog (reading: %d)", reading);
    }
}

