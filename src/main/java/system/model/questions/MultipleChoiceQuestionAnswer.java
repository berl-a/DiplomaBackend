package system.model.questions;

import java.util.LinkedList;

public class MultipleChoiceQuestionAnswer implements IAnswer{

    public MultipleChoiceQuestionAnswer(LinkedList<OneAnswer> answers) {
        this.answers = answers;
    }

    private LinkedList<OneAnswer> answers = new LinkedList<>();

    public LinkedList<OneAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedList<OneAnswer> answers) {
        this.answers = answers;
    }

}
