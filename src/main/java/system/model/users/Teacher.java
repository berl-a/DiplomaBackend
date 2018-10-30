package system.model.users;


public class Teacher implements IUser {

    private String login, hash;

    public Teacher(String login, String hash) {
        this.login = login;
        this.hash = hash;
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
}
