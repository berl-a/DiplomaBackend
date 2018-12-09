package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.controller.simple_frontend_models.Response;
import system.controller.tools.DataToolkit;
import system.model.classes.games.*;
import system.model.classes.questions.Question;
import system.model.classes.questions.QuestionType;
import system.controller.service.GameService;
import system.controller.service.PlayerService;
import system.controller.service.QuizService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        code = code.toUpperCase();
        if(service.getByCode(code) != null) {
            String playerId = service.join(code, name);
            GameWithActualQuiz gameJoined = service.getByCode(code);
            resp = new Response();
            resp.put(Const.PLAYER_KEY, playerService.get(playerId));
            resp.put(Const.GAME_KEY, gameJoined);
        } else {
            resp = new Response(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/waitForGameStart", method = RequestMethod.GET)
    public @ResponseBody
    Response waitForGameStart(@RequestParam("gameId") String gameId, @RequestParam("playerId") String playerId) {
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            long waitStartTime = System.currentTimeMillis();
            long maxWaitTime = waitStartTime + MAX_WAITING_FOR_GAME_START_TIME;
            boolean gameStarting = false;
            while(System.currentTimeMillis() < maxWaitTime) {
                foundGame = service.getWithQuiz(gameId);
                if(foundGame.getStartTime() != 0 && foundGame.getStartTime() < System.currentTimeMillis()) {
                    gameStarting = true;
                    break;
                }
                try {Thread.sleep(GAME_START_CHECK_DELAY);} catch (InterruptedException e) {e.printStackTrace();}
            }
            if(gameStarting) {
                LinkedList<Question> realQuestionsForPlayer = service.getRealQuestionsForPlayer(gameId, playerId);
                LinkedList<String> questionsForPlayer = realQuestionsForPlayer
                        .stream()
                        .map(Question::getId)
                        .collect(Collectors.toCollection(LinkedList::new));

                int playerIndex = foundGame.getPlayers().indexOf(playerId);
                LinkedList<ListOfQuestions> questionsForAllPlayers = foundGame.getQuestionsForPlayers();

                questionsForAllPlayers.set(playerIndex, new ListOfQuestions(questionsForPlayer));
                foundGame.setQuestionsForPlayers(foundGame.getQuestionsForPlayers());
                service.set(gameId, foundGame);

                LinkedList<Question> questionsWithoutCorrectAnswers = realQuestionsForPlayer.stream().peek(q -> q.setCorrectAnswers(null)).collect(Collectors.toCollection(LinkedList::new));
                resp.put(Const.OBJECT_KEY, questionsWithoutCorrectAnswers);
//                resp.put(Const.START_TIME_KEY, foundGame.getStartTime());
//                resp.put(Const.FULL_TIME_KEY, foundGame.getFullTime());
                resp.put(Const.TIME_LEFT_KEY, foundGame.getStartTime() + foundGame.getFullTime() - System.currentTimeMillis());
                resp.put(Const.NAME_KEY, foundGame.getName());
//                resp.put(Const.SERVER_TIME, System.currentTimeMillis());
            } else {
                resp.put(Const.LONG_POLL_KEY, Const.REPEAT_LONG_POLL);
            }
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }


    @RequestMapping(value="/getGameInfo", method = RequestMethod.GET)
    public @ResponseBody
    Response getGameInfo(@RequestParam("gameId") String gameId) {
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
//            System.out.println("Game started " + ((System.currentTimeMillis() - foundGame.getStartTime()) / 60_000) + " minutes ago");
//            resp.put(Const.OBJECT_KEY, service.getRealQuestionsWithoutCorrectAnswerForPlayer(gameId, playerId));
            resp.put(Const.START_TIME_KEY, foundGame.getStartTime());
            resp.put(Const.SERVER_TIME, System.currentTimeMillis());
            resp.put(Const.TIME_LEFT_KEY, foundGame.getStartTime() + foundGame.getFullTime() - System.currentTimeMillis());
            resp.put(Const.FULL_TIME_KEY, foundGame.getFullTime());
            resp.put(Const.NAME_KEY, foundGame.getName());
//            resp.put(Const.SERVER_TIME, System.currentTimeMillis());

        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/waitForPlayerJoin", method = RequestMethod.GET)
    public @ResponseBody
    Response waitForPlayerJoin(@RequestParam("playersAlreadyJoined") LinkedList<String> playersAlreadyJoined, @RequestParam("gameId") String gameId, @RequestParam("teacherId") String teacherId) {
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            long waitStartTime = System.currentTimeMillis();
            long maxWaitTime = waitStartTime + MAX_WAITING_TIME_FOR_PLAYER_JOIN;
            boolean differenceFound = false;
            while(System.currentTimeMillis() < maxWaitTime) {
                foundGame = service.getWithQuiz(gameId);
                LinkedList<String> gamePlayers;
                if(foundGame != null) {
                    gamePlayers = foundGame.getPlayers();
                    differenceFound = !DataToolkit.areListsTheSame(gamePlayers, playersAlreadyJoined);
                    if (differenceFound) {
                        break;
                    }
                } else {
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
            @RequestParam("questionType") QuestionType questionType,
            @RequestParam("answerAsString") String answerAsString
    ) {
        System.out.println("Answering question " + questionId);
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            Answer answer = null;
            if(questionType == QuestionType.SINGLE_CHOICE)
                answer = new SingleChoiceAnswer(Integer.valueOf(answerAsString));
            else if(questionType == QuestionType.FREE_TEXT)
                answer = new FreeTextAnswer(answerAsString);
            else if(questionType == QuestionType.MULTIPLE_CHOICE)
                answer = new MultipleChoiceAnswer(
                        Arrays
                                .stream(answerAsString.split(","))
                                .map(Integer::parseInt)
                                .collect(Collectors.toCollection(LinkedList::new))
                );
            boolean isAnswerCorrect = service.answerQuestion(gameId, playerId, questionId, answer);
//            System.out.println("ANswer is correct? " + isAnswerCorrect);

            long startTime = foundGame.getStartTime();
            long fullTime = foundGame.getFullTime();

            resp.put(Const.RESULT_KEY, isAnswerCorrect);
            resp.put(Const.FULL_TIME_KEY, fullTime);
            resp.put(Const.TIME_LEFT_KEY, startTime + fullTime - System.currentTimeMillis());
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/setFullTime", method = RequestMethod.POST)
    public @ResponseBody
    Response setFullTime(
            @RequestParam("gameId") String gameId,
            @RequestParam("fullTime") long fullTime
    ) {
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            foundGame.setFullTime(fullTime);
            service.set(gameId, foundGame);
//            System.out.println("TIME_LEFT is " + (foundGame.getStartTime() + foundGame.getFullTime() - System.currentTimeMillis()));
            resp.put(Const.TIME_LEFT_KEY, foundGame.getStartTime() + foundGame.getFullTime() - System.currentTimeMillis());
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/startGame", method = RequestMethod.POST)
    public @ResponseBody
    Response startGame(@RequestParam("gameId") String gameId, @RequestParam("teacherId") String teacherId, @RequestParam("time") int time, @RequestParam("gameName") String gameName) {

        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();

        if(foundGame != null) {
            foundGame.setName(gameName);
            foundGame.setFullTime(time * 60_000);
            service.set(gameId, foundGame);

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


    @RequestMapping(value="/endGame", method = RequestMethod.POST)
    public @ResponseBody
    Response endGame(
            @RequestParam("gameId") String gameId
    ) {
        Game foundGame = service.getWithQuiz(gameId);
        Response resp = new Response();
        if(foundGame != null) {
            foundGame.setFullTime(System.currentTimeMillis() - foundGame.getStartTime());
            service.set(gameId, foundGame);
            resp.put(Const.RESULT_KEY, Const.OK_RESULT);
        } else {
            resp.put(Const.ERROR_KEY, Const.NOT_FOUND_RESULT);
        }
        return resp;
    }

    @RequestMapping(value="/getWithQuiz", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@RequestParam(value = "id") String id) {

        Response resp = new Response();
        GameWithActualQuiz result = service.getWithQuiz(id);
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
