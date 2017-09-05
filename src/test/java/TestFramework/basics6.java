package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class basics6 {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis = new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPICreateIssue()
	{
		//Creating Issue/Defect

		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		//creating sessionc
		Response res = given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID=" + ReusableMethods.getSessionKEY()).
		body("{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"SAMISABIR\""+
       "},"+
       "\"summary\": \"issue from Java Framework\","+
       "\"description\": \"Creating my second bug\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();


		/*
		* once you get the response
		* must convert raw response into string
		* then again convert from string into json
		* */
		   JsonPath js = ReusableMethods.rawToJson(res);
		   String id = js.get("id");


				
		
		
		
		}
}
