package system.controller.dao;

import com.mysql.jdbc.Blob;
import system.controller.Const;
import system.controller.service.database.MySQLDatabaseService;
import system.controller.tools.DataToolkit;
import system.controller.service.database.IntStringBlobDatabaseEntry;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.*;

public class Dao<Type extends Idable> {

    public static final boolean CONTENT_IS_BYTE_ARRAY_NOT_OBJECT = false;
    private String tableName;

    public Dao(String tableName) {
        this.tableName = tableName;
    }

    public LinkedList<Type> getAll() {
        LinkedList<Type> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("Id_STRING");
        fields.add("Content_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = MySQLDatabaseService.getDataFromDatabase(tableName, fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            Object blobFieldAsObject = entry.getField("Content_BLOB");
            Object content = null;
            if(CONTENT_IS_BYTE_ARRAY_NOT_OBJECT) {
                byte[] contentAsByteArray = (byte[]) entry.getField("Content_BLOB");
                content = DataToolkit.byteArrayToObject(contentAsByteArray);
            } else {
                System.out.println(entry.getField("Content_BLOB").getClass());
                Blob blob = (Blob) entry.getField("Content_BLOB");
                try {
                    InputStream stream = blob.getBinaryStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                    Object object = objectInputStream.readObject();
                    content = object;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            resultList.add((Type) content);
        }
        return resultList;
    }

    public void add(Type obj) {
        HashMap<String, Object> newObjectInfo = new HashMap<>();
        newObjectInfo.put("Id_STRING", obj.getId());
        newObjectInfo.put("Content_BLOB", obj);
        IntStringBlobDatabaseEntry newEntry = new IntStringBlobDatabaseEntry(newObjectInfo);
        MySQLDatabaseService.addDataToDatabase(tableName, new LinkedList<>(Collections.singletonList(newEntry)));
    }

    public String remove(String id) {
        boolean result = MySQLDatabaseService.removeItemWhereStringFieldEqualsValue(tableName, "Id_STRING", id);
        return result ? Const.OK_RESULT : Const.NOK_RESULT;
    }



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
