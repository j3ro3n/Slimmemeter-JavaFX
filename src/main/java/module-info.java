module nl.avans.slimmemeterjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    //requires javafx.sql;
    //requires gson;

    opens nl.avans.slimmemeterjavafx to javafx.fxml;
    exports nl.avans.slimmemeterjavafx.gui;

}
