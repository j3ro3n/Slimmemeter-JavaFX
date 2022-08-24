package nl.avans.slimmemeterjavafx.gui.data;

public class LoadGauges extends DataScene {
    public static boolean display() {
        createWindowAndShow(DataScene.DATA_SCENE_TYPE_LOAD);

        return true;
    }
}
