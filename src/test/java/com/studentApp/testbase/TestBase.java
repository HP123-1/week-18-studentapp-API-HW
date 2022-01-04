package com.studentApp.testbase;

import org.junit.BeforeClass;
import io.restassured.RestAssured;

public class TestBase {
@BeforeClass
    public static void inIt(){
RestAssured.baseURI ="http://localhost";
RestAssured.port=8080;
RestAssured.basePath = "/student";

}
}
