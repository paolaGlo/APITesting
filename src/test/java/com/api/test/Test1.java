package com.api.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test1 {

	// given {{base_url}}/time/leap?timestamp=1987-10-23
	// when i send request
	// then I should get 200 result

	@Test
	public void testing() {
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.apitesting.dev.cc/wp-json/wp/v2/posts")
		.then()
		.statusCode(200);
	}
}
