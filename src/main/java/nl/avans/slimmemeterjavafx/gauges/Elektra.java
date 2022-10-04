package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class Elektra implements Gauges {

    // Fields for gauge elektra
    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("readingHoog", "ReadingHoog:");
        put("readingLaag", "ReadingLaag:");
        put("readingZon", "ReadingZon:");
    }};

    //Type of gauge
    public final String type = "Elektra";

    // Reading elekta high
    private int readingHoog;

    // Reading elektra low
    private int readingLaag;

    // Reading elektra sun
    private int readingZon;

    public Elektra(int readingHoog, int readingLaag, int readingZon) {
        this.setReadingHoog(readingHoog);
        this.setReadingLaag(readingLaag);
        this.setReadingZon(readingZon);
    }

    public int getReadingHoog() { return readingHoog; }

    public void setReadingHoog(int readingHoog) { this.readingHoog = readingHoog; }

    public int getReadingLaag() { return readingLaag; }

    public void setReadingLaag(int readingLaag) { this.readingLaag = readingLaag; }

    public int getReadingZon() { return readingZon; }

    public void setReadingZon(int readingZon) { this.readingZon = readingZon; }

    @Override
    public HashMap<String, String> getFields() { return Elektra.fields; }

    @Override
    public String getType() { return this.type; }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("readingHoog", this.readingHoog);
        data.put("readingLaag", this.readingLaag);
        data.put("readingZon", this.readingZon);

        return data;
    }


    @Override
    public void setData(HashMap<String, Integer> data) {
        this.readingHoog = data.getOrDefault("readingHoog", 0);
        this.readingLaag = data.getOrDefault("readingLaag", 0);
        this.readingZon = data.getOrDefault("readingZon", 0);
    }

    @Override
    public double calculateKwh() { return this.readingHoog + this.readingLaag - this.readingZon; }

    @Override
    public double calculateGas() {
        return 0;
    }

    @Override
    public double calculateBuitenTemp() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Elektra (Hoog: %d Kwh, Laag: %d Kwh, Zon: %d Kwh)", readingHoog, readingLaag, readingZon);

    }
}

