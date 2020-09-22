package resources;

public enum EnumAPIs {
	
	
	    AddPlaceAPI("/maps/api/place/add/json"),
		GetPlaceAPI("/maps/api/place/get/json"),
		DeletePlaceAPI("/maps/api/place/delete/json"),
		UpdatePlaceAPI("/maps/api/place/update/json");

	    private String resource;
	    
		EnumAPIs(String resource) {
			
			this.resource = resource;
			
		}
		
		public String CallAPI() {
			return resource;
		}
	

}
