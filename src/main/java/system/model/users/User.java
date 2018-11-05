package system.model.users;

import system.controller.tools.DataToolkit;
import system.dao.Idable;

public class User implements IUser, Idable{

    private String id, login, hash;
    private UserType type;

    public User(String id, String login, String hash, UserType type) {
        this.id = id;
        this.login = login;
        this.hash = hash;
        this.type = type;
    }

    public User(String login, String hash, UserType type) {
        this.login = login;
        this.hash = hash;
        this.type = type;
        id = DataToolkit.getUniqueId();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
