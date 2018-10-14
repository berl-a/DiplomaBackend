package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.controller.tools.DataToolkit;
import system.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public @ResponseBody
    String getTeachers() {
        return DataToolkit.convertToJsonArray(teacherService.getUniqueTeachers()).toString();
    }
}
