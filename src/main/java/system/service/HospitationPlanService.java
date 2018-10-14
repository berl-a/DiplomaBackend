package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.HospitationPlanDao;
import system.database.StringDatabaseEntry;
import system.model.*;

import java.util.LinkedList;

@Service
public class HospitationPlanService {

    public static final int HOSPITUJACY_DISCRIMINATOR = 0;

    @Autowired
    HospitationPlanDao hospitationPlanDao;


    //IF EMPTY DISCRIMINATOR - hospitujący
    private boolean getCachedHospitationPlans = false;
    private LinkedList<HospitationPlan> cachedHospitationPlans = new LinkedList<>();


    public LinkedList<HospitationPlan> getUnapprovedHospitationPlans() {
        if(!getCachedHospitationPlans) {
            cachedHospitationPlans = hospitationPlanDao.getUnapprovedHospitationPlans();
        }

        return cachedHospitationPlans;
    }


    public static LinkedList<HospitationPlan> getHospitationPlansFromDatabaseEntries(LinkedList<StringDatabaseEntry> entries) {
        LinkedList<Teacher> teachers = new LinkedList<>();
        LinkedList<Hospitation> hospitations = new LinkedList<>();
        LinkedList<HospitationPlan> hospitationPlans = new LinkedList<>();

        for(StringDatabaseEntry entry : entries) {
            if(hospitationPlans
                    .stream()
                    .noneMatch(plan -> plan.getId() == Integer.valueOf(entry.getField("PlanHospID")))) {
                hospitationPlans.add(new HospitationPlan(
                        Integer.valueOf(entry.getField("PlanHospID")),
                        Boolean.valueOf(entry.getField("Zatwierdzony")),
                        entry.getField("Wydzial"),
                        Integer.valueOf(entry.getField("Rok")),
                        Integer.valueOf(entry.getField("Semester")),
                        new LinkedList<>()
                ));
            }
            if(hospitations
                    .stream()
                    .noneMatch(hosp -> hosp.getId() == Integer.valueOf(entry.getField("HospID")))) {

                Zajecia zajeciaConnectedWithThisHospitation = null;

                if(entry.getField("ZajeciaID") != null) {
                    zajeciaConnectedWithThisHospitation = new Zajecia(
                            Integer.valueOf(entry.getField("ZajeciaID")),
                            entry.getField("NazwaPrzedmiotu"),
                            entry.getField("Data"),
                            entry.getField("Czas"),
                            entry.getField("Profil"),
                            FormaZajec.values()[Integer.valueOf(entry.getField("FormaZajec")) - 1]
                    );
                }

                hospitations.add(new Hospitation(
                        Integer.valueOf(entry.getField("HospID")),
                        Integer.valueOf(entry.getField("PlanHospID")),
                        Boolean.valueOf(entry.getField("Wykonana")),
                        Boolean.valueOf(entry.getField("Pelna")),
                        null,
                        new LinkedList<>(),
                        zajeciaConnectedWithThisHospitation
                ));



                if(hospitationPlans.stream().anyMatch(plan -> plan.getId() == Integer.valueOf(entry.getField("PlanHospID")))) {
                    HospitationPlan planToAddThisHospitationTo = hospitationPlans
                            .stream()
                            .filter(plan -> plan.getId() == Integer.valueOf(entry.getField("PlanHospID")))
                            .findAny()
                            .get();
                    planToAddThisHospitationTo.getHospitations().add(hospitations.getLast());
                }
            }

            if(teachers
                    .stream()
                    .noneMatch(teacher -> teacher.getId() == Integer.valueOf(entry.getField("NauczycielID")))) {
                teachers.add(new Teacher(
                        Integer.valueOf(entry.getField("NauczycielID")),
                        entry.getField("Imie"),
                        entry.getField("Nazwisko"),
                        entry.getField("Profile"),
                        entry.getField("Tytul"),
                        entry.getField("Wydzial"),
                        entry.getField("Przewodniczacy").equals("") ? 0 : Integer.valueOf(entry.getField("Przewodniczacy")),
                        entry.getField("Hospitowany").equals("") ? 0 : Integer.valueOf(entry.getField("Hospitowany"))
                ));

                if(hospitations.stream().anyMatch(hosp -> hosp.getId() == Integer.valueOf(entry.getField("HospID")))) {
                    Hospitation hospToAddThisTeacherTo = hospitations
                            .stream()
                            .filter(hosp -> hosp.getId() == Integer.valueOf(entry.getField("HospID")))
                            .findAny()
                            .get();

                    if(teachers.getLast().getHospitowany() == HOSPITUJACY_DISCRIMINATOR) {
                        hospToAddThisTeacherTo.getHospitujace().add(teachers.getLast());
                    } else {
                        hospToAddThisTeacherTo.setHospitowany(teachers.getLast());
                    }
                }
            }

        }
//        System.out.println("Hospitation plans are: ");
//        for(HospitationPlan hp : hospitationPlans) {
//            System.out.println(hp.toString());
//        }
        return hospitationPlans;
    }

    public boolean isGetCachedHospitationPlans() {
        return getCachedHospitationPlans;
    }

    public void setGetCachedHospitationPlans(boolean getCachedHospitationPlans) {
        this.getCachedHospitationPlans = getCachedHospitationPlans;
    }

    public void approveHospitationPlan(int planId) {
        hospitationPlanDao.approveHospitationPlan(planId);
    }
}
