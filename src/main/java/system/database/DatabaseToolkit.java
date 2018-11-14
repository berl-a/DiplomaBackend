
package system.database;

import system.controller.tools.DataToolkit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseToolkit {

	public static final int CONNECTION_TIMEOUT_MS = 20;
	public static final String GET_UNAPPROVED_HOSPITATION_PLANS_WITH_HOSPITATIONS_AND_TEACHERS = "SELECT * FROM ( ((innodb.Nauczyciel JOIN innodb.Hospitacja ON innodb.Nauczyciel.Hospitacje = innodb.Hospitacja.HospID) JOIN innodb.Zajecia ON innodb.Hospitacja.HospID = innodb.Zajecia.HospitacjaID JOIN innodb.`Plan hospitacji` ON innodb.Hospitacja.`Plan hospitacjiID` = innodb.`Plan hospitacji`.PlanHospID)) WHERE innodb.Nauczyciel.Hospitacje IN ( SELECT innodb.Hospitacja.HospID FROM innodb.Hospitacja WHERE innodb.Hospitacja.`Plan hospitacjiID` IN ( SELECT innodb.`Plan hospitacji`.PlanHospID FROM innodb.`Plan hospitacji` WHERE Zatwierdzony=0 ) )";
	private static Connection connection;

	private static boolean hasConnected = false;
	private static boolean failedConnection = false;

    static String databaseAddress = "D:\\Data\\Diploma\\Project\\Database\\test.db";

    public static void getSettingsFromFile() {
        try {
            Scanner sc = new Scanner(new File("C:/settings.txt"));
            databaseAddress = sc.nextLine();
            sc.close();
            System.out.println("Settings read from file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Settings file not found, settings read from app");
        }
    }

    public static boolean connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseAddress);
            hasConnected = true;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			failedConnection = true;
			return false;
		}
	}

	/**
	 * Get data from app.app.database with connection to the app.app.database inside
	 * @param tableName name of the SQL table you want to getWithQuiz data from
	 * @param fieldNames names of fields of the table
	 * @return list of app.app.database entries from the table
	 */
	public static LinkedList<StringDatabaseEntry> getStringDataFromDatabase(String tableName, Set<String> fieldNames) {
		if(!hasConnected && !failedConnection) {
			DatabaseToolkit.connect();
			while(!hasConnected && !failedConnection)
				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
			return getStringDataFromDatabaseInternal(tableName, fieldNames);
		} else {
			return getStringDataFromDatabaseInternal(tableName, fieldNames);
		}
	}

	/**
	 * Get data from app.app.database without app.app.database connection
	 * @param tableName name of the SQL table you want to getWithQuiz data from
	 * @param fieldNames names of fields of the table
	 * @return list of app.app.database entries from the table
	 */
	private static LinkedList<StringDatabaseEntry> getStringDataFromDatabaseInternal(String tableName, Set<String> fieldNames) {

		LinkedList<StringDatabaseEntry> resultList = new LinkedList<>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + tableName);

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

	public static boolean addStringDataToDatabase(String tableName, LinkedList<StringDatabaseEntry> databaseEntries) {
		if(!hasConnected && !failedConnection) {
			DatabaseToolkit.connect();
			while(!hasConnected && !failedConnection)
				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
			return addStringDataToDatabaseInternal(tableName, databaseEntries);
		} else {
			return addStringDataToDatabaseInternal(tableName, databaseEntries);
		}
	}

	private static boolean addStringDataToDatabaseInternal(String tableName, LinkedList<StringDatabaseEntry> databaseEntries) {
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
     * @param tableName name of the SQL table you want to getWithQuiz data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    public static LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabase(String tableName, Set<String> fieldNames) {
        waitForConnection();
        return getDataFromDatabaseInternal(tableName, fieldNames);
    }

    private static void waitForConnection() {
        if(!hasConnected && !failedConnection) {
            DatabaseToolkit.connect();
            while(!hasConnected && !failedConnection)
                try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    /**
     * Get data from app.app.database without app.app.database connection
     * @param tableName name of the SQL table you want to getWithQuiz data from
     * @param fieldNames names of fields of the table
     * @return list of app.app.database entries from the table
     */
    private static LinkedList<IntStringBlobDatabaseEntry> getDataFromDatabaseInternal(String tableName, Set<String> fieldNames) {

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
                        tempMap.put(fieldName, resultSet.getBytes(fieldName));
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

    public static boolean addDataToDatabase(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries) {
        waitForConnection();
        return addDataToDatabaseInternal(tableName, databaseEntries);
    }

    private static boolean addDataToDatabaseInternal(String tableName, LinkedList<IntStringBlobDatabaseEntry> databaseEntries) {
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

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + tableName +
                            " " + fieldNamesInDBFormat +
                            " VALUES " + valuesInDbFormat + ";"
            );

            for(int itemIndex = 0; itemIndex < databaseEntries.size(); itemIndex ++) {
                for(int blobIndexInItem = 0; blobIndexInItem < blobFieldNames.size(); blobIndexInItem ++) {
                    IntStringBlobDatabaseEntry item = databaseEntries.get(itemIndex);
                    statement.setBytes(
                            itemIndex * blobFieldNames.size() + blobIndexInItem + 1,
                            DataToolkit.objectToByteArray(item.getField(blobFieldNames.get(blobIndexInItem)))
                    );
                }
            }
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFieldValuesInDbFormat(LinkedList<IntStringBlobDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList) {

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



//	public static LinkedList<Hospitation> getUnaprovedHospitationsRaw() {
//		LinkedList<StringDatabaseEntry> resultList = new LinkedList<>();
//
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + "Hospitacje");
//
//			LinkedList<String> fieldNames = new LinkedList<>(Arrays.asList("ID", "Plan hospitacjiID", "Komisja hospitacyjnaID", "Wykonana", "Pelna", "FormaZajec"));
//
//			while (resultSet.next()) {
//				HashMap<String, String> tempMap = new HashMap<>();
//				for(String fieldName : fieldNames) {
//					tempMap.put(fieldName, resultSet.getString(fieldName));
//				}
//				resultList.add(new StringDatabaseEntry(tempMap));
//			}
//
//			return new LinkedList<>(resultList.stream().map(Hospitation::new).collect(Collectors.toList()));
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}


//	public static LinkedList<HospitationPlan> getUnaprovedHospitationPlans() {
//		if(!hasConnected && !failedConnection) {
//			DatabaseToolkit.connect();
//			while(!hasConnected && !failedConnection)
//				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
//			return getUnaprovedHospitationPlansInside();
//		} else {
//			return getUnaprovedHospitationPlansInside();
//		}
//	}
//	private static LinkedList<HospitationPlan> getUnaprovedHospitationPlansInside() {
//		LinkedList<StringDatabaseEntry> resultList = new LinkedList<>();
//
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet resultSet = stmt.executeQuery(GET_UNAPPROVED_HOSPITATION_PLANS_WITH_HOSPITATIONS_AND_TEACHERS);
//
//			LinkedList<String> fieldNames = new LinkedList<>(Arrays.asList(
//					"ID",
//					"NauczycielID",
//					"Imie",
//					"Nazwisko",
//					"Profile",
//					"Tytul",
//					"Wydzial",
//					"Hospitowany",
//					"Przewodniczacy",
//					"ProtokolyHospitacji",
//					"Hospitacje",
//					"HospID",
//					"Komisja hospitacyjnaID",
//					"Wykonana",
//					"Pelna",
//					"FormaZajec",
//					"PlanHospID",
//					"Zatwierdzony",
//					"Wydzial",
//					"Rok",
//					"Semester",
//
//					"ZajeciaID",
//					"NazwaPrzedmiotu",
//					"FormaZajec",
//					"Data",
//					"Czas",
//					"Profil"
//			));
//
//			while (resultSet.next()) {
//				HashMap<String, String> tempMap = new HashMap<>();
//				for(String fieldName : fieldNames) {
//					tempMap.put(fieldName, resultSet.getString(fieldName));
//				}
//				resultList.add(new StringDatabaseEntry(tempMap));
//			}
//
//			return HospitationPlanService.getHospitationPlansFromDatabaseEntries(resultList);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//
//	public static void approveHospitationPlan(int planId) {
//		if(!hasConnected && !failedConnection) {
//			DatabaseToolkit.connect();
//			while(!hasConnected && !failedConnection)
//				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
//			approveHospitationPlanInternal(planId);
//		} else {
//			approveHospitationPlanInternal(planId);
//		}
//	}
//
//	private static void approveHospitationPlanInternal(int planId) {
//
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("UPDATE `Plan hospitacji` SET Zatwierdzony = 1 WHERE PlanHospId = " + planId);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}




	public static boolean removeDataFromDatabase(String tableName, StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
		if(!hasConnected && !failedConnection) {
			DatabaseToolkit.connect();
			while(!hasConnected && !failedConnection)
				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
			return removeDataFromDatabaseInternal(tableName, filter, filterBehaviour);
		} else {
			return removeDataFromDatabaseInternal(tableName, filter, filterBehaviour);
		}
	}

	private static boolean removeDataFromDatabaseInternal(String tableName, StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
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


	public static boolean removeItemWhereStringFieldEqualsValue(String tableName, String fieldName, String fieldValue) {
		if(!hasConnected && !failedConnection) {
			DatabaseToolkit.connect();
			while(!hasConnected && !failedConnection)
				try{Thread.sleep(CONNECTION_TIMEOUT_MS);} catch (InterruptedException e) {e.printStackTrace();}
			return removeItemWhereStringFieldEqualsValueInternal(tableName, fieldName, fieldValue);
		} else {
			return removeItemWhereStringFieldEqualsValueInternal(tableName, fieldName, fieldValue);
		}
	}

	private static boolean removeItemWhereStringFieldEqualsValueInternal(String tableName, String fieldName, String fieldValue) {
		try {
			Statement stmt = connection.createStatement();

			stmt.executeUpdate("DELETE FROM " + tableName + " WHERE " + "" + fieldName + " = '" + fieldValue + "'" + ";");

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public static String getDeleteFilter(StringDatabaseEntry filter, FilterBehaviour filterBehaviour) {
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

	public static String getStringFieldValuesInDbFormat(LinkedList<StringDatabaseEntry> databaseEntries, LinkedList<String> fieldNamesList) {

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

	public static String getFieldNamesInDBFormat(LinkedList<String> fieldNames) {
		if(fieldNames.isEmpty())
			return "*";
		String fieldNamesInDBFormat = fieldNames.stream().reduce("", (acc, e) -> acc + "`" + e + "`, ");
		return "(" + fieldNamesInDBFormat.substring(0, fieldNamesInDBFormat.length() - 2) + ")";
	}


}
