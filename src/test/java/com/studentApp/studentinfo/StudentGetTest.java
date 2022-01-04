package com.studentApp.studentinfo;

import com.studentApp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void getSingleStudentInfo(){
        Response response = given()
                .pathParam("id",5)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void searchStudentWithParameter(){
//*********this is to use 2 value*******
        HashMap<String,Object> qparams = new HashMap<>();
qparams.put("programme","Financial Analysis");
qparams.put("limit",2);
        Response response = given()
                //**********query parameter*******
                //  .queryParam("programme" , "Financial Analysis")
                //  .queryParam("limit",2)
                .queryParams(qparams)
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();


    }


    }

