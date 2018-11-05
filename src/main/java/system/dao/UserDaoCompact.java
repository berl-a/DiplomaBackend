package system.dao;

import system.model.users.User;

public class UserDaoCompact extends Dao<User> {

    public UserDaoCompact() {
        super("Users");
    }
}
