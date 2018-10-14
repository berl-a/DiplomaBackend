package system.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class FreeTextQuestionAnswer implements IAnswer{

    public FreeTextQuestionAnswer(OneAnswer answer) {
        this.answer = answer;
    }

    private OneAnswer answer;

    @Override
    public LinkedList<OneAnswer> getAnswers() {
        return new LinkedList<>(Collections.singletonList(answer));
    }

    public OneAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(OneAnswer answer) {
        this.answer = answer;
    }
}
