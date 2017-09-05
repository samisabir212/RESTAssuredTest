package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class basics6 {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		//loading the env.properties file
		FileInputStream fis = new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPICreateIssue()
	{
		//Creating Issue/Defect

		//inizialize the base URI from properties file
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		//creating responce
		Response res = given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID=" + ReusableMethods.getSessionKEY()). //get the session key
		body("{"+   //enter in body of the Jira functionality api
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"SAMISABIR\""+/**/
       "},"+
       "\"summary\": \"issue from Java Framework testing\","+ //enter summary
       "\"description\": \"creating a bug \","+ //enter description
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+ //select the name of the issue type
       "}"+
   "}}").when(). //use when then post
		post("/rest/api/2/issue").then().statusCode(201).extract().response(); //extract the issue repononse


		/*
		* once you get the response
		* convert from string into json
		* convert raw response into string
		* */
		   JsonPath js = ReusableMethods.rawToJson(res);
		   String id = js.get("id");

		System.out.println(id);


				
		
		
		
		}
}
