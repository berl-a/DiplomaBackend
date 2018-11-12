package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.model.games.Game;
import system.model.games.Player;
import system.model.questions.Question;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

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

    private LinkedList<Game> games = new LinkedList<>();

    public LinkedList<Game> getAll() {
        return games;
    }

    public GameWithActualQuiz addGameFromQuiz(String quizId) {
        Game game = new Game(quizId);
        game.setCode(generateGameCode());
        games.add(game);
        return getGameWithActualQuiz(game);
    }

    public GameWithActualQuiz get(String gameId) {
        Optional<Game> foundGame = getAll().stream().filter(g -> gameId.equals(g.getId())).findAny();
        return getGameWithActualQuiz(foundGame.orElse(null));
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

    public LinkedList<Question> getQuestionsForPlayer(String gameId, String playerId) {
        return null;//todo add method
    }

    public void startGame(String gameId) {
        games.stream().filter(g -> gameId.equals(g.getId())).forEach(g -> {
//            System.out.println("Set time of game " + g.getId() + " to " + System.currentTimeMillis());
            g.setStartTime(System.currentTimeMillis());
        });
    }
}
