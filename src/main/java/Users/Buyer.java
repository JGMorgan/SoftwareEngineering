package Users;

import Users.User;

import java.util.*;
import java.util.List;

import DatabaseOperations.DatabaseConnector;

import javax.swing.text.Document;


public class Buyer extends User {

    protected ArrayList<String> wishList;
    protected ArrayList<String> purchaseHistory;

    public Buyer(String userName, String password) { super(userName, password); }

    public Buyer(String userName, String password, String firstName, String lastName, String email) {
        super(userName, password, firstName, lastName, email);
        accountType = "Buyer";
//        wishList = new ArrayList<>();
//        purchaseHistory = new ArrayList<>();

        DatabaseConnector.updateUserType(userName, accountType);
    }

    public void purchase(String movieTitle) { DatabaseConnector.updateMovieStock(movieTitle, -1); }

    public String viewMovie(String movieTitle) { return DatabaseConnector.getMovie(movieTitle); }

    public String getAccountType() { return accountType; }

    public List getWishList() { return wishList; }

    public List getPurchaseHistory() { return purchaseHistory; }
}
