package nl.avans.slimmemeterjavafx.gauges;

import java.util.HashMap;

public class Gas implements Gauges {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("gas", "Gas:");
    }};

    public final String type = "Gas";

    private int gas;

    public Gas(int gas) {

        this.setGas(gas);
    }

    public int getGas() { return this.gas; }

    public void setGas(int gas) { this.gas = gas; }

    @Override
    public HashMap<String, String> getFields() { return Gas.fields; }

    @Override
    public String getType() { return this.type; }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("Gas", this.gas);

        return data;
    }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.gas = data.getOrDefault("gas", 0);
    }

    @Override
    public double calculateKwh() { return this.gas; }

    @Override
    public String toString() {
        return String.format("Gas (Gas: %d m3)", gas);
    }
}

