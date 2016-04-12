/**
 * Created by jose on 3/23/16.
 */
import static spark.Spark.*;

public class Main {

    private static final String SESSION_NAME = "username";

    public static void main(String[] args) {
        staticFileLocation("public");
        port(8000);
        createRoutes();

    }

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
            }
            response.redirect("/");
            return null;
        });

        post("/", (request, response) -> {
            String search = request.queryParams("search");
            System.out.println(search);
            response.redirect("/");
            return null;
        });
    }
}
