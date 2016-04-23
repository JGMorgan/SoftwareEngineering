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
    static MongoClient client = new MongoClient("52.201.189.109", 27017);
    static MongoDatabase db = client.getDatabase("softwareengineering");
    static MongoCollection movies = db.getCollection("movies");
    static MongoCollection users = db.getCollection("users");

    /**
     * @param userName
     * @param password
     * @param firstname
     * @param lastname
     * @param accountType
     */
    public static boolean insertUser(String userName, String password, String firstname, String lastname, String accountType) {

        FindIterable<Document> iterable = users.find(new Document("username", userName));
        ArrayList<String> usernames = new ArrayList();

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                usernames.add(document.toString());
            }
        });

        if (usernames.size() >= 1)
        // explain error
        return false;
        else{
            users.insertOne(new Document()
                    .append("username", userName)
                    .append("password", password)
                    .append("firstname", firstname)
                    .append("lastname", lastname)
                    .append("accounttype", accountType));
            return true;
        }
    }

    public static Document getUser(String name, String password) {
        FindIterable<Document> iterable = users.find(new Document()
                                                .append("username", name)
                                                .append("password", password));
        return iterable.first();
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

    public static boolean userExists(String username) {
        FindIterable<Document> iterable = movies.find(new Document("username", username));
        if (iterable.first().isEmpty()) return false;
        else return true;
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

    /**
     * TODO
     * this should insert a movie into the database but we need to finalize
     * what a movie element in the database should look like
     */
    public static void insertMovie() {
        //movies.insertOne();

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
}
