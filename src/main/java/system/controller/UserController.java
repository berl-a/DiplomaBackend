package system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.FUser;
import system.controller.tools.DataToolkit;
import system.model.users.IUser;
import system.model.users.Teacher;
import system.model.users.UserType;
import system.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final String RESULT_KEY = "result";
    private static final String ERROR_KEY = "error";

    @Autowired
    UserService userService;

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public @ResponseBody
    boolean verify(FUser user) {
        return userService.isPasswordCorrect(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestBody FUser user) {
        JSONObject result = new JSONObject();
        String addResult = userService.addUser(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(addResult.equals(UserService.OK_RESULT)) {
            result.put(RESULT_KEY, UserService.OK_RESULT);
        } else {
            result.put(ERROR_KEY, addResult);
        }
        return result.toString();
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    String remove(@RequestBody FUser user) {
        JSONObject result = new JSONObject();
        String addResult = userService.addUser(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(addResult.equals(UserService.OK_RESULT)) {
            result.put(RESULT_KEY, UserService.OK_RESULT);
        } else {
            result.put(ERROR_KEY, addResult);
        }
        return result.toString();
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    String edit(@RequestBody FUser user) {
        JSONObject result = new JSONObject();
        String editResult = userService.editUser(user.getLogin(), user.getHash(), user.isTeacher() ? UserType.TEACHER : UserType.ADMINISTRATOR);
        if(editResult.equals(UserService.OK_RESULT)) {
            result.put(RESULT_KEY, UserService.OK_RESULT);
        } else {
            result.put(ERROR_KEY, editResult);
        }
        return result.toString();
    }
}
