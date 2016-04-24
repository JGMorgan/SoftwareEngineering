import DatabaseOperations.DatabaseConnector;

/**
 * Created by jose on 4/23/16.
 */
public class Movie {
    private String title;
    private String year;
    private String genre;
    private int stock;
    private float price;

    public Movie(String title, String year, String genre, int stock, float price){
        DatabaseConnector.insertMovie(title, year, genre, stock, price);
    }
}
