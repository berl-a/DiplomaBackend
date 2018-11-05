package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.UserDao;
import system.model.users.IUser;
import system.model.users.User;
import system.model.users.UserType;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class UserService {

    public static final String USER_EXISTS_ERROR = "user_exists";

//    @Autowired
//    UserDao userDao;
    @Autowired
UserDao userDao;


    private LinkedList<User> cached = new LinkedList<>();

    public void updateCachedUsers() {
        cached = userDao.getAll();
    }

    public LinkedList<User> getAll() {
        updateCachedUsers();
        return cached;
    }

    public User get(String username, UserType type) {
        LinkedList<User> users = getAll();
        Optional<User> foundUser;
        if(type != null)
           foundUser = users.stream().filter(u -> u.getType() == type && username.equals(u.getLogin())).findAny();
        else
            foundUser = users.stream().filter(u -> username.equals(u.getLogin())).findAny();
        return foundUser.orElse(null);
    }

    public boolean isPasswordCorrect(String username, String passwordHash, UserType userType) {
        IUser foundUser = get(username, userType);
        return foundUser != null && foundUser.getHash().equals(passwordHash);
    }

    public String add(String login, String hash, UserType userType) {
        updateCachedUsers();
        String result;

        if(doesUserWithUsernameExist(login)) {
            result = USER_EXISTS_ERROR;
        } else {
            User newUser;
            if (userType == UserType.TEACHER) {
                newUser = new User(login, hash, UserType.TEACHER);
            } else {
                newUser = new User(login, hash, UserType.ADMINISTRATOR);
            }
            userDao.add(newUser);
            result = Const.OK_RESULT;
        }
        return result;
    }

    private boolean doesUserWithUsernameExist(String login) {
        updateCachedUsers();
        return get(login, null) != null;
    }

    public String edit(String id, String hash, UserType userType) {
        updateCachedUsers();
        userDao.remove(id);
        add(id, hash, userType);
        return Const.OK_RESULT;
    }
}
