package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before()
	public void GetPlace_id() throws IOException {
		
		FunctionalityPlaceAPI fpa = new FunctionalityPlaceAPI();
		
		if(FunctionalityPlaceAPI.placeid==null) {
			
		 fpa.the_Base_URl_and_Add_Place_Payload();
		 fpa.user_calls_the_with_http_method("AddPlaceAPI", "Post");
		 fpa.in_the_response_payload_is("status", "OK");
		}
	}
}
