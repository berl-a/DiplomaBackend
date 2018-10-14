package system.model;

import java.util.LinkedList;
import java.util.stream.Collectors;

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
