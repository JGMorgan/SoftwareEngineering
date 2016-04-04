/**
 * Created by jose on 3/23/16.
 */
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class Main {

    private static final String SESSION_NAME = "username";

    public static void main(String[] args) {
        staticFileLocation("public");
        port(8000);
        post("/entry", (request, response) -> {
            String name = request.queryParams("name");
            System.out.println(name);
            if (name != null) {
                request.session().attribute(SESSION_NAME, name);
            }
            response.redirect("/");
            return null;
        });

    }
}
