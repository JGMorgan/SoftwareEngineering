package Users;

/**
 * Created by Gajen on 4/14/2016.
 */



public class User {

    protected String userName;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String accountType;

    /**
     * Use this constructor for Registration
     * @param userName
     * @param password
     * @param firstname
     * @param lastname
     */
    public User(String userName, String password, String firstname, String lastname)
    {
        /*
        * TODO
        * add user to the database
        * */
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Use this constructor for login
     * @param userName
     * @param password
     */
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