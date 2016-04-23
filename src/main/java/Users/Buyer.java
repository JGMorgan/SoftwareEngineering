package Users;

import Users.User;

import java.util.*;
import java.util.List;

import DatabaseOperations.DatabaseConnector;

import javax.swing.text.Document;

/**
 * Created by Gajen on 4/14/2016.
 */
public class Buyer extends User {

    protected ArrayList<String> wishList;
    protected ArrayList<String> purchaseHistory;

    public Buyer(String userName, String password) { super(userName, password); }

    public Buyer(String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);
        accountType = "Buyer";
        wishList = new ArrayList<>();
        purchaseHistory = new ArrayList<>();
    }


    public void purchase(String movieTitle) {
        DatabaseConnector.updateMovieStock(movieTitle, -1);
    }

    public String viewMovie(String movieTitle) {
        return DatabaseConnector.getMovie(movieTitle);
    }

    public String getAccountType() {
        return accountType;
    }

    public List getWishList() {
        return wishList;
    }

    public List getPurchaseHistory() {
        return purchaseHistory;
    }

}
