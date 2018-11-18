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
    Response getAll() {
        Response resp;
        List<Result> allObjects = service.getAll();
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

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public @ResponseBody
    Response remove(@RequestBody String idWithoutLastSymbol) {
        idWithoutLastSymbol = idWithoutLastSymbol.substring(0, idWithoutLastSymbol.length() - 1);

        Response resp = new Response();
        String result = service.remove(idWithoutLastSymbol);
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