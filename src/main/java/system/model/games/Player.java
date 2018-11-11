package system.model.games;

import system.controller.tools.DataToolkit;
import system.dao.Idable;

import javax.xml.crypto.Data;
import java.io.Serializable;

public class Player implements Idable, Serializable {

    private String id;
    private String name;

    public Player() {
        this.id = DataToolkit.getUniqueId();
    }

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
