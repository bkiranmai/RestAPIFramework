package resources;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathUtil {
	
	
	public static String GetJsonPath(Response response,String key ) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
