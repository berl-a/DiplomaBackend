package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.FUser;
import system.controller.simple_frontend_models.Response;
import system.model.quizzes.Quiz;
import system.model.users.User;
import system.model.users.UserType;
import system.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final String PAIR_CORRECT = "pair correct";
    public static final String PAIR_INCORRECT = "pair incorrect";

    @Autowired
    UserService service;

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public @ResponseBody
    Response verify(FUser user) {
        return new Response(
                Const.RESULT_KEY,
                service.isPasswordCorrect(
                        user.getLogin(),
                        user.getHash(),
                        user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR
                ) ? PAIR_CORRECT : PAIR_INCORRECT
        );
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll() {
        Response resp;
        List<User> allObjects = service.getAll();
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody FUser user) {
        Response resp;
        String addResult = service.add(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(addResult.equals(Const.OK_RESULT)) {
            resp = new Response(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestBody FUser user) {
        Response resp = new Response();
        String addResult = service.add(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(addResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody FUser user) {
        Response resp = new Response();
        String editResult = service.edit(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }
}
