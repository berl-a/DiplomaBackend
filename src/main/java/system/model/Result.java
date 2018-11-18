package system.model;

import system.controller.tools.DataToolkit;
import system.dao.Idable;
import system.model.games.Game;
import system.model.games.ListOfQuestions;
import system.model.games.Player;
import system.model.games.PlayerAnswers;
import system.model.quizzes.Quiz;

import java.io.Serializable;
import java.util.LinkedList;

public class Result implements Idable, Serializable {

    private String id;
    private Quiz realQuiz;
    private Game realGame;
    private String teacher;

    private LinkedList<String> players = new LinkedList<>();
    private LinkedList<Player> realPlayers = new LinkedList<>();
    private LinkedList<ListOfQuestions> questionsForPlayers = new LinkedList<>();
    private LinkedList<PlayerAnswers> playersAnswers = new LinkedList<>();

    public Result() {
        this.id = DataToolkit.getUniqueId();
    }

    public Result(String id) {
        this.id = id;
    }

    public Result(Quiz realQuiz, Game realGame, String teacher, LinkedList<String> players, LinkedList<Player> realPlayers, LinkedList<ListOfQuestions> questionsForPlayers, LinkedList<PlayerAnswers> playersAnswers) {
        this.id = DataToolkit.getUniqueId();
        this.realQuiz = realQuiz;
        this.realGame = realGame;
        this.teacher = teacher;
        this.players = players;
        this.realPlayers = realPlayers;
        this.questionsForPlayers = questionsForPlayers;
        this.playersAnswers = playersAnswers;
    }

    public Result(String id, Quiz realQuiz, Game realGame, String teacher, LinkedList<String> players, LinkedList<Player> realPlayers, LinkedList<ListOfQuestions> questionsForPlayers, LinkedList<PlayerAnswers> playersAnswers) {
        this.id = id;
        this.realQuiz = realQuiz;
        this.realGame = realGame;
        this.teacher = teacher;
        this.players = players;
        this.realPlayers = realPlayers;
        this.questionsForPlayers = questionsForPlayers;
        this.playersAnswers = playersAnswers;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Quiz getRealQuiz() {
        return realQuiz;
    }

    public void setRealQuiz(Quiz realQuiz) {
        this.realQuiz = realQuiz;
    }

    public Game getRealGame() {
        return realGame;
    }

    public void setRealGame(Game realGame) {
        this.realGame = realGame;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LinkedList<String> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<String> players) {
        this.players = players;
    }

    public LinkedList<Player> getRealPlayers() {
        return realPlayers;
    }

    public void setRealPlayers(LinkedList<Player> realPlayers) {
        this.realPlayers = realPlayers;
    }

    public LinkedList<ListOfQuestions> getQuestionsForPlayers() {
        return questionsForPlayers;
    }

    public void setQuestionsForPlayers(LinkedList<ListOfQuestions> questionsForPlayers) {
        this.questionsForPlayers = questionsForPlayers;
    }

    public LinkedList<PlayerAnswers> getPlayersAnswers() {
        return playersAnswers;
    }

    public void setPlayersAnswers(LinkedList<PlayerAnswers> playersAnswers) {
        this.playersAnswers = playersAnswers;
    }
}
