package system.controller.simple_frontend_models;

public class FUser {

    String login, hash;
    boolean isTeacher;

    public FUser(String login, String hash, boolean isTeacher) {
        this.login = login;
        this.hash = hash;
        this.isTeacher = isTeacher;
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

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }




}
