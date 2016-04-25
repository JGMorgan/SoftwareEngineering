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

    /**
     *
     * @param name
     * @param password
     * @return
     */
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
     * @return a JSON object with all the movies
     */
    public static String getMoviesJSON() {
        FindIterable<Document> iterable = movies.find();
        ArrayList<Document> movies = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                movies.add(document);
            }
        });
        Document out = new Document();
        int stockAsInt = 0;
        int decimalIndex = 0;
        for (int i = 0; i < movies.size(); i++) {

            try{if(movies.get(i).get("stock").toString().indexOf('.')==-1){decimalIndex = movies.get(i).get("stock").toString().length();}
                else decimalIndex = movies.get(i).get("stock").toString().indexOf('.');
                stockAsInt = Integer.parseInt(movies.get(i).get("stock").toString().substring(0,decimalIndex));}
            catch(NumberFormatException e){
                System.out.println("Number couldn't be parsed: " + movies.get(i).get("stock").toString());
            }
            if (stockAsInt>0){
                System.out.println(stockAsInt + " is the stock count for index " + i);
                out.append(movies.get(i).get("title").toString() + " at a price of $"
                        +movies.get(i).get("price").toString() + " with "
                        +movies.get(i).get("stock").toString() + " left in stock", movies.get(i));
            }
        }
        return out.toJson();
    }

    /**
     * @return a JSON object with all the sellers
     */
    public static String getSellersJSON() {
        FindIterable<Document> iterable = users.find(new Document("accounttype", "Seller"));
        ArrayList<Document> sellers = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                sellers.add(document);
            }
        });
        Document out = new Document();
        for (int i = 0; i < sellers.size(); i++){
            out.append("movies"+i, sellers.get(i));
        }
        return out.toJson();
    }

    /**
     * @return a JSON object with all the buyers
     */
    public static String getBuyersJSON() {
        FindIterable<Document> iterable = users.find(new Document("accounttype", "Buyer"));
        ArrayList<Document> buyers = new ArrayList();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                buyers.add(document);
            }
        });
        Document out = new Document();
        for (int i = 0; i < buyers.size(); i++){
            out.append("movies"+i, buyers.get(i));
        }
        return out.toJson();
    }

    /**
     *
     * @return the movie if it exists if it doesn't it will return null
     */
    public static Document getMovie(String title) {
        FindIterable<Document> iterable = movies.find(new Document("title", title));
        return iterable.first();
    }

    /**
     * Used for adding stock and when the buyer purchases a movie
     * @param title
     * @param stockUpdate
     */
    public static void updateMovieStock(String title, int stockUpdate) {
        Document movie = getMovie(title);
        System.out.println(movie.toString());
        int oldStock = Integer.parseInt(movie.get("stock").toString());
        System.out.println(oldStock);
        Document updateField = new Document("$set", new Document("stock", (oldStock + stockUpdate)));

        movies.updateOne(movie, updateField);
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
