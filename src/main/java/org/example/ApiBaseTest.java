package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.AddBook;
import utils.CommonUtilities;

import java.io.File;
import java.util.Map;

public class ApiBaseTest {


    public String baseURL(){

        return RestAssured.baseURI="https://rahulshettyacademy.com";

    }

    public Response post_request(String payload,String path){
        RestAssured.baseURI = baseURL();
        RestAssured.useRelaxedHTTPSValidation();
        Response response=RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).log().all()
                .body(payload).when().post(path);
        System.out.println("Status code: " + response.getStatusCode());

        return response;

    }

    public Response post_request_file(File payload, String path){
        String Endpoint = baseURL();
        Response respone=RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(new File(System.getProperty("user.dir")+""))
                .when().post(path);

        return respone;
    }

    public String extractResponse(Response response){

       // response.then().log().all().assertThat().statusCode(status_code);
        String response_body= response.then().extract().body().asString();
        System.out.println(response_body);
        return response_body;
    }

    public <T> T deserializeJson(Response response, Class<T> responseClass) {
        return response.as(responseClass);
    }

    public void request_spec_builder(){
        RequestSpecBuilder req=new RequestSpecBuilder();

        req.setBasePath(baseURL()).setContentType(ContentType.JSON);

    }


    public Response post_request_pojo(String path,Object object){
        String Endpoint = baseURL();
//         object=new AddBook("Divyansh QA Book",CommonUtilities.generateRandomString(5),CommonUtilities.generateRandomNumeric(////6),"Divyansh bansal");
//        ab.setAuthor("Divyansh Bansal");
//        ab.setName("Divyansh QA Book");
//        ab.setIsbn(CommonUtilities.generateRandomString(5));
//        ab.setAisle(CommonUtilities.generateRandomString(6));
        Response respone=RestAssured.given().contentType(ContentType.JSON).log().all()
                .body(object)
                .post(path);

        return respone;
    }

    public Response getRequest(String Resourcepath, Map queryParam){

        String baseURL=baseURL();

        Response response=RestAssured.given().log().all().baseUri(baseURL).contentType(ContentType.JSON).queryParams(queryParam)
                .when().get(Resourcepath);
        System.out.println(response.asPrettyString());
        return  response;
    }

    public Response deleteRequestPojo(Object object,String path){
        RestAssured.baseURI=baseURL();
        Response response=RestAssured.given().contentType(ContentType.JSON).body(object).log().all()
                .when().delete(path);
        System.out.println(response.body());
        return response;
    }

}
