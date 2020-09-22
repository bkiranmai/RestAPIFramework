package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.AddPlacePayload;
import resources.EnumAPIs;
import resources.JsonPathUtil;
import resources.specUtils;

public class FunctionalityPlaceAPI {
	
	RequestSpecification req;
	Response res;
	static String placeid;
	
	@Given("The Base URl and Add Place Payload")
	public void the_Base_URl_and_Add_Place_Payload() throws IOException {
	    req = given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.AddPayload());
	}

	@When("User calls the {string} with {string} http method")
	public void user_calls_the_with_http_method(String resource, String httpmethod) {
	    
		EnumAPIs API = EnumAPIs.valueOf(resource);
		System.out.println(API.CallAPI());
		
		if(httpmethod.equalsIgnoreCase("Post")) {
		res =	req.when().post(API.CallAPI());
			}
		else if 
			(httpmethod.equalsIgnoreCase("Get")){
			res = req.when().get(API.CallAPI());
		}
		else if
			(httpmethod.equalsIgnoreCase("Put")){
			res = req.when().put(API.CallAPI());
		}
		else if
		(httpmethod.equalsIgnoreCase("Delete")){
		res = req.when().delete(API.CallAPI());
	}
		
	}

	@Then("user call is successful with status {int}")
	public void user_call_is_successful_with_status(Integer int1) {
		
		assertEquals(res.getStatusCode(),200);
	   	}
	@Then("{string} in the response payload is {string}")
	public void in_the_response_payload_is(String ActValue, String expvalue) {
	   
		 String value = JsonPathUtil.GetJsonPath(res, ActValue);
	      System.out.println(value);
	      assertEquals(value,expvalue);
	      placeid = JsonPathUtil.GetJsonPath(res, "place_id");

	}

    //verify with invalid Url end point
	@When("User calls the AddPlaceAPI {string} with Post http method")
	public void user_calls_the_AddPlaceAPI_with_Post_http_method(String Uri) {
	   res =  req.when().post(Uri);
	}

	@Then("user call is unsuccessful with status {int} not found")
	public void user_call_is_unsuccessful_with_status_not_found(Integer int1) {
		
		assertEquals(res.getStatusCode(),404);
		System.out.println("Verified by passing invalid url");
	}
	
	//Verify getPlace api by passing place_id
	
	@Given("The Base URl and Get Place Payload")
	public void the_Base_URl_and_Get_Place_Payload() throws IOException {
	    req = given().spec(specUtils.RequestSpecUtils()).queryParam("place_id", placeid);
	}

	//Verify update Place api by passing place_id and address
	@Given("The Base URl and Update Place Payload")
	public void the_Base_URl_and_Update_Place_Payload() throws IOException {
	   req =  given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.UpdatePayload(placeid, "Amsterdam"));
	}
	@Then("{string} in  response payload is {string}")
	public void in_response_payload_is(String ActValue, String expvalue) {
		 String value = JsonPathUtil.GetJsonPath(res, ActValue);
	      System.out.println(value);
	      assertEquals(value,expvalue);
	}
	
	// Verify the created place_id details is deleted with delete place API
	@Given("The Base URl and  Delete Place Payload")
	public void the_Base_URl_and_Delete_Place_Payload() throws IOException {
	    req = given().spec(specUtils.RequestSpecUtils()).body(AddPlacePayload.DeletePayload(placeid));
	}

}
