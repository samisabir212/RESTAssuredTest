package TestFramework;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

public class basics2 {

	//THIS IS POST

	
	@Test
	public void createPlaceAPI()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		
		queryParam("key","AIzaSyDzqysuFEAF3kUc_1x6eB9GCZ5Md2alNA0").
		body(

				/*
				* you will find all this data from the webservies documentation
				* https://developers.google.com/places/web-service/add-place
				* scroll down
				* add it to post mate and send the post with correct key api and test it
				* */

				"{"+
  "\"location\": {"+
    "\"lat\": -33.8669710,"+
    "\"lng\": 151.1958750"+
  "},"+
  "\"accuracy\": 50,"+
  "\"name\": \"Google Shoes!\","+
  "\"phone_number\": \"(02) 9374 4000\","+
  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
  "\"types\": [\"shoe_store\"],"+
  "\"website\": \"http://www.google.com.au/\","+
  "\"language\": \"en-AU\""+
"}"

		).
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		

	}
}
