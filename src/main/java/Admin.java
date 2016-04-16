/**
 * Created by Gajen on 4/16/2016.
 */
public class Admin extends User{

    private String accountType;

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
