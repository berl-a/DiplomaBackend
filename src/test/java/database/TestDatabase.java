package database;

import org.jboss.logging.annotations.Pos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.database.DatabaseToolkit;
import system.database.StringDatabaseEntry;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Component
public class TestDatabase {


    @Test
    @PostConstruct
    public void testGettingTeachersFromDatabase() {

        Set<String> fields = new TreeSet<>();
        fields.add("ID");
        fields.add("Imie");
        fields.add("Nazwisko");
        fields.add("Profile");
        fields.add("Tytul");

//        LinkedList<StringDatabaseEntry> result = DatabaseToolkit.getStringDataFromDatabase("Nauczyciel", fields);
//        for(StringDatabaseEntry e : result) {
//            System.out.println("Entry is " + e.toString());
//        }

    }

    @Test
    @PostConstruct
    public void testGettingHospitationsFromDatabase() {

        Set<String> fields = new TreeSet<>();
        fields.add("ID");
        fields.add("Plan hospitacjiID");
        fields.add("Komisja hospitacyjnaID");
        fields.add("Wykonana");
        fields.add("Pelna");
        fields.add("FormaZajec");

//        LinkedList<StringDatabaseEntry> result = DatabaseToolkit.getStringDataFromDatabase("Hospitacja", fields);
//        for(StringDatabaseEntry e : result) {
//            System.out.println("Entry is " + e.toString());
//        }

    }
//
//    @Test
//    @PostConstruct
//    public void testUnapprovedHospitationsGottenFromDatabaseOkay() {
//        DatabaseToolkit.getUnaprovedHospitationPlans();
//    }

}
