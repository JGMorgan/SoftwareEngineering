/**
 * Created by Gajen on 4/14/2016.
 */



public class User {

    private String userName;
    private String password;

    public User()
    {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUserName(String newUserName)
    {
        userName = newUserName;
    }

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }
}
