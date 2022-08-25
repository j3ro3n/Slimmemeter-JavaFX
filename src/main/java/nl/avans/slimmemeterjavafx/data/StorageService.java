package nl.avans.slimmemeterjavafx.data;

import java.io.ObjectStreamException;

public class StorageService {

    // Storage of Text, Object, SQL
    public static final String STORAGE_TYPE_TEXT = "STORAGE_TYPE_TEXT";
    public static final String STORAGE_TYPE_OBJECT = "STORAGE_TYPE_OBJECT";
    public static final String STORAGE_TYPE_DATABASE = "STORAGE_TYPE_DATABASE";

    // Storage of instance Text, Object, SQL/database
    private TextStorage textStorage = new TextStorage();
    private StorageObject storageObject = new StorageObject();
    private DatabaseStorage databaseStorage = new DatabaseStorage();

    // Get storage gauges on the base of storage type
    public StorageInterface getStorage(String storageType) {
        switch (storageType) {
            case STORAGE_TYPE_TEXT:
                return textStorage;
            case STORAGE_TYPE_OBJECT:
                return storageObject;
            case STORAGE_TYPE_DATABASE:
                return databaseStorage;
                default:
                    return null;
        }
    }
}
