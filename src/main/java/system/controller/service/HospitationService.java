package system.controller.service;

import org.springframework.stereotype.Service;

@Service
public class HospitationService {

//    @Autowired
//    HospitationDao hospitationDao;
//
//    private boolean getCachedHospitations = false;
//    private LinkedList<Hospitation> cachedHospitations = new LinkedList<>();
//
//
//    public LinkedList<Hospitation> getHospitations() {
//        if(!getCachedHospitations) {
//            cachedHospitations = hospitationDao.getHospitations();
//        }
//
//        return cachedHospitations;
//    }
//
//    public void addLightHospitation(HospitationController.HospitationFromFrontend hosp) {
//        hospitationDao.addLightHospitation(hosp);
//    }
//
//    public HospitationPlan getRandomUnapprovedHospitationPlan() {
//        LinkedList<HospitationPlan> hospPlans = SqliteDatabaseService.getUnaprovedHospitationPlans();
//        return hospPlans.getWithQuiz(new Random().nextInt(hospPlans.size()));
//    }
//
//
//    public boolean isGetCachedHospitations() {
//        return getCachedHospitations;
//    }
//
//    public void setGetCachedHospitations(boolean getCachedHospitations) {
//        this.getCachedHospitations = getCachedHospitations;
//    }
//
//    public void addLightHospitationFromFrontend(HospitationController.HospitationFromFrontend hospitation) {
//        hospitationDao.addLightHospitation(hospitation);
//    }
}
