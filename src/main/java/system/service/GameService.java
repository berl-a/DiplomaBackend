package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.model.games.Game;
import system.model.games.Player;
import system.model.questions.Question;
import system.model.quizzes.Quiz;
import system.model.quizzes.QuizPart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameService {

    public static final int CODE_LENGTH = 4;
    public static final int NUMBER_OF_TRIES_TO_GENERATE_CODE = 100;

    @Autowired
    PlayerService playerService;
    @Autowired
    QuizService quizService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionGroupService questionGroupService;

    private LinkedList<Game> games = new LinkedList<>();

    public void set(String gameId, Game newGame) {
        Game foundGame = get(gameId);
        int gameIndex = games.indexOf(foundGame);
        games.set(gameIndex, newGame);
    }

    public LinkedList<Game> getAll() {
        return games;
    }

    public GameWithActualQuiz addGameFromQuiz(String quizId) {
        Game game = new Game(quizId);
        game.setCode(generateGameCode());
        games.add(game);
        return getGameWithActualQuiz(game);
    }

    public GameWithActualQuiz getWithQuiz(String gameId) {
        Optional<Game> foundGame = getAll().stream().filter(g -> gameId.equals(g.getId())).findAny();
        return getGameWithActualQuiz(foundGame.orElse(null));
    }

    public Game get(String gameId) {
        Optional<Game> foundGame = getAll().stream().filter(g -> gameId.equals(g.getId())).findAny();
        return foundGame.orElse(null);
    }

    public GameWithActualQuiz getByCode(String code) {
        Optional<Game> foundGame = getAll().stream().filter(g -> code.equals(g.getCode())).findAny();
        return getGameWithActualQuiz(foundGame.orElse(null));
    }

    public String remove(String id) {
        games.removeIf(g -> id.equals(g.getId()));
        return Const.OK_RESULT;
    }

    public String removeByCode(String code) {
        games.removeIf(g -> code.equals(g.getCode()));
        return Const.OK_RESULT;
    }

    private GameWithActualQuiz getGameWithActualQuiz(Game game) {
        if(game != null) {
            Quiz quiz = quizService.get(game.getQuiz());
            return new GameWithActualQuiz(game, quiz);
        } else {
            return null;
        }
    }

    private String generateGameCode() {
        String resultCode = "ERROR_GENERATING_CODE";
        for(int tries = 0; tries < NUMBER_OF_TRIES_TO_GENERATE_CODE; tries ++) {
            final String alphabet = "ABCDEFHIKLMNPQSTXYZ";
            final int alphabetLength = alphabet.length();
            Random r = new Random();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < CODE_LENGTH; i++) {
                builder.append(alphabet.charAt(r.nextInt(alphabetLength)));
            }
            resultCode = builder.toString();
            String tempResultCode = resultCode;
            if (games.stream().noneMatch(g -> tempResultCode.equals(g.getCode()))) break;
            else resultCode = "ERROR_GENERATING_CODE";
        }
        return resultCode;
    }

    public String join(String code, String playerName) {
        Player player = new Player(playerName);
        playerService.addPlayer(player);
        Game game = getByCode(code);
        game.addPlayer(player.getId());
        return player.getId();
    }

    public LinkedList<Question> getQuestionsWithoutCorrectAnswerForPlayer(String gameId, String playerId) {
        LinkedList<QuizPart> quizParts = quizService.appendQuizParts(quizService.get(getWithQuiz(gameId).getQuiz())).getParts();
        LinkedList<Question> questionsForPlayer = new LinkedList<>();
        for(QuizPart part : quizParts) {
            if(part.getCategory() != null && part.getCategory().equals(""))
                part.setCategory(null);
            if(part.getSubcategory() != null && part.getSubcategory().equals(""))
                part.setSubcategory(null);
            if(part.getSubsubcategory() != null && part.getSubsubcategory().equals(""))
                part.setSubsubcategory(null);
            LinkedList<Question> questionsFromThisPart = questionGroupService.getQuestionsFromGroups(part.getCategory(), part.getSubcategory(), part.getSubsubcategory());

            Collections.shuffle(questionsFromThisPart);
            questionsForPlayer.addAll(questionsFromThisPart.subList(0, part.getNumber()));
        }
        Collections.shuffle(questionsForPlayer);
        questionsForPlayer = questionsForPlayer.stream().peek(q -> q.setCorrectAnswers(null)).collect(Collectors.toCollection(LinkedList::new));
        return questionsForPlayer;
    }

    public void startGame(String gameId) {
        games.stream().filter(g -> gameId.equals(g.getId())).forEach(g -> {
           g.setStartTime(System.currentTimeMillis());
        });
    }

    public boolean answerQuestion(String gameId, String playerId, String questionId, Integer answerIndex) {
        Game foundGame = getWithQuiz(gameId);
        int playerAnswersIndex = foundGame.getPlayers().indexOf(playerId);
        foundGame.getPlayersAnswers().get(playerAnswersIndex).addAnswer(questionId, answerIndex);
//        System.out.println("Answer to question is number \\|/");
//        System.out.println(getWithQuiz(gameId).getPlayersAnswers().get(playerAnswersIndex).getAnswers().get(questionId));
        return questionService.get(questionId).getCorrectAnswers().get(answerIndex);
    }
}
