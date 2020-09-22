package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.PojoAddPlace;
import pojoClasses.location;

public class AddPlacePayload {
	
	public static PojoAddPlace PayloadAddPlace(String name,String phonenumber,String language) {
		

		PojoAddPlace pap = new PojoAddPlace();
		
		pap.setAccuracy(50);
		pap.setAddress("29, side layout, cohen 09");
		pap.setLanguage(language);
		pap.setName(name);
		pap.setPhone_number(phonenumber);
		pap.setWebsite("http://google.com");
		
		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");
		pap.setTypes(ty);
		
		location L = new location();
		L.setLat(-38.389745);
		L.setLng(33.564323);
		pap.setLocation(L);
		return pap;
		
	}
	
	public static String UpdatePayload(String placeid, String address) {
		
		return "{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String DeletePayload(String place_id) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
	}
	
public static String AddPayload() {
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"30, mainstreet , cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"amstelveen\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
