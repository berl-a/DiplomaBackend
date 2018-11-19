package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.Response;
import system.model.Result;
import system.service.ResultService;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    ResultService service;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll(@RequestParam("teacherId") String teacherId) {
        Response resp;
        List<Result> allObjects = service.getAllByTeacher(teacherId);
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody Result result) {
        Response resp;
        String addResult = service.add(result);
        if(!addResult.equals(Const.NOK_RESULT)) {
            resp = new Response(Const.NEW_ID_KEY, addResult);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/grade", method = RequestMethod.POST)
    public @ResponseBody
    Response grade(
            @RequestParam("resultId") String resultId,
            @RequestParam("studentIndex") int studentIndex,
            @RequestParam("questionId") String questionId,
            @RequestParam("grade") Double grade
    ) {
        Response resp = new Response();
        String gradeResult = service.grade(resultId, studentIndex, questionId, grade);
        if(gradeResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, gradeResult);
        }
        return resp;
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestParam("id") String id) {
//        id = id.substring(0, id.length() - 1);

        Response resp = new Response();
        String result = service.remove(id);
        if(result.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, result);
        }
        return resp;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Result result) {
        Response resp = new Response();
        String editResult = service.edit(result);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }

}
