package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.controller.tools.DataToolkit;
import system.service.HospitationService;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/hospitations")
public class HospitationController {

    @Autowired
    HospitationService hospitationService;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public @ResponseBody
    String getHospitations() {
        return DataToolkit.convertToJsonArray(hospitationService.getHospitations()).toString();
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    String addHospitation(@RequestBody HospitationFromFrontend hospitation) {

        hospitation.removeEmptyHospitujace();

        System.out.println("Hosp is " + hospitation.hospitowany + " " + hospitation.hospitujace + " " + hospitation.hospitujacePrzew);
        hospitationService.addLightHospitationFromFrontend(hospitation);
        return "Success";
    }


    public static class HospitationFromFrontend {

        private String hospitowany;
        private List<String> hospitujace;
        private List<Boolean> hospitujacePrzew;

        public HospitationFromFrontend(String hospitowany, List<String> hospitujace, List<Boolean> hospitujacePrzew) {
            this.hospitowany = hospitowany;
            this.hospitujace = hospitujace;
            this.hospitujacePrzew = hospitujacePrzew;

        }


        private void removeEmptyHospitujace() {
            LinkedList<Integer> indexesToRemove = new LinkedList<>();
            for(int i = 0; i < hospitujace.size(); i ++) {
                if(hospitujace.get(i).equals("")) {
                    indexesToRemove.add(i);
                    System.out.println("Something is empty!");
                }
            }
            indexesToRemove.sort(Comparator.reverseOrder());
            indexesToRemove.forEach(i -> hospitujace.remove(i.intValue()));
            indexesToRemove.forEach(i -> hospitujacePrzew.remove(i.intValue()));
        }


        public String getHospitowany() {
            return hospitowany;
        }

        public void setHospitowany(String hospitowany) {
            this.hospitowany = hospitowany;
        }

        public List<String> getHospitujace() {
            return hospitujace;
        }

        public void setHospitujace(List<String> hospitujace) {
            this.hospitujace = hospitujace;
        }

        public List<Boolean> getHospitujacePrzew() {
            return hospitujacePrzew;
        }

        public void setHospitujacePrzew(List<Boolean> hospitujacePrzew) {
            this.hospitujacePrzew = hospitujacePrzew;
        }
    }

}
