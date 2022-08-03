package nl.avans.slimmemeterjavafx.gui.gauges;

import nl.avans.slimmemeterjavafx.gauges.Gauges;
import java.util.HashMap;

public class EditGauges {
    public static Gaugee display(Gauges Gauges) {
        GaugesScene scene = new GaugesScene();
        System.out.println("Gauges: " + Gauges);
        HashMap<String, Integer> data = scene.createWindowAndShow("Edit Gauges", Gauges.getFields(), Gauges.getData());
        if (data != null) {
            Gauges.setData(data);
        }
        return Gauges;
    }
}
