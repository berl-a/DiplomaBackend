package system.model.questions;

import java.util.LinkedList;

public class QuestionSubSub implements QuestionGroup {

    private LinkedList<IQuestion> questions = new LinkedList<>();

    public QuestionSubSub() {
    }

    public QuestionSubSub(LinkedList<IQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public LinkedList<IQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<IQuestion> questions) {
        this.questions = questions;
    }
}
