package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.controller.HospitationController;
import system.database.DatabaseToolkit;
import system.database.StringDatabaseEntry;
import system.model.Hospitation;
import system.model.Teacher;
import system.service.TeacherService;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class HospitationDao {

    @Autowired
    TeacherService teacherService;

    public LinkedList<Hospitation> getHospitations() {
        LinkedList<Hospitation> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("ID");
        fields.add("Plan hospitacjiID");
        fields.add("Komisja hospitacyjnaID");
        fields.add("Wykonana");
        fields.add("Pelna");
        fields.add("FormaZajęć");

        LinkedList<StringDatabaseEntry> databaseResult = DatabaseToolkit.getStringDataFromDatabase("Hospitacja", fields);
        for(StringDatabaseEntry entry : databaseResult) {
            resultList.add(
                    new Hospitation(entry)
            );
        }
        return resultList;
    }

    public void addLightHospitation(HospitationController.HospitationFromFrontend hospitation) {

        //getting index of the latest hospitation plan
        Set<String> fields = new HashSet<>();
        fields.add("PlanHospID");
        fields.add("Rok");
        fields.add("Wydzial");
        fields.add("Semester");


        StringDatabaseEntry goodHospPlan =
                DatabaseToolkit
                        .getStringDataFromDatabase("`Plan hospitacji`", fields)
                        .stream()
                        .filter(entry ->
                                entry.getField("Wydzial").equals("W8") &&
                                        entry.getField("Rok").equals("1") &&
                                        entry.getField("Semester").equals("2")
                        )
                        .findFirst()
                        .get();

        int goodHospPlanId = Integer.valueOf(goodHospPlan.getField("PlanHospID"));
        System.out.println("Good id is " + goodHospPlanId);



        String tableName = "Hospitacja";
        HashMap<String, String> newHospitationInfo = new HashMap<>();
        newHospitationInfo.put("Plan hospitacjiID", "" + goodHospPlanId);
        newHospitationInfo.put("Komisja hospitacyjnaID", "0");
        newHospitationInfo.put("Wykonana", "0");
        newHospitationInfo.put("Pelna", "0");
        StringDatabaseEntry newHospitationEntry = new StringDatabaseEntry(newHospitationInfo);
        DatabaseToolkit.addDataToDatabase(tableName, new LinkedList<>(Collections.singletonList(newHospitationEntry)));

        Set<String> neededFields = new HashSet<>();
        neededFields.add("HospID");
        LinkedList<StringDatabaseEntry> allHospitationsAfterAddingNew = DatabaseToolkit.getStringDataFromDatabase("Hospitacja", neededFields);
        int newlyAddedHospId = Integer.valueOf(allHospitationsAfterAddingNew.getLast().getField("HospID"));

        System.out.println("Newly added hosp id is " + newlyAddedHospId);


        Teacher hospitatedTeacher = teacherService.getTeacherWithTytulAndNameAndSurname(hospitation.getHospitowany());
        hospitatedTeacher.setHospitowany(1);
        LinkedList<Teacher> komHosp = new LinkedList<>(
                hospitation
                        .getHospitujace()
                        .stream()
                        .map(teacherService::getTeacherWithTytulAndNameAndSurname)
                        .collect(Collectors.toList())
        );
        for(int i = 0; i < komHosp.size(); i ++) {
            komHosp.get(i).setPrzewdoniczacy(hospitation.getHospitujacePrzew().get(i) ? 1 : 0);
        }
        komHosp.add(hospitatedTeacher);

        LinkedList<StringDatabaseEntry> teachersAsEntries = new LinkedList<>();
        for(Teacher t : komHosp) {
            HashMap<String, String> teacherAsMap = new HashMap<>();
            teacherAsMap.put("NauczycielID", "" + t.getId());
            teacherAsMap.put("Imie", t.getName());
            teacherAsMap.put("Tytul", t.getTytul());
            teacherAsMap.put("Nazwisko", t.getSurname());
            teacherAsMap.put("Profile", t.getProfiles());
            teacherAsMap.put("Wydzial", t.getWydzial());
            teacherAsMap.put("Hospitowany", "" + t.getHospitowany());
            teacherAsMap.put("Przewodniczacy", "" + t.getPrzewdoniczacy());
            teacherAsMap.put("Hospitacje", "" + newlyAddedHospId);
            teachersAsEntries.add(
                    new StringDatabaseEntry(teacherAsMap)
            );

        }
        DatabaseToolkit.addDataToDatabase("Nauczyciel", teachersAsEntries);

    }

    public boolean removeHospitation(Hospitation hospitation) {
        return DatabaseToolkit.removeItemWhereFieldEqualsValue("Hospitacja", "ID", "" + hospitation.getId());
    }

}
