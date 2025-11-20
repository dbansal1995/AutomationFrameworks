package org.apiTestCases;

import apiUits.apiUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.responses.GetCourseDetails;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthTestCase extends ApiBaseTest {

    @Test
    public void getCourseDetails() throws IOException {

        Map<String,String> formData=new HashMap<>();
        formData.put("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        formData.put("client_secret","erZOWM9g3UtwNRj340YYaK_W");
        formData.put("grant_type","client_credentials");
        formData.put("scope","trust");

        Response response=given().log().all().spec(reqSpecBuilderWithFormData(formData))
                .when().post("/oauthapi/oauth2/resourceOwner/token");
        String responseTokenBody=extractResponse(response);

        JsonPath js=apiUtils.jsonPath(responseTokenBody);
        String token=js.get("access_token");
        System.out.println("Token "+token);
        Assert.assertEquals(js.getInt("expires_in"),3600);

        Map<String,String > getToken=new HashMap();
        getToken.put("access_token",token);

        Response res=getRequest("oauthapi/getCourseDetails",getToken);
        String getcourseDetailsRes=extractResponse(getRequest("oauthapi/getCourseDetails",getToken));

        System.out.println(getcourseDetailsRes);

        deserializeJson(res,GetCourseDetails.class);
        GetCourseDetails gc = res.as(GetCourseDetails.class);

        Assert.assertTrue(gc.getLinkedIn().contains("rahul-shetty-trainer"));

        Assert.assertTrue(gc.getCourses().getWebAutomation().size()>1);
        //Print all course title for Api courses
        for(int i=0;i<gc.getCourses().getApi().size();i++){
            System.out.println(gc.getCourses().getApi().get(i).getCourseTitle());
        }

    }


}
