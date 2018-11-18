package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.ResultDao;
import system.model.Result;
import system.model.games.Game;
import system.model.games.Player;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    ResultDao dao;
    @Autowired
    QuizService quizService;
    @Autowired
    PlayerService playerService;

    private LinkedList<Result> cachedResults = new LinkedList<>();

    public void updateCached() {
        cachedResults = dao.getAll();
    }

    public LinkedList<Result> getAll() {
        updateCached();
        return cachedResults;
    }

    public Result get(String resultId) {
        Optional<Result> foundResult = getAll().stream().filter(q -> resultId.equals(q.getId())).findAny();
        return foundResult.orElse(null);
    }

    public String add(Result result) {
        updateCached();
        dao.add(result);
        return result.getId();
    }

    public String edit(Result result) {
        updateCached();
        dao.remove(result.getId());
        add(result);
        return Const.OK_RESULT;
    }

    public String remove(String id) {
        updateCached();
        dao.remove(id);
        return Const.OK_RESULT;
    }

    /*
        private String id;
        private Quiz realQuiz;
        private Game realGame;
        private String teacher;

        private LinkedList<String> players = new LinkedList<>();
        private LinkedList<Player> realPlayers = new LinkedList<>();
        private LinkedList<ListOfQuestions> questionsForPlayers = new LinkedList<>();
        private LinkedList<PlayerAnswers> playersAnswers = new LinkedList<>();
     */
    public void archiveGames(LinkedList<Game> games) {
        LinkedList<Result> results = new LinkedList<>();
        games.forEach(g -> {
            Quiz realQuiz = quizService.get(g.getQuiz());
            LinkedList<String> players = g.getPlayers();
            LinkedList<Player> realPlayers = new LinkedList<>();
            players.forEach(p -> realPlayers.add(playerService.get(p)));

            Result r = new Result(
                    realQuiz,
                    g,
                    realQuiz.getTeacher(),
                    g.getPlayers(),
                    realPlayers,
                    g.getQuestionsForPlayers(),
                    g.getPlayersAnswers()
            );

            results.add(r);
        });
        results.forEach(this::add);
    }
}
