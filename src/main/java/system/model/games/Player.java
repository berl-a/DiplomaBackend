package system.model.games;

import system.dao.Idable;

public class Player implements Idable {

    private String id;

    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
