package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.controller.tools.DataToolkit;

@Controller
@RequestMapping("/hospitationPlans")
public class HospitationPlanController {
//
//    @Autowired
//    HospitationPlanService hospitationPlanService;
//
//    @RequestMapping(value="/getUnapproved", method = RequestMethod.GET)
//    public @ResponseBody
//    String getUnapprovedHospitationPlans() {
//        return DataToolkit.convertToJsonArray(hospitationPlanService.getUnapprovedHospitationPlans()).toString();
//    }
//
//    @RequestMapping(value="/approvePlan", method = RequestMethod.POST)
//    public @ResponseBody
//    String approveHospitationPlan(int planId) {
//        hospitationPlanService.approveHospitationPlan(planId);
//        return "Success";
//    }



}
