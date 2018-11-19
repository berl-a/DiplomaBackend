package system.model.games;

import java.io.Serializable;
import java.util.LinkedList;

public class ListOfQuestions implements Serializable {

    private LinkedList<String> questionIds;

    public ListOfQuestions() {
    }

    public ListOfQuestions(LinkedList<String> questionIds) {
        this.questionIds = questionIds;
    }

    public LinkedList<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(LinkedList<String> questionIds) {
        this.questionIds = questionIds;
    }
}
