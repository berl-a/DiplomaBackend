package system.model.games;

import system.dao.Idable;

import java.util.LinkedList;

public class Game implements Idable {

    private String quiz;
    private LinkedList<Integer> players = new LinkedList<>();
    private LinkedList<ListOfQuestions> questionsForPlayers = new LinkedList<>();

    private String id;
    private long fullTime;
    private int numberOfQuestions;
    private long startTime, oneQuestionTime;

    public Game() {
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public LinkedList<Integer> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Integer> players) {
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LinkedList<ListOfQuestions> getQuestionsForPlayers() {
        return questionsForPlayers;
    }

    public void setQuestionsForPlayers(LinkedList<ListOfQuestions> questionsForPlayers) {
        this.questionsForPlayers = questionsForPlayers;
    }
}
