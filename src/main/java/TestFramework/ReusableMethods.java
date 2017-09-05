package TestFramework;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	


	//converting to xml
	public static XmlPath rawToXML(Response r)
	{
	
		
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
		
	}


	//converting to json
	public static JsonPath rawToJson(Response r)
	{ 
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		return x;
	}

	//jira access key setup "Reusable method"
	public static String getSessionKEY()
	{
		RestAssured.baseURI= "http://localhost:8080";

		Response res = given().header("Content-Type", "application/json").
		body("{ \"username\": \"samisabir212\", \"password\": \"Africaunite2012\" }").
		when().
		post("/rest/auth/1/session").then().statusCode(200).
		extract().response();
		 JsonPath js = ReusableMethods.rawToJson(res);
		String sessionid= js.get("session.value");
		return sessionid;
	}
	
	

}
