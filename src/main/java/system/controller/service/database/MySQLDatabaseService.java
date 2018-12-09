
package system.controller.service.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.stereotype.Service;
import system.controller.tools.DataToolkit;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

import static system.controller.Const.SETTINGS_FILE_LOCATION;

/**
 * Service for working with MySQL database
 */
@Service
public class MySQLDatabaseService implements DatabaseService {


    public final int CONNECTION_TIMEOUT_MS = 20;
    private Connection connection;

    private boolean hasConnected = false;
    private boolean failedConnection = false;

    int dbPort = 3306;
    String dbName = "test";
    String dbUser = "root";
    String dbPassword = "pass";
    String dbServerName = "127.0.0.1";

    @PostConstruct
    public void init() {
        getSettingsFromFile();
        connect();
    }

    public void getSettingsFromFile() {
        try {
            Scanner sc = new Scanner(new File(SETTINGS_FILE_LOCATION));
            if(sc.hasNext()) {
                dbPort = Integer.parseInt(sc.nextLine());
                dbName = sc.nextLine();
                dbUser = sc.nextLine();
                dbPassword = sc.nextLine();
                dbServerName = sc.nextLine();
            }
            sc.close();
            System.out.println("Settings read from file");
        } catch (FileNotFoundException e) {
            System.out.println("Database settings file not found, settings read from app");
        }
    }

    public boolean connect() {
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setPort(dbPort);
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setServerName(dbServerName);


        try {
            connection = dataSource.getConnection();
            hasConnected = true;
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            System.err.println("Database connection error");
            System.err.println("Error code: " + e.getErrorCode() + "\nSQL state: " + e.getSQLState());
            failedConnection = true;
            return false;
        }

    }

    /**
     * Get data from app.app.database with connection to the app.app.database inside
     * @param tableName name of the SQL table you want to get data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    public LinkedList<StringDatabaseEntry> getStringDataFromDatabase(String tableName, Set<String> fieldNames) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
            return getStringDataFromDatabaseInternal(tableName, fieldNames);
        } else {
            return getStringDataFromDatabaseInternal(tableName, fieldNames);
        }
    }

    /**
     * Get data from app.app.database without app.app.database connection
     * @param tableName name of the SQL table you want to get data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    private LinkedList<StringDatabaseEntry> getStringDataFromDatabaseInternal(String tableName, Set<String> fieldNames) {

        LinkedList<StringDatabaseEntry> resultList = new LinkedList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM test." + tableName);

            while (resultSet.next()) {
                HashMap<String, String> tempMap = new HashMap<>();
                for(String fieldName : fieldNames) {
                    tempMap.put(fieldName, resultSet.getString(fieldName));
                }
                resultList.add(new StringDatabaseEntry(tempMap));
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addStringDataToDatabase(String tableName, LinkedList<StringDatabaseEntry> databaseEntries) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
            return addStringDataToDatabaseInternal(tableName, databaseEntries);
        } else {
            return addStringDataToDatabaseInternal(tableName, databaseEntries);
        }
    }

    private boolean addStringDataToDatabaseInternal(String tableName, LinkedList<StringDatabaseEntry> databaseEntries) {
        try {
            Statement stmt = connection.createStatement();

            Set<String> fieldNamesSet = databaseEntries.getFirst().getFieldNames();
            LinkedList<String> fieldNamesList = new LinkedList<>(fieldNamesSet);
            String fieldNamesInDBFormat = getFieldNamesInDBFormat(fieldNamesList);

            String valuesInDbFormat = getStringFieldValuesInDbFormat(databaseEntries, fieldNamesList);

            stmt.executeUpdate("INSERT INTO " + tableName + " " + fieldNamesInDBFormat + " VALUES " + valuesInDbFormat + ";");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //////////////////adding blob

    /**
     * Get data from app.app.database with connection to the app.app.database inside
     * @param tableName name of the SQL table you want to get data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    public LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabase(String tableName, Set<String> fieldNames) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
        }
        return getDataFromDatabaseInternal(tableName, fieldNames);
    }

    /**
     * Get data from app.app.database without app.app.database connection
     * @param tableName name of the SQL table you want to get data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    private LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabaseInternal(String tableName, Set<String> fieldNames) {

        LinkedList<IntStringBlobDatabaseEntry> resultList = new LinkedList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + tableName);

            while (resultSet.next()) {
                HashMap<String, Object> tempMap = new HashMap<>();
                for(String fieldName : fieldNames) {
                    if(fieldName.contains("_INT")) {
                        tempMap.put(fieldName, resultSet.getInt(fieldName));
                    } else if(fieldName.contains("_BLOB")) {
                        tempMap.put(fieldName, resultSet.getBlob(fieldName));
                    } else if(fieldName.contains("_STRING")) {
                        tempMap.put(fieldName, resultSet.getString(fieldName));
                    }
                }
                resultList.add(new IntStringBlobDatabaseEntry(tempMap));
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addDataToDatabase(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
        }
        return addDataToDatabaseInternal(tableName, databaseEntries);
    }

    private boolean addDataToDatabaseInternal(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries) {
        try {
            Statement stmt = connection.createStatement();

            Set<String> fieldNamesSet = databaseEntries.getFirst().getFieldNames();
            LinkedList<String> fieldNames = new LinkedList<>(fieldNamesSet);
            String fieldNamesInDBFormat = getFieldNamesInDBFormat(fieldNames);

            String valuesInDbFormat = getFieldValuesInDbFormat(databaseEntries, fieldNames);

            LinkedList<String> blobFieldNames = new LinkedList<>();
            for (String fieldName : fieldNames) {
                if (fieldName.contains("_BLOB")) {
                    blobFieldNames.add(fieldName);
                }
            }

    //            LinkedList<Integer> indexesOfFieldValues = new LinkedList<>();
    //            for(int i = 0; i < fieldNames.size(); i ++) {
    //                fieldNames.forEach(System.out::println);
    //                if(fieldNames.contains("_BLOB")) {
    //                    indexesOfFieldValues.add(i);
    //                    System.out.println("Adding blob index " + i);
    //                }
    //            }

    //            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + " " + fieldNamesInDBFormat + " VALUES " + valuesInDbFormat + ";");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + tableName +
                            " " + fieldNamesInDBFormat +
                            " VALUES " + valuesInDbFormat + ";"
            );
            for(int itemIndex = 0; itemIndex < databaseEntries.size(); itemIndex ++) {
                for(int blobIndexInItem = 0; blobIndexInItem < blobFieldNames.size(); blobIndexInItem ++) {

                    IntStringBlobDatabaseEntry item = databaseEntries.get(itemIndex);
                    byte[] objectAsByteArray = DataToolkit.objectToByteArray(item.getField(blobFieldNames.get(blobIndexInItem)));
                    ByteArrayInputStream bais = new ByteArrayInputStream(objectAsByteArray);
                    statement.setBinaryStream(itemIndex * blobFieldNames.size() + blobIndexInItem + 1, bais, objectAsByteArray.length);

                }
            }
            statement.executeUpdate();
    //
    //            for(int i = 0; i < indexesOfFieldValues.size(); i ++) {
    //                statement.setBlob(i + 1, new ByteArrayInputStream(DataToolkit.objectToByteArray(databaseEntries.get(indexesOfFieldValues.get(i)))));
    //                System.out.println("Adding blob");
    //            }
    //            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getFieldValuesInDbFormat(LinkedList<IntStringBlobDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList) {

        StringBuilder allFields = new StringBuilder();
        String tempSingleEntryString;
        for(DatabaseEntry entry : databaseEntries) {
            tempSingleEntryString = fieldNamesList.stream().reduce(
                    "",
                    (acc, key) -> {
                        String newPart = "";
                        if(key.contains("_STRING")) {
                            newPart = "'" + entry.getField(key) + "'";
                        } else if(key.contains("_INT")) {
                            newPart = "" + entry.getField(key);
                        } else if(key.contains("_BLOB")) {
                            newPart = "?";
                        }
                        return acc + newPart + ", ";
                    });
            tempSingleEntryString = "(" + tempSingleEntryString.substring(0, tempSingleEntryString.length() - 2) + "), ";
            allFields.append(tempSingleEntryString);
        }
        return allFields.substring(0, allFields.length() - 2);
    }

    /////////////////not adding blob

    public boolean removeDataFromDatabase(String tableName, StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
            return removeDataFromDatabaseInternal(tableName, filter, filterBehaviour);
        } else {
            return removeDataFromDatabaseInternal(tableName, filter, filterBehaviour);
        }
    }

    private boolean removeDataFromDatabaseInternal(String tableName, StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
        try {
            Statement stmt = connection.createStatement();

            String deleteFilter = getDeleteFilter(filter, filterBehaviour);

            stmt.executeUpdate("DELETE FROM " + tableName + " WHERE " +  ";");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeItemWhereStringFieldEqualsValue(String tableName, String fieldName, String fieldValue) {
        return removeItemWhereFieldEqualsValueInternal(tableName, fieldName, fieldValue);
    }
//
//
    public boolean removeItemWhereFieldEqualsValue(String tableName, String fieldName, String fieldValue) {
        if(!hasConnected && !failedConnection) {
            this.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
            return removeItemWhereFieldEqualsValue(tableName, fieldName, fieldValue);
        } else {
            return removeItemWhereFieldEqualsValue(tableName, fieldName, fieldValue);
        }
    }
//
    private boolean removeItemWhereFieldEqualsValueInternal(String tableName, String fieldName, String fieldValue) {
        try {
            Statement stmt = connection.createStatement();

            System.out.println("Query is");
            System.out.println("DELETE FROM " + tableName + " WHERE " + "" + fieldName + "='" + fieldValue + "'" + ";");
            stmt.executeUpdate("DELETE FROM " + tableName + " WHERE " + "" + fieldName + "='" + fieldValue + "'" + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String getDeleteFilter(StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
        if(filter.getFieldNames().isEmpty())
            return "TRUE";

        System.out.println("Size of filter is " + filter.getFields().size() + ", its data is " + filter.getFields());

        String result = "";

        if(filterBehaviour == FilterBehaviour.AND) {
            result = filter.getFieldNames().stream().reduce("", (acc, key) -> acc + key + "='" + filter.getField(key) + "' AND ");
            result = result.substring(0, result.length() - 5);
        } else if(filterBehaviour == FilterBehaviour.OR) {
            result = filter.getFieldNames().stream().reduce("", (acc, key) -> acc + key + "='" + filter.getField(key) + "' OR ");
            result = result.substring(0, result.length() - 4);
        }
        return result;
    }

    public String getStringFieldValuesInDbFormat(LinkedList<StringDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList) {

        StringBuilder allFields = new StringBuilder();
        String tempSingleEntryString;
        for(StringDatabaseEntry entry : databaseEntries) {
            System.out.println(entry.getFieldNames());
            tempSingleEntryString = fieldNamesList.stream().reduce("", (acc, key) -> acc + "'" + entry.getField(key) + "', ");
            tempSingleEntryString = "(" + tempSingleEntryString.substring(0, tempSingleEntryString.length() - 2) + "), ";
            allFields.append(tempSingleEntryString);
        }
        return allFields.substring(0, allFields.length() - 2);
    }

    public String getFieldNamesInDBFormat(LinkedList<String> fieldNames) {
        if(fieldNames.isEmpty())
            return "*";
        String fieldNamesInDBFormat = fieldNames.stream().reduce("", (acc, e) -> acc + "`" + e + "`, ");
        return "(" + fieldNamesInDBFormat.substring(0, fieldNamesInDBFormat.length() - 2) + ")";
    }


}
