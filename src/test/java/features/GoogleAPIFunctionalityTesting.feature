Feature: Functionality Validating of Place API's

@smoke
Scenario: Verify Add Place API with valid Request Url
Given The Base URl and Add Place Payload 
When  User calls the "AddPlaceAPI" with "Post" http method
Then  user call is successful with status 200
And   "status" in the response payload is "OK"

@smoke
Scenario: Verify Add Place API with Invalid Request Url
Given The Base URl and Add Place Payload 
When  User calls the AddPlaceAPI "/maps/api/place/json" with Post http method
Then  user call is unsuccessful with status 404 not found

@GetPlaceAPI
Scenario: Verify GetPlaceAPI with valid Request Url
Given The Base URl and Get Place Payload 
When  User calls the "GetPlaceAPI" with "Get" http method
Then   user call is successful with status 200

@UpdatePlaceAPI
Scenario: Check whether UpdatePlaceAPI is successfully updating the address
Given The Base URl and Update Place Payload 
When  User calls the "UpdatePlaceAPI" with "Put" http method
Then  user call is successful with status 200
And   "msg" in  response payload is "Address successfully updated"

@DeletePlaceAPI
Scenario: Check whether DeletePlaceAPI is successfully updating the address
Given The Base URl and  Delete Place Payload 
When  User calls the "DeletePlaceAPI" with "Delete" http method
Then  user call is successful with status 200
And   "status" in  response payload is "OK"
