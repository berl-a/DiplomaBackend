package system.controller.dao;

import system.controller.Const;
import system.controller.tools.DataToolkit;
import system.controller.service.database.DatabaseToolkit;
import system.controller.service.database.IntStringBlobDatabaseEntry;

import java.util.*;

public class Dao<Type extends Idable> {

    private String tableName;

    public Dao(String tableName) {
        this.tableName = tableName;
    }

    public LinkedList<Type> getAll() {
        LinkedList<Type> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("Id_STRING");
        fields.add("Content_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase(tableName, fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            byte[] contentAsByteArray = (byte[]) entry.getField("Content_BLOB");
            Object content = DataToolkit.byteArrayToObject(contentAsByteArray);
            resultList.add((Type) content);
        }
        return resultList;
    }

    public void add(Type obj) {
        HashMap<String, Object> newObjectInfo = new HashMap<>();
        newObjectInfo.put("Id_STRING", obj.getId());
        newObjectInfo.put("Content_BLOB", obj);
        IntStringBlobDatabaseEntry newEntry = new IntStringBlobDatabaseEntry(newObjectInfo);
        DatabaseToolkit.addDataToDatabase(tableName, new LinkedList<>(Collections.singletonList(newEntry)));
    }

    public String remove(String id) {
        boolean result = DatabaseToolkit.removeItemWhereStringFieldEqualsValue(tableName, "Id_STRING", id);
        return result ? Const.OK_RESULT : Const.NOK_RESULT;
    }



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
