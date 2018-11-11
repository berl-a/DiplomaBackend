package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.controller.simple_frontend_models.QuizWithCategoryNames;
import system.controller.simple_frontend_models.Response;
import system.model.games.Game;
import system.model.quizzes.Quiz;
import system.service.GameService;
import system.service.QuizService;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService service;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@RequestParam(value = "id") String id) {

        Response resp = new Response();
        GameWithActualQuiz result = service.get(id);
        if(result != null) {
            resp.put(Const.RESULT_KEY, result);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/getByCode", method = RequestMethod.GET)
    public @ResponseBody
    Response getByCode(@RequestParam(value = "code") String code) {

        Response resp = new Response();
        GameWithActualQuiz result = service.getByCode(code);
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
        List<Game> allObjects = service.getAll();
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/addFromQuiz", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody String id) {
        Response resp;

        GameWithActualQuiz createdGame = service.addGameFromQuiz(id);
        if(createdGame != null) {
            resp = new Response(Const.OBJECT_KEY, createdGame);
        } else {
            resp = new Response(Const.ERROR_KEY, Const.NOK_RESULT);
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

}
