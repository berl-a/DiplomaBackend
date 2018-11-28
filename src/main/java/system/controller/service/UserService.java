package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.dao.UserDao;
import system.model.users.IUser;
import system.model.users.User;
import system.model.users.UserType;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class UserService {

    public static final String USER_EXISTS_ERROR = "user_exists";

    @Autowired
    UserDao dao;

    private LinkedList<User> cached = new LinkedList<>();

    public void updateCached() {
        cached = dao.getAll();
    }

    public LinkedList<User> getAll() {
        updateCached();
        return cached;
    }

    public User get(String username) {
        LinkedList<User> users = getAll();
        Optional<User> foundUser = Optional.empty();
        if(users != null && users.size() != 0)
            foundUser = users.stream().filter(u -> username.equals(u.getLogin())).findAny();
        return foundUser.orElse(null);
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

    public User getById(String id) {
        LinkedList<User> users = getAll();
        Optional<User> foundUser;
            foundUser = users.stream().filter(u -> id.equals(u.getId())).findAny();
        return foundUser.orElse(null);
    }

    public boolean isPasswordCorrect(String username, String passwordHash) {
        IUser foundUser = get(username);
        return foundUser != null && foundUser.getHash().equals(passwordHash);
    }

    public String add(User user) {
        updateCached();
        String result;

        if(doesUserWithUsernameExist(user.getLogin())) {
            result = USER_EXISTS_ERROR;
        } else {
            dao.add(user);
            result = Const.OK_RESULT;
        }
        return result;
    }

    private boolean doesUserWithUsernameExist(String login) {
        updateCached();
        return get(login, null) != null;
    }

    public String edit(User user) {
        updateCached();
        dao.remove(user.getId());
        add(user);
        return Const.OK_RESULT;
    }

    public String copy(String id) {
        updateCached();
        String result;
        User existing = get(id);
        User copied = new User(existing);
        copied.changeId();
        dao.add(copied);
        result = copied.getId();
        return result;
    }

    public String remove(String id) {
        updateCached();
        dao.remove(id);
        return Const.OK_RESULT;
    }
}
