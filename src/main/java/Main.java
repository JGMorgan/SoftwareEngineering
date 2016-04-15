import com.mongodb.MongoClient;

import static spark.Spark.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.Block;

public class Main {

    private static final String SESSION_NAME = "username";

    public static void main(String[] args) {
        staticFileLocation("public");
        port(8000);
        createRoutes();
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("movies");
        db.getCollection("movies").insertOne(new Document("name", "The Revenant"));
        FindIterable<Document> iterable = db.getCollection("movies").find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
    /**
     * sets up routes to connect to the front end
     */
    public static void createRoutes(){
        get("/username", (request, response) -> {
            String name = request.session().attribute(SESSION_NAME);

            System.out.println("SESSION NAME EXISTS " + name);
            if (name != null){
                return name;
            }else{
                return SESSION_NAME;
            }

        });

        post("/login", (request, response) -> {
            String name = request.queryParams("name");
            String pass = request.queryParams("pass");
            System.out.println(name);
            System.out.println(pass);
            if (name != null) {
                request.session().attribute(SESSION_NAME, name);
                /*
                TODO
                check the database for what type of user a certain username is
                 */
            }
            response.redirect("/");
            return null;
        });

        post("/register", (request, response) -> {
            String username = request.queryParams("user_name");
            String fname = request.queryParams("first_name");
            String lname = request.queryParams("last_name");
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String passconfirm = request.queryParams("password_confirmation");
            System.out.println(fname);
            System.out.println(pass);
            if (username != null) {
                request.session().attribute(SESSION_NAME, username);
            }
            response.redirect("/");
            return null;
        });

        post("/search", (request, response) -> {
            String search = request.queryParams("search");
            System.out.println(search);
            response.redirect("/");
            return null;
        });
    }
}
