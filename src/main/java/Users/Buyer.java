package Users;

import Users.User;

import java.util.*;
import java.util.List;

/**
 * Created by Gajen on 4/14/2016.
 */
public class Buyer extends User {

    protected List<String> wishList;
    protected List<String> purchaseHistory;



    public Buyer(String userName, String password)
    {
        super(userName, password);
        accountType = "Buyer";
        wishList = new ArrayList<>();
        purchaseHistory = new ArrayList<>();
    }

    public void purchase()
    {
        throw new UnsupportedOperationException();
    }

    public void viewMovie()
    {
        throw new UnsupportedOperationException();
    }

    public String getAccountType()
    {
        return accountType;
    }

    public List getWishList()
    {
        return wishList;
    }

    public List getPurchaeHistory()
    {
        return purchaseHistory;
    }





}
