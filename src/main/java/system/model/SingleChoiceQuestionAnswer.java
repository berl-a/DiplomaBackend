package system.model;

import java.util.Collections;
import java.util.LinkedList;

public class SingleChoiceQuestionAnswer implements IAnswer {

    public SingleChoiceQuestionAnswer(OneAnswer answer) {
        this.answer = answer;
    }

    private OneAnswer answer;

    public OneAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(OneAnswer answer) {
        this.answer = answer;
    }

    @Override
    public LinkedList<OneAnswer> getAnswers() {
        return new LinkedList<>(Collections.singletonList(answer));
    }
}
