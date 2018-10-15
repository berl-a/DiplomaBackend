package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.users.IUser;

import java.util.LinkedList;

@Service
public class UserService {

    @Autowired
    UserDao questionDao;

    private LinkedList<IUser> cachedIUsers = new LinkedList<>();

    public void updateCachedUsers() {
        cachedIUsers = questionDao.getUsers();
    }

    public LinkedList<IUser> getUsers() {
        updateCachedUsers();
        return cachedIUsers;
    }
}
