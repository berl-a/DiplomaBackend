package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.ResultDao;
import system.model.Result;
import system.model.games.*;
import system.model.questions.Question;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    ResultDao dao;

    @Autowired
    QuizService quizService;
    @Autowired
    PlayerService playerService;
    @Autowired
    QuestionService questionService;

    private LinkedList<Result> cachedResults = new LinkedList<>();

    public void updateCached() {
        cachedResults = dao.getAll();
    }

    public LinkedList<Result> getAll() {
        updateCached();
        return cachedResults;
    }

    public List<Result> getAllByTeacher(String teacherId) {
        return getAll().stream().filter(r -> teacherId.equals(r.getTeacher())).collect(Collectors.toCollection(LinkedList::new));
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

            LinkedList<Question> questions = questionService.getAll();
            LinkedList<ListOfRealQuestions> realQuestions = new LinkedList<>();
            System.out.println("Number of questions before transform: " + g.getQuestionsForPlayers().size());
            g.getQuestionsForPlayers().forEach(listOfQuestions -> {
                LinkedList<Question> transformedIdsToQuestions = new LinkedList<>();
                listOfQuestions.getQuestionIds().forEach(qid -> {
                    transformedIdsToQuestions.add(questions.stream().filter(q -> qid.equals(q.getId())).findAny().orElse(null));
                });
                transformedIdsToQuestions.removeIf(Objects::isNull);
                realQuestions.add(new ListOfRealQuestions(transformedIdsToQuestions));
            });
            LinkedList<PlayerPoints> playerPointsForPlayers = new LinkedList<>();
            for(int playerIndex = 0; playerIndex < g.getPlayersAnswers().size(); playerIndex ++) {
                PlayerAnswers playerAnswers = g.getPlayersAnswers().get(playerIndex);

                LinkedList<Double> playerPoints = new LinkedList<>();
                for(int questionIndex = 0; questionIndex < g.getQuestionsForPlayers().get(playerIndex).getQuestionIds().size(); questionIndex ++) {
                    String questionId = g.getQuestionsForPlayers().get(playerIndex).getQuestionIds().get(questionIndex);
                    Double currentAnswerCorrect = playerAnswers.getAnswers().get(questionId).getCorrect();
                    playerPoints.add(currentAnswerCorrect);
                }
                playerPointsForPlayers.add(new PlayerPoints(playerPoints));
            }
            LinkedList<Double> playerPointsSums = new LinkedList<>();
            playerPointsForPlayers.forEach(points -> playerPointsSums.add(points.getPoints().stream().reduce(0.0, (acc, el) -> acc + el)));

            System.out.println("Number of questions after transform: " + realQuestions.size());

            Result r = new Result(
                    realQuiz,
                    g,
                    realQuiz.getTeacher(),
                    g.getPlayers(),
                    realPlayers,
                    realQuestions,
                    g.getPlayersAnswers(),
                    playerPointsForPlayers,
                    playerPointsSums
            );

            results.add(r);
        });
        results.forEach(this::add);
    }
}
