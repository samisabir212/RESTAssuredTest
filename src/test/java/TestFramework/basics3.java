package TestFramework;
import static io.restassured.RestAssured.given;
import TestFramework.resources;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import TestFramework.payLoad;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics3 {
	
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{

		//how to initialize the Properties file to extract test data for easy readablilty
		FileInputStream fis=new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}

	@Test
	public void AddandDeletePlace()
	{
		
		//Task 1- Grab the response
		RestAssured.baseURI= prop.getProperty("HOST");
		Response res = given().

				/*
				* the body is coming from a seperate class called payload from a method called getPostData()
				* */

		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).



		extract().response(); //this whole response ibeing passed to the res variable above from the Response class instance

		System.out.println(res);


		// Task 2- Grab the Place ID from response
		
		String responseString=res.asString(); //converting res to a string so we can print out and see it
		System.out.println(responseString);

		JsonPath js= new JsonPath(responseString);

		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		
		//Task 3 place this place id in the Delete request
		given().
		queryParam("key","AIzaSyDzqysuFEAF3kUc_1x6eB9GCZ5Md2alNA0").
		
		body("{"+
  "\"place_id\": \""+placeid+"\""+
"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
		
	}
}
