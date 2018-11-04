package system.dao;

import org.springframework.stereotype.Repository;
import system.controller.tools.DataToolkit;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.users.Administrator;
import system.model.users.IUser;
import system.model.users.Teacher;
import system.model.users.UserType;
import system.service.UserService;

import java.util.*;

@Repository
public class UserDao {

    public static final String ERROR_RESULT = "error";

    public LinkedList<IUser> getUsers() {
        LinkedList<IUser> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("Id_STRING");
        fields.add("Type_STRING");
        fields.add("Content_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Users", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
//            String id = (String) entry.getField("Id_STRING");
            UserType type = UserType.valueOf((String)entry.getField("Type_STRING"));
            byte[] userContentAsByteArray = (byte[]) entry.getField("Content_BLOB");
            Object userContent = DataToolkit.byteArrayToObject(userContentAsByteArray);
            IUser convertedIUser = null;
            if(type == UserType.TEACHER)
                convertedIUser = (Teacher) userContent;
            else if(type == UserType.ADMINISTRATOR)
                convertedIUser = (Administrator) userContent;
            resultList.add(convertedIUser);
        }
        return resultList;
    }


    public void addUser(IUser newUser) {
        String tableName = "Users";
        HashMap<String, Object> newObjectInfo = new HashMap<>();
        newObjectInfo.put("Id_STRING", newUser.getId());
        newObjectInfo.put("Type_STRING", newUser.getType().toString());
        newObjectInfo.put("Content_BLOB", newUser);
        IntStringBlobDatabaseEntry newEntry = new IntStringBlobDatabaseEntry(newObjectInfo);
        DatabaseToolkit.addDataToDatabase(tableName, new LinkedList<>(Collections.singletonList(newEntry)));
    }

    public String removeUser(String login) {
        boolean result = DatabaseToolkit.removeItemWhereStringFieldEqualsValue("Users", "Id_STRING", login);
        return result ? UserService.OK_RESULT : ERROR_RESULT;
    }
}
