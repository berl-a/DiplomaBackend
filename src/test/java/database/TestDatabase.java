package database;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

//        LinkedList<StringDatabaseEntry> result = SqliteDatabaseService.getStringDataFromDatabase("Nauczyciel", fields);
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

//        LinkedList<StringDatabaseEntry> result = SqliteDatabaseService.getStringDataFromDatabase("Hospitacja", fields);
//        for(StringDatabaseEntry e : result) {
//            System.out.println("Entry is " + e.toString());
//        }

    }
//
//    @Test
//    @PostConstruct
//    public void testUnapprovedHospitationsGottenFromDatabaseOkay() {
//        SqliteDatabaseService.getUnaprovedHospitationPlans();
//    }

}
