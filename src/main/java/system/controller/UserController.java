package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.FUser;
import system.controller.simple_frontend_models.Response;
import system.model.users.Teacher;
import system.model.users.UserType;
import system.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final String PAIR_CORRECT = "pair correct";
    public static final String PAIR_INCORRECT = "pair incorrect";

    @Autowired
    UserService userService;

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public @ResponseBody
    Response verify(FUser user) {
        return new Response(
                Const.RESULT_KEY,
                userService.isPasswordCorrect(
                        user.getLogin(),
                        user.getHash(),
                        user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR
                ) ? PAIR_CORRECT : PAIR_INCORRECT
        );
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestBody FUser user) {
        Response resp;
        String addResult = userService.add(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
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
        String addResult = userService.add(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
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
        String editResult = userService.edit(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(editResult.equals(Const.OK_RESULT)) {
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, editResult);
        }
        return resp;
    }
}
