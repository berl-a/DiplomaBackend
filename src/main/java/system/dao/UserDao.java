package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.questions.*;
import system.model.users.Administrator;
import system.model.users.IUser;
import system.model.users.Teacher;
import system.model.users.UserType;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class UserDao {

    public LinkedList<IUser> getUsers() {
        LinkedList<IUser> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("UserId_INT");
        fields.add("UserType_STRING");
        fields.add("UserContent_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Users", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            UserType type = UserType.valueOf((String)entry.getField("UserType_STRING"));
            Object userContent = entry.getField("UserContent_BLOB");
            IUser convertedIUser = null;
            if(type == UserType.TEACHER)
                convertedIUser = (Teacher) userContent;
            else if(type == UserType.ADMINISTRATOR)
                convertedIUser = (Administrator) userContent;
            resultList.add(convertedIUser);
        }
        return resultList;
    }
}
