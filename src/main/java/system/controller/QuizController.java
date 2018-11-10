package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.QuizWithCategoryNames;
import system.controller.simple_frontend_models.Response;
import system.model.quizzes.Quiz;
import system.service.QuizService;

import java.util.List;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    QuizService service;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@RequestParam(value = "id") String id) {

        Response resp = new Response();
        Quiz result = service.get(id);
        if(result != null) {
            resp.put(Const.RESULT_KEY, result);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll() {
        Response resp;
        List<Quiz> allObjects = service.getAll();
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/getAllWithCatNames", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllWithCanNames(@RequestParam("teacher") String teacherId) {
        Response resp;
        List<QuizWithCategoryNames> allObjects = service.getAllWithCatNamesByTeacher(teacherId);
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody Quiz quiz) {
        Response resp;
        String addResult = service.add(quiz);
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
        String idWithoutEquals = id.substring(0, id.length() - 1);
        System.out.println("id without = is " + idWithoutEquals);
        Response resp = new Response();
        String result = service.remove(idWithoutEquals);
        if(result.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, result);
        }
        return resp;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Quiz quiz) {
        Response resp = new Response();
        String editResult = service.edit(quiz);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }
}
