package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.PojoAddPlace;
import pojoClasses.location;
import resources.AddPlacePayload;
import resources.EnumAPIs;
import resources.JsonPathUtil;
import resources.specUtils;

public class AddPlaceStepDefinition {
	
	RequestSpecification res;
	Response response ;
	static String placeid;
	static String expAddress;
	
	//Verify Add Place API end to end
	@Given("The BaseURL and AddPlace payload with {string} {string} {string}")
	public void the_BaseURL_and_AddPlace_payload_with(String name, String phonenumber, String language) throws IOException {
		//Build AddPlace response payload
		res= given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.PayloadAddPlace(name, phonenumber, language)); 
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
		
		EnumAPIs API = EnumAPIs.valueOf(resource);
		System.out.println(API.CallAPI());
		
		if(httpmethod.equalsIgnoreCase("Post")) {
		response =	res.when().post(API.CallAPI());
			}
		else if 
			(httpmethod.equalsIgnoreCase("Get")){
			response = res.when().get(API.CallAPI());
		}
		else if
			(httpmethod.equalsIgnoreCase("Put")){
			response = res.when().put(API.CallAPI());
		}
		else if
		(httpmethod.equalsIgnoreCase("Delete")){
		response = res.when().delete(API.CallAPI());
	}
		
		
	}
	@Then("User call is succesful with StatusCode {int}")
	public void user_call_is_succesful_with_StatusCode(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response payload is {string}")
	public void in_response_payload_is(String ActValue, String ExpectedValue) {
	    
		String Value = JsonPathUtil.GetJsonPath(response,ActValue);
		System.out.println(Value );
		 assertEquals(Value,ExpectedValue);
	}
	
	@Then("unique place_id is generated and verify that maps to {string} field in {string} response")
	public void unique_place_id_is_generated_and_verify_that_maps_to_field_in_response( String Expectedname,String resource) throws IOException {
		
	    placeid = JsonPathUtil.GetJsonPath(response, "place_id");
		res = given().spec(specUtils.RequestSpecUtils()).queryParam("place_id", placeid);
		user_calls_with_http_request(resource,"Get");
		String Actualname = JsonPathUtil.GetJsonPath(response, "name");
		System.out.println(Actualname);
		assertEquals(Actualname,Expectedname);
	}

	//verify Update place API end to end
	@Given("Update place payload with place_id and address{string}")
	public void update_place_payload_with_place_id_and_address(String address) throws IOException {
		res = given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.UpdatePayload(placeid, address));
		 expAddress = address;
		 
	}
	@When("User call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		user_calls_with_http_request(resource,method);
	}


	@Then("Verify that {string} is succesfully updated by calling GetPlaceAPI")
	public void verify_that_is_succesfully_updated_by_calling_GetPlaceAPI(String vaddress) throws IOException {
		response = specUtils.GetCall(placeid);
	    String actualAdress =JsonPathUtil.GetJsonPath(response,vaddress);
	    assertEquals(actualAdress, expAddress);
	    System.out.println(actualAdress);
	   }
	
	//Verify end to end scenario of delete API
	@Given("The BaseURL and delete payload place_id")
	public void the_BaseURL_and_delete_payload_place_id() throws IOException {
		
		res = given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.DeletePayload(placeid));
		}
	@When("Now User calls {string} with {string} http request")
	public void Now_user_calls_with_http_request(String resource, String method) {
		user_calls_with_http_request(resource,method);
	}

	@Then("verify the given place_id record is succesfully deleted by calling GetPlaceAPI")
	public void verify_the_given_place_id_record_is_succesfully_deleted_by_calling_GetPlaceAPI() throws IOException {
		
		response = specUtils.GetCall(placeid);
		
	}


}

