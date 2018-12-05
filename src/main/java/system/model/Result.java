package system.model;

import system.controller.tools.DataToolkit;
import system.model.dao.Idable;
import system.model.games.*;
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
    private LinkedList<ListOfRealQuestions> questionsForPlayers = new LinkedList<>();
    private LinkedList<PlayerAnswers> playersAnswers = new LinkedList<>();
    private LinkedList<PlayerPoints> playersPoints = new LinkedList<>();
    private LinkedList<Double> playersPointSums = new LinkedList<>();

    public Result() {
        this.id = DataToolkit.getUniqueId();
    }

    public Result(String id) {
        this.id = id;
    }

    public Result(Quiz realQuiz, Game realGame, String teacher, LinkedList<String> players, LinkedList<Player> realPlayers, LinkedList<ListOfRealQuestions> questionsForPlayers, LinkedList<PlayerAnswers> playersAnswers, LinkedList<PlayerPoints> playersPoints, LinkedList<Double> playersPointSums) {
        this.id = DataToolkit.getUniqueId();
        this.realQuiz = realQuiz;
        this.realGame = realGame;
        this.teacher = teacher;
        this.players = players;
        this.realPlayers = realPlayers;
        this.questionsForPlayers = questionsForPlayers;
        this.playersAnswers = playersAnswers;
        this.playersPoints = playersPoints;
        this.playersPointSums = playersPointSums;
    }

    public Result(String id, Quiz realQuiz, Game realGame, String teacher, LinkedList<String> players, LinkedList<Player> realPlayers, LinkedList<ListOfRealQuestions> questionsForPlayers, LinkedList<PlayerAnswers> playersAnswers, LinkedList<PlayerPoints> playersPoints, LinkedList<Double> playersPointSums) {
        this.id = id;
        this.realQuiz = realQuiz;
        this.realGame = realGame;
        this.teacher = teacher;
        this.players = players;
        this.realPlayers = realPlayers;
        this.questionsForPlayers = questionsForPlayers;
        this.playersAnswers = playersAnswers;
        this.playersPoints = playersPoints;
        this.playersPointSums = playersPointSums;
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

    public LinkedList<ListOfRealQuestions> getQuestionsForPlayers() {
        return questionsForPlayers;
    }

    public void setQuestionsForPlayers(LinkedList<ListOfRealQuestions> questionsForPlayers) {
        this.questionsForPlayers = questionsForPlayers;
    }

    public LinkedList<PlayerAnswers> getPlayersAnswers() {
        return playersAnswers;
    }

    public void setPlayersAnswers(LinkedList<PlayerAnswers> playersAnswers) {
        this.playersAnswers = playersAnswers;
    }

    public LinkedList<PlayerPoints> getPlayersPoints() {
        return playersPoints;
    }

    public void setPlayersPoints(LinkedList<PlayerPoints> playersPoints) {
        this.playersPoints = playersPoints;
    }

    public LinkedList<Double> getPlayersPointSums() {
        return playersPointSums;
    }

    public void setPlayersPointSums(LinkedList<Double> playersPointSums) {
        this.playersPointSums = playersPointSums;
    }
}
