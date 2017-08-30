package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class basics {

	//look at postmate history for this request

	public String name = "Sydney";
	public String place_id = "ChIJP3Sa8ziYEmsRUKgyFmh9AQM";

	@Test
	public void getPlaceAPI() {
		// TODO Auto-generated method stub

		//BaseURL or Host
		RestAssured.baseURI = "https://maps.googleapis.com";

		given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "500").
				param("key", "AIzaSyDzqysuFEAF3kUc_1x6eB9GCZ5Md2alNA0").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and(). //content type as json request
				//in the body you navigate inbetween "" to extract data get data etccc

						body("results[0].name", equalTo(name)).and().
				body("results[0].place_id", equalTo(place_id)).and().
				header("Server", "pablo").and().
				contentType("application/json; charset=UTF-8");


		       
		       
		       /*header("dfd","fsdfds").
			   cookie("dsfs","csder").
		       body()*/
		//Status code of the response
		//Content type 
		//Body
		//Header responses

	}

}
