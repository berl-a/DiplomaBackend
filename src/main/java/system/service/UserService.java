package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.users.Administrator;
import system.model.users.IUser;
import system.model.users.Teacher;
import system.model.users.UserType;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class UserService {

    public static final String USER_EXISTS_ERROR = "user_exists";
    public static final String OK_RESULT = "ok";

    @Autowired
    UserDao userDao;

    private LinkedList<IUser> cachedIUsers = new LinkedList<>();

    public void updateCachedUsers() {
        cachedIUsers = userDao.getUsers();
    }

    public LinkedList<IUser> getUsers() {
        updateCachedUsers();
        return cachedIUsers;
    }

    public IUser getUser(String username, UserType type) {
        LinkedList<IUser> users = getUsers();
        Optional<IUser> foundUser;
        if(type != null)
           foundUser = users.stream().filter(u -> u.getType() == type && username.equals(u.getLogin())).findAny();
        else
            foundUser = users.stream().filter(u -> username.equals(u.getLogin())).findAny();
        return foundUser.orElse(null);
    }

    public boolean isPasswordCorrect(String username, String passwordHash, UserType userType) {
        IUser foundUser = getUser(username, userType);
        return foundUser != null && foundUser.getHash().equals(passwordHash);
    }

    public String addUser(String login, String hash, UserType userType) {
        updateCachedUsers();
        String result;

        if(doesUserWithUsernameExist(login)) {
            result = USER_EXISTS_ERROR;
        } else {
            IUser newUser;
            if (userType == UserType.TEACHER) {
                newUser = new Teacher(login, hash);
            } else {
                newUser = new Administrator(login, hash);
            }
            userDao.addUser(newUser);
            result = OK_RESULT;
        }
        return result;
    }

    private boolean doesUserWithUsernameExist(String login) {
        updateCachedUsers();
        return getUser(login, null) != null;
    }

    public String editUser(String login, String hash, UserType userType) {
        updateCachedUsers();
        userDao.removeUser(login);
        addUser(login, hash, userType);
        return null;
    }
}
