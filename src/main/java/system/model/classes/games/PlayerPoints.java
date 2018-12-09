package system.model.classes.games;

import java.io.Serializable;
import java.util.LinkedList;

public class PlayerPoints implements Serializable {

    private LinkedList<Double> points;

    public PlayerPoints() {
    }

    public PlayerPoints(LinkedList<Double> points) {
        this.points = points;
    }

    public LinkedList<Double> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Double> points) {
        this.points = points;
    }
}
