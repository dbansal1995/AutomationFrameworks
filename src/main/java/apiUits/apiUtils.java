package apiUits;

import io.restassured.path.json.JsonPath;

public class apiUtils {

    public static JsonPath jsonPath(String response){

        JsonPath js=new JsonPath(response);

        return js;

    }

}
