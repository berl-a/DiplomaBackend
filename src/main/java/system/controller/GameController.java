package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.controller.simple_frontend_models.Response;
import system.controller.tools.DataToolkit;
import system.model.games.Game;
import system.service.GameService;
import system.service.PlayerService;
import system.service.QuizService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    public static final int MAX_WAITING_FOR_GAME_START_TIME = 30_000;
    public static final int GAME_START_CHECK_DELAY = 1000;
    public static final int MAX_WAITING_TIME_FOR_PLAYER_JOIN = 60_000;
    @Autowired
    GameService service;
    @Autowired
    PlayerService playerService;
    @Autowired
    QuizService quizService;

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public @ResponseBody
    Response add(@RequestParam("code") String code, @RequestParam("name") String name) {
        Response resp;
        if(service.getByCode(code) != null) {
            String playerId = service.join(code, name);
            GameWithActualQuiz gameJoined = service.getByCode(code);
            resp = new Response();
            resp.put(Const.PLAYER_KEY, playerService.getPlayer(playerId));
            resp.put(Const.GAME_KEY, gameJoined);
        } else {
            resp = new Response(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/waitForGameStart", method = RequestMethod.GET)
    public @ResponseBody
    Response waitForGameStart(@RequestParam("gameId") String gameId, @RequestParam("playerId") String playerId) {
        Game foundGame = service.get(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            long waitStartTime = System.currentTimeMillis();
            long maxWaitTime = waitStartTime + MAX_WAITING_FOR_GAME_START_TIME;
            boolean gameStarting = false;
            while(System.currentTimeMillis() < maxWaitTime) {
                foundGame = service.get(gameId);
                if(foundGame.getStartTime() != 0 && foundGame.getStartTime() < System.currentTimeMillis()) {
                    gameStarting = true;
                    break;
                }
                try {Thread.sleep(GAME_START_CHECK_DELAY);} catch (InterruptedException e) {e.printStackTrace();}
            }
            if(gameStarting) {
                resp.put(Const.OBJECT_KEY, service.getQuestionsWithoutCorrectAnswerForPlayer(gameId, playerId));
            } else {
                resp.put(Const.LONG_POLL_KEY, Const.REPEAT_LONG_POLL);
            }
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }



    @RequestMapping(value="/waitForPlayerJoin", method = RequestMethod.GET)
    public @ResponseBody
    Response waitForPlayerJoin(@RequestParam("playersAlreadyJoined") LinkedList<String> playersAlreadyJoined, @RequestParam("gameId") String gameId, @RequestParam("teacherId") String teacherId) {
        Game foundGame = service.get(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            long waitStartTime = System.currentTimeMillis();
            long maxWaitTime = waitStartTime + MAX_WAITING_TIME_FOR_PLAYER_JOIN;
            boolean differenceFound = false;
            while(System.currentTimeMillis() < maxWaitTime) {
                foundGame = service.get(gameId);
                LinkedList<String> gamePlayers = foundGame.getPlayers();
                differenceFound = !DataToolkit.areListsTheSame(gamePlayers, playersAlreadyJoined);
                if(differenceFound) {
                    break;
                }
                try {Thread.sleep(GAME_START_CHECK_DELAY);} catch (InterruptedException e) {e.printStackTrace();}
            }
            if(differenceFound) {
                resp.put(Const.OBJECTS_KEY, playerService.getPlayersForIds(foundGame.getPlayers()));
            } else {
                resp.put(Const.LONG_POLL_KEY, Const.REPEAT_LONG_POLL);
            }
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/answerQuestion", method = RequestMethod.POST)
    public @ResponseBody
    Response answerQuestion(
            @RequestParam("gameId") String gameId,
            @RequestParam("playerId") String playerId,
            @RequestParam("questionId") String questionId,
            @RequestParam("answerIndex") int answerIndex
    ) {
        Game foundGame = service.get(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            boolean isAnswerCorrect = service.answerQuestion(gameId, playerId, questionId, answerIndex);
            resp.put(Const.RESULT_KEY, isAnswerCorrect);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/startGame", method = RequestMethod.POST)
    public @ResponseBody
    Response startGame(@RequestParam("gameId") String gameId, @RequestParam("teacherId") String teacherId) {
        Game foundGame = service.get(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            if (teacherId.equals(quizService.get(foundGame.getQuiz()).getTeacher())) {
                service.startGame(gameId);
                resp.put(Const.RESULT_KEY, Const.OK_RESULT);
            } else {
                resp.put(Const.ERROR_KEY, Const.WRONG_TEACHER_ID);
            }
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }

        return resp;
    }


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
