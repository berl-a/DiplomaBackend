package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.StringDatabaseEntry;
import system.model.Hospitation;
import system.model.HospitationPlan;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class HospitationPlanDao {

    public LinkedList<HospitationPlan> getUnapprovedHospitationPlans() {
        return DatabaseToolkit.getUnaprovedHospitationPlans();
    }

    public void approveHospitationPlan(int planId) {
        DatabaseToolkit.approveHospitationPlan(planId);
    }
}
