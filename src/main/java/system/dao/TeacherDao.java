package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.StringDatabaseEntry;
import system.model.Teacher;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class TeacherDao {

    public LinkedList<Teacher> getTeachers() {
        LinkedList<Teacher> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("NauczycielID");
        fields.add("Imie");
        fields.add("Nazwisko");
        fields.add("Profile");
        fields.add("Tytul");
        fields.add("Wydzial");

        LinkedList<StringDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Nauczyciel", fields);
        for(StringDatabaseEntry entry : databaseResult) {
            resultList.add(
                    new Teacher(entry)
            );
        }
        return resultList;
    }
}
