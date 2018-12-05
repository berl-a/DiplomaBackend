package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.model.dao.UserDao;
import system.model.users.IUser;
import system.model.users.User;
import system.model.users.UserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

import static system.controller.Const.SETTINGS_FILE_LOCATION;
import static system.controller.Const.USER_EXISTS_ERROR;

@Service
public class UserService {

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
        if(username == null) return null;

        LinkedList<User> users = getAll();
        Optional<User> foundUser = Optional.empty();
        if(users != null && users.size() != 0)
            foundUser = users.stream().filter(u -> u != null && username.equals(u.getLogin())).findAny();
        return foundUser.orElse(null);
    }

    public User get(String username, UserType type) {
        LinkedList<User> users = getAll();
        Optional<User> foundUser;
        if(type != null)
           foundUser = users.stream().filter(u -> u.getType() == type && username.equals(u.getLogin())).findAny();
        else
            foundUser = users.stream().filter(u -> u != null && username.equals(u.getLogin())).findAny();
        return foundUser.orElse(null);
    }

    public User getById(String id) {
        LinkedList<User> users = getAll();
        Optional<User> foundUser;
            foundUser = users.stream().filter(u -> u != null && id.equals(u.getId())).findAny();
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

    public void addAdminToDatabase() {
        String username = "admin";
        String password = "pass";
        User user = get(username);
        if(user != null) {
            try {
                Scanner sc = new Scanner(new File(SETTINGS_FILE_LOCATION));
                for(int i = 0; i < 5; i ++) {
                    if(sc.hasNext())
                        sc.nextLine();
                }
                if(sc.hasNext())
                    password = sc.nextLine();

                sc.close();
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                System.out.println("User settings file not found");
            }
            user.setHash(String.valueOf(password.hashCode()));
            user.setType(UserType.ADMINISTRATOR);
            edit(user);
        } else {
            user = new User(username,  String.valueOf(password.hashCode()), UserType.ADMINISTRATOR);
            add(user);
        }

    }
}
