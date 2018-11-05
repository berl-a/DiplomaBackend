package system.dao;

import org.springframework.stereotype.Repository;
import system.controller.Const;
import system.controller.tools.DataToolkit;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.users.IUser;
import system.model.users.User;
import system.model.users.UserType;
import system.service.UserService;

import java.util.*;

@Repository
public class UserDao {

    public LinkedList<User> getUsers() {
        LinkedList<User> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("Id_STRING");
        fields.add("Content_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Users", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            byte[] userContentAsByteArray = (byte[]) entry.getField("Content_BLOB");
            Object userContent = DataToolkit.byteArrayToObject(userContentAsByteArray);
            User convertedIUser = (User) userContent;
            resultList.add(convertedIUser);
        }
        return resultList;
    }


    public void addUser(IUser newUser) {
        String tableName = "Users";
        HashMap<String, Object> newObjectInfo = new HashMap<>();
        newObjectInfo.put("Id_STRING", newUser.getId());
        newObjectInfo.put("Content_BLOB", newUser);
        IntStringBlobDatabaseEntry newEntry = new IntStringBlobDatabaseEntry(newObjectInfo);
        DatabaseToolkit.addDataToDatabase(tableName, new LinkedList<>(Collections.singletonList(newEntry)));
    }

    public String removeUser(String login) {
        boolean result = DatabaseToolkit.removeItemWhereStringFieldEqualsValue("Users", "Id_STRING", login);
        return result ? Const.OK_RESULT : Const.NOK_RESULT;
    }
}
