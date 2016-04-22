package DatabaseOperations;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * This is a utility class similar to Java's math class
 * so all of the methods are static
 */
public class DatabaseConnector {
    static MongoClient client = new MongoClient("localhost", 27017);
    static MongoDatabase db = client.getDatabase("softwareegineering");
    static MongoCollection movies = db.getCollection("movies");
    static MongoCollection users = db.getCollection("users");


    /**
     * TODO
     * this should insert a movie into the database but we need to finalize
     * what a movie element in the database should look like
     */
    public static void insertMovie() {
        //movies.insertOne();

    }

    /**
     * @param userName
     * @param password
     * @param firstname
     * @param lastname
     * @param accountType
     */
    public static void insertUser(String userName, String password, String firstname, String lastname, String accountType) {
        users.insertOne(new Document()
                .append("username", userName)
                .append("password", password)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("accounttype", accountType));
    }

    /**
     * @return an arraylist with all the movies
     */
    public static ArrayList<String> getMovies() {
        FindIterable<Document> iterable = movies.find();
        ArrayList<String> movies = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                movies.add(document.toString());
            }
        });
        return movies;
    }

    /**
     *
     * @return the movie if it exists if it doesn't it will return null
     */
    public static String getMovie(String title) {
        FindIterable<Document> iterable = movies.find(new Document("name", title));
        return iterable.first().toString();
    }

    /**
     * Used for adding stock and when the buyer purchases a movie
     * @param title
     * @param stock
     */
    public static void updateMovieStock(String title, int stock) {
        movies.findOneAndUpdate(new Document("name", title), new Document());
        //replace this with an updated stock
    }

    /**
     * @return an arraylist with all the users
     */
    public static ArrayList<String> getUsers() {
        FindIterable<Document> iterable = movies.find();
        ArrayList<String> users = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                users.add(document.toString());
            }
        });
        return users;
    }

    /**
     * This method is for testing
     */
    public static void displayMovies() {
        FindIterable<Document> iterable = movies.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }

    /**
     * This method is for testing
     */
    public static void displayUsers() {
        FindIterable<Document> iterable = users.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
}
