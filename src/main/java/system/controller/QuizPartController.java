package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.QuizPartWithCategoryNames;
import system.controller.simple_frontend_models.Response;
import system.model.quizzes.QuizPart;
import system.controller.service.QuizPartService;

import java.util.List;

@Controller
@RequestMapping("/quiz-parts")
public class QuizPartController {

    @Autowired
    QuizPartService service;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@RequestParam(value = "id") String id) {

        Response resp = new Response();
        QuizPart result = service.get(id);
        if(result != null) {
            resp.put(Const.RESULT_KEY, result);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/getAllWithCatNames", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllWithCatNames(@RequestParam("quiz") String quizId) {
        Response resp;
        List<QuizPartWithCategoryNames> allObjects = service.getAllWithCatNamesFromQuiz(quizId);
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll() {
        Response resp;
        List<QuizPart> allObjects = service.getAll();
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody QuizPart quiz) {
        Response resp;
        String addResult = service.add(quiz);
        if(!addResult.equals(Const.NOK_RESULT)) {
            resp = new Response(Const.NEW_ID_KEY, addResult);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/copy", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody String id) {
        String idWithoutEquals = id.substring(0, id.length() - 1);
//        System.out.println("id without = is " + idWithoutEquals);
        Response resp = new Response();
        String result = service.copy(idWithoutEquals);
        if(!result.equals(Const.NOK_RESULT)) {
            resp = new Response(Const.NEW_ID_KEY, result);
        } else {
            resp = new Response(Const.ERROR_KEY, result);
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
    Response edit(@RequestBody QuizPart quiz) {
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
