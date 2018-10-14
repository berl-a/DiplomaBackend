package system.model;

import java.io.Serializable;

public interface IQuestion extends Serializable{

    void setMaxPoints(double maxPoints);
    double getPointsForAnswer(IAnswer answer);
}
