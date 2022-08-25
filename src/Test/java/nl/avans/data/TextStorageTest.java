package nl.avans.data;

import nl.avans.slimmemeterjavafx.data.StorageInterface;
import nl.avans.slimmemeterjavafx.data.StorageService;
import nl.avans.slimmemeterjavafx.data.TextStorage;
import nl.avans.slimmemeterjavafx.gauges.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TextStorageTest extends DataTestBase {

    @AfterEach
    void tearDown() {
        removeCreatedFiles();
    }

    @Test
    void loadData() {
        GaugesServices gaugesServices = new GaugesServices();
        gaugesServices.addGauges(new Elektra(200, 250, 90));

        ArrayList<Gauges> gauges = gaugesServices.getGauges();

        String fileLocation = generateFileLocation("Text", "json");

        StorageInterface storageInterface = new StorageService()
                .getStorage(StorageService.STORAGE_TYPE_TEXT);

        ArrayList<Gauges> loadedGauges = new ArrayList<>();
        try {
            storageInterface.saveData(fileLocation, gaugesServices.getGauges());
            loadedGauges = storageInterface.loadData(fileLocation);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof FileNotFoundException) {
                fail("Could not find file location");
            } else {
                fail("Unknown problem while trying to load/save the gauges using Text Storage");
            }
        }

        assertEquals(gauges.size(), loadedGauges.size());

        for (int i = 0; i < gauges.size(); i++) {
            assertEquals(gauges.get(i).getData(), loadedGauges.get(i).getData());
            assertEquals(gauges.get(i).getFields(), loadedGauges.get(i).getFields());
        }

        createdFiles.add(fileLocation);
    }

    @Test
    void saveData() {
        GaugesServices gaugesServices = new GaugesServices();
        gaugesServices.addGauges(new Elektra(200, 250, 90));

        String fileLocation = generateFileLocation("Text", "json");

        StorageInterface storageInterface = new StorageService()
                .getStorage(StorageService.STORAGE_TYPE_TEXT);

        try {
            storageInterface.saveData(fileLocation, gaugesServices.getGauges());
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof FileNotFoundException) {
                fail("Could not find file location");
            } else {
                fail("Unknown problem while trying to save the Gauges using Text Storage");
            }
        }

        File file = new File(fileLocation);

        assertTrue(file.exists());

        createdFiles.add(fileLocation);
    }

    @Test
    void loadData1() {
        TextStorage textStorage = (TextStorage) new StorageService()
                .getStorage(StorageService.STORAGE_TYPE_TEXT);

        assertThrows(UnsupportedOperationException.class, textStorage::loadData);
    }

    @Test
    void saveData1() {
        TextStorage textStorage = (TextStorage) new StorageService()
                .getStorage(StorageService.STORAGE_TYPE_TEXT);

        assertThrows(UnsupportedOperationException.class, () -> textStorage.saveData(new ArrayList<>()));
    }
}
