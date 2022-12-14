package nl.avans.slimmemeterjavafx.data;

import nl.avans.slimmemeterjavafx.gauges.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseStorage implements StorageInterface {

    //The URL to the MariaDB vat database with live connection to it. Sets and gets database URL
    private static String databaseUrl = "jdbc:mysql://127.0.0.1:3306/P1?useSSL=false&serverTimezone=UTC";
    private Connection connection;
    static String getDatabaseUrl() { return databaseUrl; }
    static void setDatabaseUrl(String databaseUrl) { DatabaseStorage.databaseUrl = databaseUrl; }
    private static Connection connect() throws Exception {
        return DriverManager.getConnection(
                databaseUrl,
                "P1Logger",
                "P1Logger!"
        );
    }

    //Check the P1 database connection
    public static boolean canMakeConnection() {
        try {
            connect();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // Make new connection to P1 database
    Connection newConnection() throws Exception {
        return connect();
    }

    //Load reading from P1 database
    @Override
    public ArrayList<Gauges> loadData() throws Exception {
        this.connection = connect();
        Statement statement = connection.createStatement();
        // Query for P1 database to receive data
        ResultSet gaugesResultSet = statement.executeQuery("SELECT * FROM readings");
        ArrayList<Gauges> gauges = new ArrayList<>();

        while (gaugesResultSet.next()) {
            String gaugeType = gaugesResultSet.getString("type");

            int readingHoog = gaugesResultSet.getInt("readingHoog");
            int readingLaag = gaugesResultSet.getInt("readingLaag");
            int readingZon = gaugesResultSet.getInt("readingZon");
            int Gas = gaugesResultSet.getInt("Gas");
            int BuitenTemp = gaugesResultSet.getInt("BuitenTemp");

            switch (gaugeType) {
                case "Elektra":
                    gauges.add(new Elektra(readingHoog, readingLaag, readingZon));
                    break;
                case "Gas":
                    gauges.add(new Gas(Gas));
                    break;
                case "BuitenTemp":
                    gauges.add(new BuitenTemp(BuitenTemp));
                    break;

            }

        }

        //Close the P1 database connection
        connection.close();

        return gauges;
    }

    // Save one gauge to the P1 database
    private void saveGauges(Gauges gauges) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO readings (type, readingHoog, readingLaag, readingZon, Gas, BuitenTemp) VALUES (?, ?, ?, ?, ?, ?)");
        HashMap<String, Integer> data = gauges.getData();
        statement.setObject(1, gauges.getType());
        statement.setObject(2, data.getOrDefault("readingHoog", 0));
        statement.setObject(3, data.getOrDefault("readingLaag", 0));
        statement.setObject(4, data.getOrDefault("readingZon", 0));
        statement.setObject(5, data.getOrDefault("Gas", 0));
        statement.setObject(6, data.getOrDefault("BuitenTemp", 0));
        statement.executeUpdate();
    }

    //Load location with data
    @Override
    public ArrayList<Gauges> loadData(String location) throws Exception {
        return this.loadData();
    }

    // Save data to P1 database
    @Override
    public void saveData(ArrayList<Gauges> gauge) throws Exception {
        this.connection = connect();

        connection.prepareStatement("DELETE FROM readings")
                .execute();
        for (Gauges gauges : gauge) {
            this.saveGauges(gauges);
        }

        connection.close();
    }

    // Save list of gauges on given location
    @Override
    public void saveData(String location, ArrayList<Gauges> gauges) throws Exception {
        this.saveData(gauges);
    }
}






