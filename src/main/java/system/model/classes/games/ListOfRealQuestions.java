package system.model.classes.games;

import system.model.classes.questions.Question;

import java.io.Serializable;
import java.util.LinkedList;

public class ListOfRealQuestions implements Serializable {

    private LinkedList<Question> questions;

    public ListOfRealQuestions() {
    }

    public ListOfRealQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }
}
