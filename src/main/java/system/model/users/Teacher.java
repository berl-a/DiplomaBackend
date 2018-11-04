package system.model.users;


import system.controller.tools.DataToolkit;

public class Teacher implements IUser {

    private String id, login, hash;

    public Teacher(String id, String login, String hash) {
        this.id = id;
        this.login = login;
        this.hash = hash;
    }

    public Teacher(String login, String hash) {
        this.login = login;
        this.hash = hash;
        id = DataToolkit.getUniqueId();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public UserType getType() {
        return UserType.TEACHER;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setId(String id) {
        this.id = id;
    }
}
