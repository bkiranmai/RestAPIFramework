package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import stepDefinitions.AddPlaceStepDefinition;

public class specUtils {
	
	static RequestSpecification req;
	public static RequestSpecification RequestSpecUtils() throws IOException {
		
		if(req==null) {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(GlobalBaseURI("baseURI"))
                .addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static ResponseSpecification ResponseSpecUtils() {
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resspec;
		
	}
	
	public static String GlobalBaseURI(String key) throws IOException {
		
		Properties pro = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\CucumberAPIFramework\\src\\test\\java\\resources\\GlobalValues.Properties");
	    pro.load(fis);
	    String baseuri = pro.getProperty(key);
	    return baseuri;
	}
	
	public static Response GetCall(String placeid) throws IOException {
	
		Response response = given().spec(specUtils.RequestSpecUtils()).queryParam("place_id", placeid)
		                    .when().get("/maps/api/place/get/json");
		return response;
			
	}

}
