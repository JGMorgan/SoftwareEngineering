package Users;

import DatabaseOperations.DatabaseConnector;
import Users.Buyer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Seller extends Buyer {

    protected ArrayList<String> salesHistory;

    public Seller(String userName, String password) {
        super(userName, password);
        salesHistory = new ArrayList<>();
        accountType = "Seller";
    }

    /*public void addMovie(String movieTitle) {
        DatabaseConnector.insertMovie();
    }*/

    public ArrayList getSalesHistory() {
        return salesHistory;
    }
}
