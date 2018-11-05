package system.model.questions;

import system.dao.Idable;

import java.io.Serializable;

public interface IQuestion extends Serializable, Idable {

    String getId();
    void setMaxPoints(double maxPoints);
    double getPointsForAnswer(IAnswer answer);
    QuestionType getQuestionType();
}
