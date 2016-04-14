import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Gajen on 4/14/2016.
 */
public class Buyer extends User {

    private List<String> wishList;
    private List<String> purchaseHistory;
    private String accountType;

    public Buyer()
    {

    }

    public Buyer(String userName, String passWord)
    {
        super(userName, passWord);
        accountType = "Customer";
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
