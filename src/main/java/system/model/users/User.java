package system.model.users;

import system.controller.tools.DataToolkit;
import system.model.dao.Idable;

public class User implements IUser, Idable{

    private String id, login, hash;
    private UserType type;

    public User() {
        this.id = DataToolkit.getUniqueId();
    }

    public User(User u) {
        this.id = u.id;
        this.login = u.login;
        this.hash = u.hash;
        this.type = u.type;
    }

    public User(String id, String login, String hash, UserType type) {
        this.id = id;
        this.login = login;
        this.hash = hash;
        this.type = type;
    }

    public User(String login, String hash, UserType type) {
        id = DataToolkit.getUniqueId();
        this.login = login;
        this.hash = hash;
        this.type = type;
    }

    public void changeId() {
        this.id = DataToolkit.getUniqueId();
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
