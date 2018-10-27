package system.model.questions;

import java.util.LinkedList;

public class QuestionCategory implements QuestionGroup {

    private LinkedList<QuestionSub> subs;

    public QuestionCategory() {
        subs = new LinkedList<>();
    }

    @Override
    public LinkedList<IQuestion> getQuestions() {
        LinkedList<IQuestion> questionsCombined = new LinkedList<>();
        subs.forEach(sub -> questionsCombined.addAll(sub.getQuestions()));
        return questionsCombined;
    }

    public LinkedList<QuestionSub> getSubs() {
        return subs;
    }

    public void setSubs(LinkedList<QuestionSub> subs) {
        this.subs = subs;
    }
}
