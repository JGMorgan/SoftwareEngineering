package Users;

import Users.Buyer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gajen on 4/14/2016.
 */
public class Seller extends Buyer {

    protected List<String> salesHistory;

    public Seller(String userName, String password)
    {
        super(userName, password);
        salesHistory = new ArrayList<>();
        accountType = "Seller";
    }

    public void addMovie(String movieName)
    {
        throw new UnsupportedOperationException();
    }

    public List getSalesHistory()
    {
        return salesHistory;
    }






}
