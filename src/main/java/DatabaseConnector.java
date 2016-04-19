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
    MongoClient client;
    MongoDatabase db;
    MongoCollection movies;
    MongoCollection users;

    public DatabaseConnector() {
        client = new MongoClient("localhost", 27017);
        db = client.getDatabase("softwareegineering");
        movies = db.getCollection("movies");
        users = db.getCollection("users");
    }

    public void insertMovie() {
        //movies.insertOne();

    }

    /**
     * @param userName
     * @param password
     * @param firstname
     * @param lastname
     * @param accountType
     */
    public void insertUser(String userName, String password, String firstname, String lastname, String accountType) {
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
    public ArrayList<Document> getMovies() {
        FindIterable<Document> iterable = movies.find();
        ArrayList<Document> movies = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                movies.add(document);
            }
        });
        return movies;
    }

    /**
     * @return an arraylist with all the users
     */
    public ArrayList<Document> getUsers() {
        FindIterable<Document> iterable = movies.find();
        ArrayList<Document> users = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                users.add(document);
            }
        });
        return users;
    }

    /**
     * This method is for testing
     */
    public void displayMovies() {
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
    public void displayUsers() {
        FindIterable<Document> iterable = users.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
}
