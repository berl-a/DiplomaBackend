package system.controller.service.database;

import java.util.LinkedList;
import java.util.Set;

public interface DatabaseService {

    void init();
    void getSettingsFromFile();
    boolean connect();
    LinkedList<StringDatabaseEntry> getStringDataFromDatabase(String tableName, Set<String> fieldNames);
    boolean addStringDataToDatabase(String tableName, LinkedList<StringDatabaseEntry> databaseEntries);
    LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabase(String tableName, Set<String> fieldNames);
    boolean addDataToDatabase(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries);
    String getFieldValuesInDbFormat(LinkedList<IntStringBlobDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList);
    boolean removeItemWhereStringFieldEqualsValue(String tableName, String fieldName, String fieldValue);
    String getStringFieldValuesInDbFormat(LinkedList<StringDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList);
    String getFieldNamesInDBFormat(LinkedList<String> fieldNames);
}
