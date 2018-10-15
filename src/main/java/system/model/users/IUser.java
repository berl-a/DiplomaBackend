package system.model.users;


import java.io.Serializable;

public interface IUser extends Serializable{

    String getLogin();
    String getPasswordHash();
    UserType getType();
}
