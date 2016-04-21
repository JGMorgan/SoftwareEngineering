package DatabaseOperations;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by jose on 4/18/16.
 */
public class DatabaseConnector {
    static MongoClient client = new MongoClient("localhost", 27017);
    static MongoDatabase db = client.getDatabase("softwareegineering");
    static MongoCollection movies = db.getCollection("movies");
    static MongoCollection users = db.getCollection("users");

    private DatabaseConnector(){}

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
