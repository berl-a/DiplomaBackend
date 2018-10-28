package system.model.games;

import java.util.LinkedList;

public class ListOfQuestions {

    private LinkedList<String> questionIds;

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
