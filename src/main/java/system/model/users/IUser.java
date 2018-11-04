package system.model.users;


import java.io.Serializable;

public interface IUser extends Serializable{

    String getId();
    String getLogin();
    String getHash();
    UserType getType();
}
