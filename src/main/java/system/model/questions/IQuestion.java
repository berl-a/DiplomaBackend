package system.model.questions;

import java.io.Serializable;

public interface IQuestion extends Serializable {

    void setMaxPoints(double maxPoints);
    double getPointsForAnswer(IAnswer answer);
    QuestionType getQuestionType();
}
