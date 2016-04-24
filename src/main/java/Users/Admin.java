package Users;


public class Admin extends User {

    public Admin(String userName, String password)
    {
        super(userName, password);
        accountType = "Admin";
    }

    public void AddMovieData()
    {
        throw new UnsupportedOperationException();
    }

    public void SetAccountType()
    {
        throw new UnsupportedOperationException();
    }


}
