/**
 * Created by jose on 3/23/16.
 */
import static spark.Spark.*;

public class Main {

    private static final String SESSION_NAME = "username";

    public static void main(String[] args) {
        staticFileLocation("public");
        port(8000);
        post("/login", (request, response) -> {
            String name = request.queryParams("name");
            String pass = request.queryParams("pass");
            System.out.println(name);
            System.out.println(pass);
            if (name != null) {
                request.session().attribute(SESSION_NAME, name);
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
