package system.controller.dao;

import org.springframework.stereotype.Repository;
import system.model.users.User;

@Repository
public class UserDao extends Dao<User> {

    public UserDao() {
        super("Users");
    }
}
