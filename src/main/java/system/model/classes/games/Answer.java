package system.model.classes.games;

import java.io.Serializable;

public interface Answer extends Serializable {

    Double getCorrect();

    void setCorrect(Double correct);
}
