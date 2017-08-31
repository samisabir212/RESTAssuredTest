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


/*
* THIS TEST IS USING JSON
* */
public class basics3 {
	
	Properties prop=new Properties(); //this has to be declared globally which is..
	@BeforeTest
	public void getData() throws IOException
	{

		//how to initialize the Properties file to extract test data for easy readablilty
		FileInputStream fis=new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);
		
	}

	@Test
	public void AddandDeletePlace()
	{

		/*
		* data will be in raw
		* must convert into string
		* then convert into json format
		* and then get the place_id parameter with json class
		* then print out the resonse
		* it will not work if you dont convert to json
		* */
		
		//Task 1- Grab the response look at env properties file
		//baseuri is https://maps.googleapis.com
		RestAssured.baseURI= prop.getProperty("HOST");

		//this is the given query stored in res Reponse class as raw
		Response res = given().

				/*
				* the body is coming from a seperate class called payload from a method called getPostData()
				* BODY DATA IS IN PAYLOAD CLASS
				* also the place post data which is the RESOURSE is coming from the resource Class
				* from a method called placePostData()
				* */

		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).



		extract().response(); //this whole response being passed to the res variable above from the Response class instance

		System.out.println("incorrect print out " + res); //you need to convert it this out put is not correct


		// Task 2- Grab the Place ID from response

		String responseString = res.asString(); //converting res to a string so we can print out and see it
		System.out.println("printing correct response data = " + responseString);

		//converting responseString int json format using jsonpath class
		//anything you pass will automatically try to convert into json format and store it in js
		//in order to get the parameter place id
		JsonPath js = new JsonPath(responseString);

		String placeid = js.get("place_id"); //getting the json place_id by getting the responseString query
		System.out.println("this is the place id = "+  placeid);
		
		
		//Task 3 place this place id in the Delete request
		//THIS IS THE DELETE REQUEST
		given().
		queryParam("key","AIzaSyDzqysuFEAF3kUc_1x6eB9GCZ5Md2alNA0").
		
		body("{"+
  		"\"place_id\": \""+placeid+"\""+ "}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
		
	}
}
