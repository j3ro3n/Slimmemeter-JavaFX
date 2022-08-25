package nl.avans.slimmemeterjavafx.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.avans.slimmemeterjavafx.gauges.Gauges;
import nl.avans.slimmemeterjavafx.gauges.GaugesServices;
import nl.avans.slimmemeterjavafx.gui.data.LoadGauges;
import nl.avans.slimmemeterjavafx.gui.data.SaveGauges;
import nl.avans.slimmemeterjavafx.gui.gauges.EditGauges;
import nl.avans.slimmemeterjavafx.gui.gauges.NewGauges;

import java.util.ArrayList;

public class Main extends Application {

    public static GaugesServices gaugesServices = new GaugesServices();
    private Stage window;
    private ComboBox<String> gaugesTypeComboBox;
    private ListView<String> gaugesList;
    private TextField kwhText;
    private TextField totalKwhText;
    private Button deleteButton;
    private Gauges previousSelectedGauges = null;
    private String previousSelectedItem = null;


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Readings Slimmemeter P1 poort");

        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = this.createMenu();
        GridPane editor = this.createEditor();

        borderPane.setTop(menuBar);
        borderPane.setCenter(editor);

        Scene scene = new Scene(borderPane, 600, 350);
        window.setScene(scene);
        window.setResizable(false); // Makes the Gui not adjustable
        window.show();


    }

    private GridPane createEditor() {
        GridPane editor = new GridPane();
        ColumnConstraints leftColumnConstraint = new ColumnConstraints();
        leftColumnConstraint.setPercentWidth(40);
        ColumnConstraints rightColumnConstraint = new ColumnConstraints();
        rightColumnConstraint.setPercentWidth(60);
        editor.getColumnConstraints().addAll(leftColumnConstraint, rightColumnConstraint);

        editor.setPadding(new Insets(20));
        editor.setVgap(8);
        editor.setHgap(20);
        GridPane leftPane = new GridPane();
        leftPane.setVgap(10);
        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);

        editor.add(leftPane, 0, 0);
        editor.add(rightPane, 1, 0);

        // Gauge selector. Select which gauge you want to see.
        VBox gaugesTypeBox = this.createGaugesTypBox();

        // Meterstand van de gauge
        VBox kwhBox = new VBox();
        Label kwhLabel = new Label("Kilowatt per hour: (KwH)");
        kwhText = new TextField();
        kwhText.setPrefWidth(200.0);
        kwhText.setText("0");
        kwhText.setDisable(true);
        kwhBox.getChildren().addAll(kwhLabel, kwhText);

        // Meterstand totaal
        VBox totalKwhBox = new VBox();
        Label totalKwhLabel = new Label("Total Kilowatt per hour: (KwH)");
        totalKwhText = new TextField();
        totalKwhText.setPrefWidth(200.0);
        totalKwhText.setText("0");
        totalKwhText.setDisable(true);
        kwhBox.getChildren().addAll(totalKwhLabel, totalKwhText);

        leftPane.add(gaugesTypeBox, 0, 0);
        leftPane.add(kwhBox, 0, 1);
        leftPane.add(totalKwhBox, 0,2);

        // Create the gauge list box where al gauges are listed
        VBox gaugesListBox = new VBox();
        Label gaugesListHeader = new Label("Gauges:");

        gaugesList = new ListView<>();
        gaugesList.setPrefWidth(300.0);
        gaugesList.setOnMouseClicked(click -> {
            String selectedItem = gaugesList.getSelectionModel().getSelectedItem();
            int selectedIndex = gaugesList.getSelectionModel().getSelectedIndex();

            if (selectedItem == null) {
                return;
            }
            if (click.getClickCount() == 1) {
                System.out.println("selectedItem: " + selectedItem);
                System.out.println("this.previousSelectedItem: " + this.previousSelectedItem);
                if (this.previousSelectedItem != null && this.previousSelectedItem.equals(selectedItem)) {
                    this.previousSelectedItem = null;
                    this.previousSelectedGauges = null;
                    kwhText.setText("0");
                    gaugesListHeader.setText("Gauges:");
                    deleteButton.setDisable(true);
                    Platform.runLater(() -> gaugesList.getSelectionModel().select(null));
                } else {
                    Gauges gauges = gaugesServices.getGauges().get(selectedIndex);
                    kwhText.setText(String.format("%s", gauges.calculateKwh()));
                    this.previousSelectedItem = selectedItem;
                    this.previousSelectedGauges = gauges;
                    deleteButton.setDisable(false);
                    gaugesListHeader.setText("Tip: Double tab to edit.");
                }
            } else if (click.getClickCount() == 2) {
                window.hide();
                EditGauges.display(gaugesServices.getGauges().get(selectedIndex));
                this.previousSelectedItem = null;
                this.previousSelectedGauges = null;
                deleteButton.setDisable(true);
                gaugesListHeader.setText("Gauges:");
                window.show();
                updateView();
            }
        });

        gaugesListBox.getChildren().addAll(gaugesListHeader, gaugesList);

        deleteButton = new Button("Delete selected gauges");
        deleteButton.setDisable(true);
        deleteButton.setOnAction(e -> {
            if (previousSelectedItem != null) {
                System.out.println(previousSelectedGauges);
                System.out.println(gaugesServices.getGauges().indexOf(previousSelectedGauges));
                gaugesServices.getGauges().remove(previousSelectedGauges);
                previousSelectedGauges = null;
                previousSelectedItem = null;
                deleteButton.setDisable(false);
                gaugesListHeader.setText("Gauges");

                this.updateView();
            }
        });

        rightPane.add(gaugesListBox, 0 ,0);
        rightPane.add(deleteButton, 0, 1);

        this.updateView();

        return editor;
    }

    private void updateView() {
        gaugesList.getItems().clear();

        ArrayList<String> gauges = new ArrayList<>();

        for (Gauges gauge: gaugesServices.getGauges()) {
            gauges.add(gauge.toString());
        }

        kwhText.setText("0");
        totalKwhText.setText(String.format("%s", gaugesServices.calculateTotalKwh()));
        gaugesList.getItems().addAll(gauges);
    }

    private VBox createGaugesTypBox() {
        VBox gaugeTypeBox = new VBox();
        Label gaugeTypeLabel = new Label("Create gauge: ");
        gaugesTypeComboBox = new ComboBox<>();
        gaugesTypeComboBox.setPrefWidth(200.0);
        gaugesTypeComboBox.setPromptText("Select gauge...");
        gaugesTypeComboBox.getItems().addAll("Elektra", "gas", "Buiten temp");
        gaugesTypeComboBox
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        window.hide();

                        NewGauges.display(newValue);
                        window.show();
                        this.updateView();
                        Platform.runLater(() -> gaugesTypeComboBox.setValue(null));
                    }
                });
        gaugeTypeBox.getChildren().addAll(gaugeTypeLabel, gaugesTypeComboBox);
        return gaugeTypeBox;
    }

    private MenuBar createMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem save = new MenuItem("Save");
        save.setOnAction(e -> {
            window.hide();
            if (SaveGauges.display()) {
                window.show();
                this.updateView();
            }
        });

        MenuItem load = new MenuItem("Load");
        load.setOnAction(e -> {
            window.hide();
            if (LoadGauges.display()) {
                window.show();
                this.updateView();
            }
        });

        MenuItem close = new MenuItem("Shutdown");
        close.setOnAction(e -> window.close());

        fileMenu.getItems()
                .addAll(
                        save,
                        load,
                        new SeparatorMenuItem(),
                        close
                );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }
}

