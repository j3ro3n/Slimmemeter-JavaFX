package nl.avans.slimmemeterjavafx.gui.gauges;

import javafx.scene.shape.Shape;
import nl.avans.slimmemeterjavafx.gauges.Gauges;
import nl.avans.slimmemeterjavafx.gauges.GaugesServices;
import nl.avans.slimmemeterjavafx.gui.Main;

import java.util.HashMap;

public class NewGauges {
    public static Gauges display(String gaugesType) {
        GaugesScene scene = new GaugesScene();

        HashMap<String, Integer> data = scene.createWindowAndShow("Give a reading: " + gaugesType, GaugesServices.getFields(gaugesType));

        if (data == null) {
            return null;
        }

        return Main.gaugesServices.addGauges(gaugesType, data);
    }
}
