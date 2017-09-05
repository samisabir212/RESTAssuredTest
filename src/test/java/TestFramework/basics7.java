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


public class basics7 {

	Properties prop = new Properties();


	@BeforeTest
	public void getData() throws IOException

	{
		
		FileInputStream fis=new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPICreateComment()
	{
		//Creating Issue/Defect

		RestAssured.baseURI = prop.getProperty("JIRAHOST"); //inizialize the baseURI


		//what the body looks like without optimization by usuing forward slash begin with quote end with quote and use concatanation operator
	/*	{
			"body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.",
				"visibility": {
			"type": "role",
					"value": "Administrators"
		}
		}*/


	/*
	* session
	* */


		//creating response to hit an issue feature
		//updating the 10035 issue in jira that was created with the basics6 class reponse
		Response res = given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{ \"body\": \"Inserting comment from the automation code from basics7.java\","+
    "\"visibility\": {"+
        "\"type\": \"role\","+ //role of the type is admin
        "\"value\": \"Administrators\" }"+
"}").when().
				//make sure the issue # is correct to the specific place
		post("/rest/api/2/issue/10037/comment/").then().statusCode(201).extract().response(); //post is the resouces after the base URI the 10035 is the location of the issue


		   JsonPath js= ReusableMethods.rawToJson(res);
		   String id=js.get("id");
		   System.out.println(id);
		
		
		
		
		
		
		
		
		
		
		
		  
		
				
		
		
		
		}
}
