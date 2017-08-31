package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import TestFramework.ReusableMethods;

public class basics5 {

	@Test
public void extractingNamesAPI()
{

	/*
	* Requirement scenrio
	* interview question
	* print all the names of the listed result
	*
	* */



		// TODO Auto-generated method stub

		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";


		//this is the body in res
		Response res = given().

		       param("location","-33.8670522,151.1957362").
		       param("radius","500").
		       param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").log().all(). //<--- we are logging all!!
		       when().
		       get("/maps/api/place/nearbysearch/json").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		       body("results[0].name",equalTo("Sydney")).and().
		       body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		       header("Server","pablo").log().body(). ///adding log to the body
		       extract().response();


			//converting  complete body reponse with json method
		   JsonPath js = ReusableMethods.rawToJson(res);

		   //see that getting the array list is written in javascript format.
		   int count = js.get("results.size()"); //getting size of the 'results' of the request and storing it in count variable

		   for(int i = 0; i<count; i++)
		   //for every result in the count print results.name
		   {
			   //System.out.println(i);
			 System.out.println(js.get("results["+i+"].name"));
		   }
		  		System.out.println(count);
		       
		       
		       /*header("dfd","fsdfds").
		       cookie("dsfs","csder").
		       body()*/
		//Status code of the response
		//Content type 
		//Body
		//Header responses
	
}

}
