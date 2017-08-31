package TestFramework;

public class payLoad {

	//this payload is being used in basics3 class

	public static String getPostData()
	{

		//this is the payload from the google webservice that is used for an example
		//storing it in b variable and method getPostData will return b which is
		//the pay load
		String b ="{"+
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
				"}";
		
		
		return b;
	}
}
