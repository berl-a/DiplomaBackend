package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.controller.simple_frontend_models.FUser;
import system.controller.simple_frontend_models.Response;
import system.model.quizzes.Quiz;
import system.model.users.UserType;
import system.service.QuizService;
import system.service.UserService;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    QuizService service;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody Quiz quiz) {
        Response resp;
        String addResult = service.add(quiz);
        if(addResult.equals(Const.OK_RESULT)) {
            resp = new Response(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestBody String id) {
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
