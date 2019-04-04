package com.api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

//import static org.hamcrest.CoreMatchers


public class RestAssuredExampleClass {

	@BeforeClass
	public void setUp() {
		baseURI = "https://www.apitesting.dev.cc/wp-json";
		basePath = "/wp/v2";
	}
	
	@Test
	public void deleteWpExample() {		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")  //<--preemptive: will make sure to pass this authorization at the beginning
		.pathParam("id", "33")
		.queryParam("force", true)
		.delete("/posts/{id}")
		.then()
		.log().all()
		.statusCode(200)
		.body("deleted", is(true));
	}
	
	//@Test
	public void deleteWpTrashExample() {		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")  //<--preemptive: will make sure to pass this authorization at the beginning
		.pathParam("id", "29")
		.delete("/posts/{id}")
		.then()
		.log().all()
		.statusCode(200)
		.body("status", equalTo("trash"));
	}
	
	//@Test
	public void putExample() {		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")  //<--preemptive: will make sure to pass this authorization at the beginning
		.when()
		.contentType(ContentType.JSON)  // <--this is an Enum
		.when()
		.body("{\"title\" : \"UpdatedAgain2\" , "
				+ "\"content\" : \"Upppp\" , "
				+ "\"status\" : \"publish\"}")
		.log().all()
		.pathParam("id", "29")
		.put("/posts/{id}")
		.then()
		.log().all()
		.statusCode(200)
		.body("title.raw", equalTo("UpdatedAgain2"));
	}
	
	//@Test
	public void postExample() {		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")  //<--preemptive: will make sure to pass this authorization at the beginning
		.when()
		.contentType(ContentType.JSON)  // <--this is an Enum
		.when()
		.body("{\"title\" : \"postExampleRestAssured\" , "
				+ "\"content\" : \"My content is Awesome\" , "
				+ "\"status\" : \"publish\"}")
		.log().all()
		.post("/posts")
		.then()
		.log().all()
		.statusCode(201);
	}
	
	//@Test
	public void getExample() {
		given()
		.relaxedHTTPSValidation()
		.when()
		.log().all()
				//.get("/posts/{id}", 26) // <-returns single resource
				.pathParam("id", "26").get("/posts/{id}")  //<-- same than above commented line
				.then()
				.log().all()
				.statusCode(200)
				.body("id", equalTo(26))
				.body("title.rendered", equalTo("updating title for this resource"))
				.body("sticky", equalTo(false));
	}
}
