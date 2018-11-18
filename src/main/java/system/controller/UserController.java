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

    @Autowired
    UserService service;

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public @ResponseBody
    Response verify(@RequestParam("login") String login, @RequestParam("hash") String hash) {

        System.out.println(">");
        System.out.println(login + " " + hash);
        System.out.println(">");
        Response resp = new Response();

        System.out.println("__");
        service.getAll().forEach(u -> System.out.println(u.getLogin() + " " + u.getHash()));

        if(service.get(login) != null) {
            resp.put(Const.RESULT_KEY, service.isPasswordCorrect(login, hash));
            resp.put(Const.TEACHER_ID_KEY, service.get(login).getId());
            resp.put(Const.TEACHER_TYPE_KEY, service.get(login).getType());
        } else {
            resp.put(Const.RESULT_KEY, false);
            resp.put(Const.TEACHER_ID_KEY, null);
        }

        return resp;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll() {
        Response resp;
        List<User> allObjects = service.getAll();
        resp = new Response(Const.OBJECTS_KEY, allObjects);
        return resp;
    }

    @RequestMapping(value="/getByLogin", method = RequestMethod.GET)
    public @ResponseBody
    Response getByLogin(@RequestParam(value = "login") String login) {

        Response resp = new Response();
        User result = service.get(login);
        if(result != null) {
            resp.put(Const.RESULT_KEY, result);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/getById", method = RequestMethod.GET)
    public @ResponseBody
    Response getById(@RequestParam(value = "id") String id) {

        Response resp = new Response();
        User result = service.getById(id);
        if(result != null) {
            resp.put(Const.RESULT_KEY, result);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody User user) {
        Response resp;
        String addResult = service.add(user);
        if(addResult.equals(Const.OK_RESULT)) {
            resp = new Response(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp = new Response(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestParam("id") String id) {
        Response resp = new Response();
        String addResult = service.remove(id);
        if(addResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, addResult);
        }
        return resp;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody User user) {
        Response resp = new Response();
        String editResult = service.edit(user);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }

    @RequestMapping(value="/copy", method = RequestMethod.POST)
    public @ResponseBody
    Response copy(@RequestBody String id) {
        String idWithoutEquals = id.substring(0, id.length() - 1);
        System.out.println("id without = is " + idWithoutEquals);
        Response resp = new Response();
        String result = service.copy(idWithoutEquals);
        if(!result.equals(Const.NOK_RESULT)) {
            resp = new Response(Const.NEW_ID_KEY, result);
        } else {
            resp = new Response(Const.ERROR_KEY, result);
        }
        return resp;
    }
}
