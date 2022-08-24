package nl.avans.slimmemeterjavafx.gui.gauges;

import nl.avans.slimmemeterjavafx.gauges.Gauges;
import java.util.HashMap;

public class EditGauges {
   public static Gauges display(Gauges gauges) {
       GaugesScene scene = new GaugesScene();
       System.out.println("Gauges: " + gauges);
       HashMap<String, Integer> data = scene.createWindowAndShow("Bewerk Gauges", gauges.getFields(), gauges.getData());
       if (data != null) {
           gauges.setData(data);
       }
       return gauges;
   }
}
