package TestFramework;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import TestFramework.ReusableMethods;


/*
* THIS TEST IS USING XML
*
* */
public class basics4 {


	Properties prop=new Properties(); //this has to be declared globally which is..
	@BeforeTest
	public void getData() throws IOException
	{

		//how to initialize the Properties file to extract test data for easy readablilty
		FileInputStream fis=new FileInputStream("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/env.properties");
		prop.load(fis);

	}

	
	@Test
	public void postDataXML() throws IOException
	{

		//post data always goes into the body
		//we are using xml and converting it into string POSTDATA
		String postdata = GenerateStringFromResource("/Users/sami/IdeaProjects/RESTAssuredTest/src/main/java/TestFramework/TestResource/postdata.xml");

		RestAssured.baseURI = "https://maps.googleapis.com";

		System.out.println("printing post data from xml file" +  postdata);

		Response resp = given().
				queryParam("key",prop.getProperty("KEY")).
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response(); //extract gets the reponse




		System.out.println("conversion from raw to xml into string " + resp);


		//we want to see the response out put how it come out curious
		String responseOutput = resp.asString();
		System.out.println("this is the response out put " + responseOutput);


		//converting post data response output from raw to xml in order to see the output
		XmlPath x = ReusableMethods.rawToXML(resp);



		System.out.println("without getting the place_id Response " + x);
		System.out.println(x.get("PlaceAddResponse.place_id")); //generating the place_id value from the placeAddRequest tag
		
		
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		

	}

	/*
	* this is taking post data thats in xml to string to pass into the body
	* */
	public static String GenerateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));

	}
}
