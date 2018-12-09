package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.users.User;

@Repository
public class UserDao extends Dao<User> {

    public UserDao() {
        super("Users");
    }
}
