package nl.avans.slimmemeterjavafx.data;

import nl.avans.slimmemeterjavafx.gauges.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TextStorage implements StorageInterface {
    //
    private Type gaugeList = new TypeToken<ArrayList<Gauges>>() {
    }.getType();

    //
    private JsonDeserializer<ArrayList<Gauges>> deserializer = (json, typeOfT, context) -> {
        ArrayList<Gauges> gauges = new ArrayList<>();
        JsonArray jsonArray = (JsonArray) json;

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            switch (jsonObject.get("type").getAsString()) {
                case "Elektra hoog":
                    gauges.add(new ElektraHoog(jsonObject.get("ElektraHoog").getAsInt()));
                    break;
                case "Elektra laag":
                    gauges.add(new ElektraLaag(jsonObject.get("ElektraLaag").getAsInt()));
                    break;
                case "Elektra huidig":
                    gauges.add(new ElektraHuidig(jsonObject.get("ElektraHuidig").getAsInt()));
                    break;
                case "Elektra zon":
                    gauges.add(new ElektraZon(jsonObject.get("ElektraZon").getAsInt()));
                    break;
                case "Gas":
                    gauges.add(new Gas(jsonObject.get("Gas").getAsInt()));
                    break;
                case "Buiten temp":
                    gauges.add(new BuitenTemp(jsonObject.get("BuitenTemp").getAsInt()));
                    break;
            }
        }
        return gauges;
    };

    //
    @Override
    public ArrayList<Gauges> loadData() { throw new UnsupportedOperationException(); }

    //
    @Override
    public ArrayList<Gauges> loadData(String location) throws Exception {
        FileReader fileReader = new FileReader(location);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine = bufferedReader.readLine();

        while (currentLine != null) {
            file.append(currentLine);
            file.append(System.lineSeparator());
            currentLine = bufferedReader.readLine();
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(gaugeList, this.deserializer);
        Gson custonGson = gsonBuilder.create();

        return custonGson.fromJson(file.toString(), gaugeList);
    }

    //
    @Override
    public void saveData(ArrayList<Gauges> gauges) { throw new UnsupportedOperationException(); }

    //
    @Override
    public void saveData(String location, ArrayList<Gauges> gauges) throws Exception {
        String json = new Gson().toJson(gauges);
        FileWriter fileWriter = new FileWriter(location);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(json);
        bufferedWriter.close();
    }

}
