package system.model.users;

public class Administrator implements IUser {

    private String login, passwordHash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public UserType getType() {
        return UserType.ADMINISTRATOR;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
