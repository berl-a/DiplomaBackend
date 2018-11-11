package system.model.games;

import org.springframework.beans.factory.annotation.Autowired;
import system.controller.tools.DataToolkit;
import system.dao.Idable;
import system.service.GameService;

import java.io.Serializable;
import java.util.LinkedList;

public class Game implements Idable, Serializable {

    private String id;
    private String quiz;
    private String name;
    private String code;

    private LinkedList<String> players = new LinkedList<>();
    private LinkedList<ListOfQuestions> questionsForPlayers = new LinkedList<>();

    private long fullTime;
    private int numberOfQuestions;
    private long startTime, oneQuestionTime;

    public Game() {
        this.id = DataToolkit.getUniqueId();
    }

    public Game(String id, String quiz, String name, String code, LinkedList<String> players, LinkedList<ListOfQuestions> questionsForPlayers, long fullTime, int numberOfQuestions, long startTime, long oneQuestionTime) {
        this.id = id;
        this.quiz = quiz;
        this.name = name;
        this.code = code;
        this.players = players;
        this.questionsForPlayers = questionsForPlayers;
        this.fullTime = fullTime;
        this.numberOfQuestions = numberOfQuestions;
        this.startTime = startTime;
        this.oneQuestionTime = oneQuestionTime;
    }

    public Game(Game g) {
        this.id = g.id;
        this.quiz = g.quiz;
        this.name = g.name;
        this.code = g.code;
        this.players = g.players;
        this.questionsForPlayers = g.questionsForPlayers;
        this.fullTime = g.fullTime;
        this.numberOfQuestions = g.numberOfQuestions;
        this.startTime = g.startTime;
        this.oneQuestionTime = g.oneQuestionTime;
    }

    public Game(String quizId) {
        this.id = DataToolkit.getUniqueId();
        this.quiz = quizId;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LinkedList<String> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<String> players) {
        this.players = players;
    }

    public LinkedList<ListOfQuestions> getQuestionsForPlayers() {
        return questionsForPlayers;
    }

    public void setQuestionsForPlayers(LinkedList<ListOfQuestions> questionsForPlayers) {
        this.questionsForPlayers = questionsForPlayers;
    }

    public long getFullTime() {
        return fullTime;
    }

    public void setFullTime(long fullTime) {
        this.fullTime = fullTime;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getOneQuestionTime() {
        return oneQuestionTime;
    }

    public void setOneQuestionTime(long oneQuestionTime) {
        this.oneQuestionTime = oneQuestionTime;
    }
}
