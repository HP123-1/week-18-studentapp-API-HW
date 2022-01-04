package com.studentApp.studentinfo;

import com.studentApp.StudentAppPojo;
import com.studentApp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {

    @Test
    public void putStudentDetails() {
        List<String> courseList = new ArrayList<>();
        courseList.add("java");
        courseList.add("selenium");

        StudentAppPojo studentPojo = new StudentAppPojo();
        studentPojo.setFirstName("Vernon");
        studentPojo.setLastName("Testng1");
        studentPojo.setEmail("abcdef123567@yahoo.com");
        studentPojo.setProgramme("Automation");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 102)
                .body(studentPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }
}
