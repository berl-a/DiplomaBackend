package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.HospitationController;
import system.dao.HospitationDao;
import system.database.DatabaseToolkit;
import system.model.Hospitation;
import system.model.HospitationPlan;

import java.util.LinkedList;
import java.util.Random;

@Service
public class HospitationService {

    @Autowired
    HospitationDao hospitationDao;

    private boolean getCachedHospitations = false;
    private LinkedList<Hospitation> cachedHospitations = new LinkedList<>();


    public LinkedList<Hospitation> getHospitations() {
        if(!getCachedHospitations) {
            cachedHospitations = hospitationDao.getHospitations();
        }

        return cachedHospitations;
    }

    public void addLightHospitation(HospitationController.HospitationFromFrontend hosp) {
        hospitationDao.addLightHospitation(hosp);
    }

    public HospitationPlan getRandomUnapprovedHospitationPlan() {
        LinkedList<HospitationPlan> hospPlans = DatabaseToolkit.getUnaprovedHospitationPlans();
        return hospPlans.get(new Random().nextInt(hospPlans.size()));
    }


    public boolean isGetCachedHospitations() {
        return getCachedHospitations;
    }

    public void setGetCachedHospitations(boolean getCachedHospitations) {
        this.getCachedHospitations = getCachedHospitations;
    }

    public void addLightHospitationFromFrontend(HospitationController.HospitationFromFrontend hospitation) {
        hospitationDao.addLightHospitation(hospitation);
    }
}
