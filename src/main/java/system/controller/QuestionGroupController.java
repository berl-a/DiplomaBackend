package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.QuestionGroupWithNumberOfQuestions;
import system.controller.simple_frontend_models.Response;
import system.model.questions.Question;
import system.model.questions.QuestionGroup;
import system.model.quizzes.Quiz;
import system.controller.service.QuestionGroupService;
import system.controller.service.QuizService;

import java.util.List;

@Controller
@RequestMapping("/questionGroups")
public class QuestionGroupController {

    @Autowired
    QuestionGroupService service;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll(@RequestParam("teacher") String teacherId) {
        Response resp;
        List<QuestionGroup> allObjects = service.getAll(teacherId);
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/getAllWithNumberOfQuestions", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllWithNumberOfQuestions(@RequestParam("teacher") String teacherId) {
        Response resp;
        List<QuestionGroupWithNumberOfQuestions> allObjects = service.getAllWithNumberOfQuestions(teacherId);
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody QuestionGroup group) {
        Response resp;
        String addResult = service.add(group);
        if(!addResult.equals(Const.NOK_RESULT)) {
            resp = new Response(Const.NEW_ID_KEY, addResult);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestBody String id) {
        id = id.substring(0, id.length() - 1);
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
    Response edit(@RequestBody QuestionGroup group) {
        Response resp = new Response();
        String editResult = service.edit(group);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }

    @RequestMapping(value="/rename", method = RequestMethod.POST)
    public @ResponseBody
    Response rename(@RequestParam("id") String id, @RequestParam("newName") String newName) {
        Response resp = new Response();
        String editResult = service.rename(id, newName);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }

}
