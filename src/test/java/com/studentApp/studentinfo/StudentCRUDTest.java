package com.studentApp.studentinfo;

import com.studentApp.StudentAppPojo;
import com.studentApp.testbase.TestBase;
import com.studentApp.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentCRUDTest extends TestBase {
    static String firstName = "Hinal" + TestUtils.getRandomValue();
    static String lastName = "Oscar" + TestUtils.getRandomValue();
    static String programme = "Api Testing ";
    static String email = "abcde@yahoo.com" + TestUtils.getRandomValue();
    static int studentId;


    @Test
    public void postTest001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Selenium");

        StudentAppPojo studentpojo = new StudentAppPojo();//object
        studentpojo.setFirstName(firstName);
        studentpojo.setLastName(lastName);
        studentpojo.setEmail(email);
        studentpojo.setProgramme(programme);
        studentpojo.setCourses(courseList);


        Response response = given()
                .header("Content-Type", "application/json")
                .body(studentpojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void getTest002() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =
                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);
        studentId = (int) value.get("id");
    }


    @Test
    public void getTest003() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        firstName = firstName + "_Updated";

        List<String> courseList = new ArrayList<>();
        courseList.add("Scala");
        courseList.add("Java");

        StudentAppPojo studentPojo = new StudentAppPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        given()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put("/{studentID}")
                .then().log().all().statusCode(200);

        HashMap<String, Object> value =

                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);


    }

    @Test

    public void deleteTest004() {
        given()
                .pathParam("studentId", studentId)
                .when()
                .delete("/{studentId}")
                .then()
                .statusCode(204);
        given()
                .pathParam("studentId", studentId)
                .when()
                .get("/{studentId}")
                .then()
                .statusCode(404);
    }


}





