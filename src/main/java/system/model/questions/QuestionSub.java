package system.model.questions;

import java.util.LinkedList;

public class QuestionSub implements QuestionGroup {

    private LinkedList<QuestionSubSub> subSubs;

    @Override
    public LinkedList<IQuestion> getQuestions() {
        LinkedList<IQuestion> questionsCombined = new LinkedList<>();
        subSubs.forEach(sub -> questionsCombined.addAll(sub.getQuestions()));
        return questionsCombined;
    }
}
