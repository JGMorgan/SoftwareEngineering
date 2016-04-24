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
     * @param email
     */
    public static void insertUser(String userName, String password, String firstname, String lastname, String email) {
            users.insertOne(new Document()
                    .append("username", userName)
                    .append("password", password)
                    .append("firstname", firstname)
                    .append("lastname", lastname)
                    .append("email", email));
    }

    /**
     *
     * @param username
     * @param accountType
     */
    public static void updateUserType(String username, String accountType){
        Document user = new Document("username", username);
        Document updateField = new Document("$set", new Document("accounttype", accountType));
        users.updateOne(user, updateField);
    }

    /**
     *
     * @param name
     * @param password
     * @return
     */
    /**
     *
     * @return Arraylist with all the names of the Sellers
     */
    public static ArrayList<String> getSellers(){
        FindIterable<Document> iterable = users.find(new Document("accountType", "Seller"));
        ArrayList<String> users = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                users.add(document.get("username").toString());
            }
        });
        return users;
    }

    /**
     *
     * @return Arraylist with all the names of the Buyers
     */
    public static ArrayList<String> getBuyers(){
        FindIterable<Document> iterable = users.find(new Document("accountType", "Buyer"));
        ArrayList<String> users = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                users.add(document.get("username").toString());
            }
        });
        return users;
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

    /**
     *
     * @param username
     * @return
     */
    public static Document userExists(String username) {
        FindIterable<Document> iterable = users.find(new Document("username", username));
        return iterable.first();
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
    public static void insertMovie(String title, String year, String genre, int stock, float price) {
        movies.insertOne(new Document()
                            .append("title", title)
                            .append("year", year)
                            .append("genre", genre)
                            .append("stock", stock)
                            .append("price", price));

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
