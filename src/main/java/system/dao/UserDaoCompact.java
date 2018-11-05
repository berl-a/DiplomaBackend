package system.dao;

import org.springframework.stereotype.Repository;
import system.model.users.User;

@Repository
public class UserDaoCompact extends Dao<User> {

    public UserDaoCompact() {
        super("Users");
    }
}
