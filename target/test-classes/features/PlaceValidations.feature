@EndToEnd
Feature: Validating Place API's

Scenario Outline: Verify if place is successfully added using AddPlaceAPI
Given The BaseURL and AddPlace payload with "<name>" "<phonenumber>" "<language>"
When  User calls "AddPlaceAPI" with "Post" http request
Then  User call is succesful with StatusCode 200 
And   "status" in response payload is "OK"
And   "scope" in response payload is "APP"
And   unique place_id is generated and verify that maps to "<name>" field in "GetPlaceAPI" response


Examples:

|name        | phonenumber         |language |
|Gaasterland |(+91) 983 893 3937   |english  |
|karellaan   |(+51) 989 893 3005   |telugu   |
|buitenlaan  |(+31) 980 893 2591   |dutch    |
|nadenlaan   |(+21) 889 443 3543   |german   |
|agenenlaan  |(+61) 989 893 3537   |spanish  |
|puntlaan    |(+01) 909 763 3894   |tamil    |


Scenario: Verify if place is successfully updated using UpdatePlaceAPI
Given  Update place payload with place_id and address"New west, India"
When   User call "UpdatePlaceAPI" with "Put" http request
Then   User call is succesful with StatusCode 200
And    "msg" in response payload is "Address successfully updated"
And    Verify that "address" is succesfully updated by calling GetPlaceAPI


Scenario: verify created place is successfully deleted using DeleteplaceAPI
Given     The BaseURL and delete payload place_id
When      Now User calls "DeletePlaceAPI" with "Delete" http request
Then      User call is succesful with StatusCode 200 
And       "status" in response payload is "OK"
And       verify the given place_id record is succesfully deleted by calling GetPlaceAPI
And      "msg" in response payload is "Get operation failed, looks like place_id  doesn't exists"
