/*Subsystem Driver Class*/

import DatabaseOperations.DatabaseConnector;
import Users.Buyer;
import com.mongodb.MongoClient;

import static spark.Spark.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.Block;

import java.util.ArrayList;

public class Main {

    private static final String SESSION_NAME = "username";
    private static final String SESSION_TYPE = "accounttype";

    public static void main(String[] args) {
        staticFileLocation("public");
        port(8000);
        System.out.println(DatabaseConnector.getBuyers());
        createRoutes();

    }

    /**
     * sets up routes to connect to the front end
     */
    public static void createRoutes() {
        /**
         * returns the username or the string "username"
         * if the string is "username" then the user is not logged in
         * otherwise it will return the user's username
         */
        get("/username", (request, response) -> {
            String name = request.session().attribute(SESSION_NAME);

            System.out.println("SESSION NAME EXISTS " + name);
            if (name != null) {
                return name;
            } else {
                return SESSION_NAME;
            }

        });

        /**
         * returns account type to the front end
         */
        get("/accounttype", (request, response) -> {
            return request.session().attribute(SESSION_TYPE);
        });

        /**
         *This will return an array of movies that can be shown
         * to the user in the front end
         */
        get("/movies", (request, response) -> {
            return DatabaseConnector.getMoviesJSON();
        });

        /**
         * Posts the username and password from the front end
         * to the Java backend
         */
        post("/login", (request, response) -> {
            String name = request.queryParams("name");
            String pass = request.queryParams("pass");
            System.out.println(name);
            System.out.println(pass);
            Document user = DatabaseConnector.getUser(name,pass);
            if (user == null){
                request.session().attribute(SESSION_NAME, "/invalid/");
            }else{
                request.session().attribute(SESSION_NAME, name);
                request.session().attribute(SESSION_TYPE, user.get("accounttype"));
            }
            response.redirect("/");
            return null;
        });

        post("/buy", (request, response) -> {
            String movieName = request.queryParams("buy");
            System.out.println(movieName);
            DatabaseConnector.updateMovieStock(movieName, -1);
            response.redirect("/");
            return null;
        });

        /**
         * Posts user to logout when needed
         */
        post("/logout", (request, response) -> {

            request.session().attribute(SESSION_NAME, null);
            response.redirect("/");
            return null;
        });

//        get("/register", (request, response) -> {
//            String username = request.queryParams("user_name");
//            String email = request.queryParams("email");
//
//            if(DatabaseConnector.userExists(username, email))
//                request.session().attribute(SESSION_NAME, "/invalidRegistration");
//
//            return null;
//        });

        /**
         * Posts registration information from the front end
         * to the Java backend
         */
        post("/register", (request, response) -> {
            String username = request.queryParams("user_name");
            String fname = request.queryParams("first_name");
            String lname = request.queryParams("last_name");
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String passconfirm = request.queryParams("password_confirmation");

            Document user = DatabaseConnector.userExists(username);
            if(user == null && pass.equals(passconfirm)) {
                new Buyer(username, pass, fname, lname, email);
                System.out.println(fname);
                System.out.println(pass);
                request.session().attribute(SESSION_NAME, username);

            }
            else{
                if(!pass.equals(passconfirm))
                    request.session().attribute(SESSION_NAME, "/mismatchedPassRegistration");
                else
                    request.session().attribute(SESSION_NAME, "/invalidRegistration");
            }
            response.redirect("/");
            return null;
        });

        get("/buyers", (request, response) -> {
            return DatabaseConnector.getBuyersJSON();
        });

        get("/sellers", (request, response) -> {
            return DatabaseConnector.getSellersJSON();
        });

        post("/addseller", (request, response) -> {
            DatabaseConnector.updateUserType(request.queryParams("newseller"), "Seller");
            response.redirect("/");
            return null;
        });

        post("/removeseller", (request, response) -> {
            DatabaseConnector.updateUserType(request.queryParams("exseller"), "Buyer");
            response.redirect("/");
            return null;
        });

        post("/sell", (request, response) -> {
            String title = request.queryParams("title");
            String year = request.queryParams("year");
            String genre = request.queryParams("genre");
            int stock = Integer.parseInt(request.queryParams("stock"));
            float price = Float.parseFloat(request.queryParams("price"));

            new Movie(title, year, genre, stock, price);

            response.redirect("/");
            return null;
        });

        //Possible search function
        post("/search", (request, response) -> {
            String searchTitle = request.queryParams("movie_title");

            for(int i = 0; i < DatabaseConnector.getMovies().size(); i++)
            {
                if(DatabaseConnector.getMovies().contains(searchTitle))
                {
                    break;
                }
                else
                {
                    return null;
                }
            }
            return searchTitle;
        });
    }
}
