package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.model.classes.games.*;
import system.model.dao.ResultDao;
import system.model.classes.Result;
import system.model.classes.questions.Question;
import system.model.classes.quizzes.Quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static system.controller.Const.FREE_TEXT_ANSWER_CORRECTNESS;

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
        cachedResults.forEach(r -> {
            System.out.print(r.getRealGame().getName() + " ");
            System.out.println(r.getRealGame().getCode());
        });
        return cachedResults;
    }

    public List<Result> getAllByTeacher(String teacherId) {
        return getAll().stream().filter(r -> r != null && teacherId.equals(r.getTeacher())).collect(Collectors.toCollection(LinkedList::new));
    }

    public Result get(String resultId) {
        Optional<Result> foundResult = getAll().stream().filter(q -> q != null && resultId.equals(q.getId())).findAny();
        System.out.println(foundResult.get().getRealGame().getName());
        System.out.println(foundResult.get().getRealGame().getCode());
        return foundResult.orElse(null);
    }

    public String add(Result result) {
        updateCached();
        dao.add(result);
        return result.getId();
    }

    private void set(String resultId, Result resultToChange) {
        remove(resultId);
        add(resultToChange);
    }

    public String grade(String resultId, int studentIndex, String questionId, Double grade) {
        Result resultToChange = get(resultId);
        Answer answerToChange = resultToChange.getPlayersAnswers().get(studentIndex).getAnswers().get(questionId);
        answerToChange.setCorrect(grade);
        resultToChange.getPlayersAnswers().get(studentIndex).getAnswers().put(questionId, answerToChange);
        resultToChange = recalculatePoints(resultToChange);
        set(resultId, resultToChange);
        return Const.OK_RESULT;
    }

    private Result recalculatePoints(Result r) {
        LinkedList<PlayerPoints> playerPointsForPlayers = new LinkedList<>();
        for(int playerIndex = 0; playerIndex < r.getPlayersAnswers().size(); playerIndex ++) {
            PlayerAnswers playerAnswers = r.getPlayersAnswers().get(playerIndex);

            LinkedList<Double> playerPoints = new LinkedList<>();
            for(int questionIndex = 0; questionIndex < r.getQuestionsForPlayers().get(playerIndex).getQuestions().size(); questionIndex ++) {
                String questionId = r.getQuestionsForPlayers().get(playerIndex).getQuestions().get(questionIndex).getId();
                Answer currentAnswer = playerAnswers.getAnswers().get(questionId);
                if(currentAnswer != null) {
                    Double currentAnswerCorrect = playerAnswers.getAnswers().get(questionId).getCorrect();
                    if(currentAnswerCorrect != null)
                        playerPoints.add(currentAnswerCorrect);
                }
            }
            playerPointsForPlayers.add(new PlayerPoints(playerPoints));
        }
        LinkedList<Double> playerPointsSums = new LinkedList<>();
        playerPointsForPlayers.forEach(points -> playerPointsSums.add(points.getPoints().stream().filter(Objects::nonNull).reduce(0.0, (acc, el) -> {
            if(el != FREE_TEXT_ANSWER_CORRECTNESS) {
                return acc + el;
            } else {
                return acc;
            }
        })));
        r.setPlayersPoints(playerPointsForPlayers);
        r.setPlayersPointSums(playerPointsSums);
        return r;
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

    public void archiveGames(LinkedList<Game> games) {
        LinkedList<Result> results = new LinkedList<>();
        games.forEach(g -> {
            Quiz realQuiz = quizService.get(g.getQuiz());
            LinkedList<String> players = g.getPlayers();
            LinkedList<Player> realPlayers = new LinkedList<>();
            players.forEach(p -> realPlayers.add(playerService.get(p)));

            LinkedList<Question> questions = questionService.getAll();
            LinkedList<ListOfRealQuestions> realQuestions = new LinkedList<>();
//            System.out.println("Number of questions before transform: " + g.getQuestionsForPlayers().size());
            g.getQuestionsForPlayers().forEach(listOfQuestions -> {
                LinkedList<Question> transformedIdsToQuestions = new LinkedList<>();
//                System.out.println("Questions user answered:");
                listOfQuestions.getQuestionIds().forEach(qid -> {
//                    System.out.println("" + qid);
//                    System.out.println("question with this id is " + questions.stream().filter(q -> qid.equals(q.getId())).findAny().get());
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
//                    System.out.println("Question is " + questionId);
                    Answer currentAnswer = playerAnswers.getAnswers().get(questionId);
//                    System.out.println("Answer is " + currentAnswer);
                    if(currentAnswer != null) {
//                        System.out.println("Is answer correct? " + playerAnswers.getAnswers().get(questionId).getCorrect());
                        Double currentAnswerCorrect = playerAnswers.getAnswers().get(questionId).getCorrect();
                        if(currentAnswerCorrect != null)
                            playerPoints.add(currentAnswerCorrect);
                    }
                }
                playerPointsForPlayers.add(new PlayerPoints(playerPoints));
            }
            LinkedList<Double> playerPointsSums = new LinkedList<>();
            playerPointsForPlayers.forEach(points -> playerPointsSums.add(points.getPoints().stream().filter(Objects::nonNull).reduce(0.0, (acc, el) -> {
                if(el != FREE_TEXT_ANSWER_CORRECTNESS) {
                    System.out.println("Not equals");
                    return acc + el;
                } else {
                    System.out.println("Equals super code, not adding");
                    return acc;
                }
            })));

//            System.out.println("Number of questions after transform: " + realQuestions.size());

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

    public Result getByQuizCode(String quizCode) {
        Optional<Result> foundResult = getAll().stream().filter(r -> quizCode.equals(r.getRealGame().getCode())).findAny();
        return foundResult.orElse(null);
    }

    public Double getPointsForQuizByPlayer(String quizCode, String playerName) {
        Result res = getByQuizCode(quizCode);
        if(res != null) {
            int studentIndex = res
                    .getRealPlayers()
                    .stream()
                    .map(Player::getName)
                    .collect(Collectors.toCollection(LinkedList::new))
                    .indexOf(playerName);
            if(studentIndex != -1) {
                int numberOfQuestions = res.getQuestionsForPlayers().getFirst().getQuestions().size();
                double playerPoints = res.getPlayersPointSums().get(studentIndex);
                double studentPercentResult = playerPoints / (double) numberOfQuestions;

                return studentPercentResult;
            }
        }
        return Const.DEFAULT_POINTS_FOR_PLAYER;
    }
}