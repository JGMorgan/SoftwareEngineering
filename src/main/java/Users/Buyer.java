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
    protected ArrayList<org.bson.Document> moviesList = DatabaseConnector.getMovies();


    public Buyer(String userName, String password)
    {
        super(userName, password);
        accountType = "Buyer";
        wishList = new ArrayList<>();
        purchaseHistory = new ArrayList<>();
    }

    public void purchase(org.bson.Document movieTitle)
    {
        for(int i = 0; i < moviesList.size(); i++)
        {
            if(moviesList.contains(movieTitle))
            {
                //Remove 1 from the amount of that particular movie, if the result is zero, remove that movie from the list
            }
        }
    }

    public org.bson.Document viewMovie(org.bson.Document movieTitle)
    {
        for(int i = 0; i < moviesList.size(); i++)
        {
            if(moviesList.contains(movieTitle))
            {
                return movieTitle;
            }
        }
        //display error: sorry, that movie is no longer being sold
        return null;

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
