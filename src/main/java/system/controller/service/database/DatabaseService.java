package system.controller.service.database;

import java.util.LinkedList;
import java.util.Set;

/**
 * Interface for working with databases
 */
public interface DatabaseService {

    void init();
    void getSettingsFromFile();
    boolean connect();
    LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabase(String tableName, Set<String> fieldNames);
    boolean addDataToDatabase(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries);
    String getFieldValuesInDbFormat(LinkedList<IntStringBlobDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList);
    boolean removeItemWhereStringFieldEqualsValue(String tableName, String fieldName, String fieldValue);
    String getStringFieldValuesInDbFormat(LinkedList<StringDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList);
    String getFieldNamesInDBFormat(LinkedList<String> fieldNames);
}
