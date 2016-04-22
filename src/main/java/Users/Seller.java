package Users;

import DatabaseOperations.DatabaseConnector;
import Users.Buyer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gajen on 4/14/2016.
 */
public class Seller extends Buyer {

    protected ArrayList<String> salesHistory;
    protected ArrayList<String> moviesList = DatabaseConnector.getMovies();

    public Seller(String userName, String password)
    {
        super(userName, password);
        salesHistory = new ArrayList<>();
        accountType = "Seller";
    }

    public void addMovie(String movieTitle)
    {
        moviesList.add(movieTitle);
    }

    public ArrayList getSalesHistory()
    {
        return salesHistory;
    }






}
