package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class BuitenTemp implements Gauges {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("buitenTemp", "BuitenTemp");
    }};

    public final String type = "BuitenTemp";

    private int buitenTemp;

    public BuitenTemp(int buitenTemp) { this.setBuitenTemp(buitenTemp); }

    public int getBuitenTemp() { return buitenTemp; }

    public void setBuitenTemp(int buitenTemp) { this.buitenTemp = buitenTemp; }

    @Override
    public HashMap<String, String> getFields() { return BuitenTemp.fields; }

    @Override
    public String getType() { return this.type; }

    @Override
    public double calculateKwh() {
        return 0;
    }

    @Override
    public double calculateGas() {
        return 0;
    }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("BuitenTemp", this.buitenTemp);

        return data;
    }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.buitenTemp = data.getOrDefault("buitenTemp", 0);
    }

    @Override
    public double calculateBuitenTemp() { return this.buitenTemp; }

    @Override
    public String toString() {
        return String.format("BuitenTemp (Temp: %d C)", buitenTemp);
    }
}

