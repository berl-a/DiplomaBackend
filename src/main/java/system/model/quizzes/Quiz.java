package system.model.quizzes;

import system.model.questions.IQuestion;

import java.util.LinkedList;

public class Quiz {

    LinkedList<QuizPart> parts = new LinkedList<>();

    public Quiz() {
    }

    public Quiz(LinkedList<QuizPart> parts) {
        this.parts = parts;
    }

    public LinkedList<QuizPart> getParts() {
        return parts;
    }

    public void setParts(LinkedList<QuizPart> parts) {
        this.parts = parts;
    }
}
