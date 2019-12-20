Feature: Searching GitHub repositories 
  
    Scenario Outline: Searching GitHub repositories using author name
  	
  	Given User send query param <queryParam> with value <queryParamValue> to search GitHub using <scenarioName>
  	When User makes a GET request with path param /search/repositories 
  	Then User should receive HTTP statuscode <statusCode>
  	And response should contain <totalCount/message>
  	
  	Examples:
  	|scenarioName			 		|queryParam	|queryParamValue			 	|statusCode|totalCount/message|
  	|Valid Author Name 		|q					|user:dimashyshkin		 	|200			 |8									|
  	|Invalid Author Name 	|q					|user:dimashyshaaaaaakin|422			 |Validation Failed	|
  	
  	Scenario Outline: Searching GitHub repositories using pagination information
  	
  	Given User send page parameter <pageParam> with value <pageNumber> along with query param <queryParam> having value <queryParamValue> to search GitHub using <scenarioName>
  	When User makes a GET request with path param /search/repositories 
  	Then User should receive HTTP statuscode <statusCode>
  	And response should contain <totalCount/message>
  	And repsonse should contain <itemCount> items
  	
  	Examples: 	
  	|scenarioName					|queryParam	|queryParamValue			 	|pageParam|pageNumber|statusCode|totalCount/message|itemCount|
  	|Valid Page Range			|q					|droolslanguage:Scala		|page			|2				 |200				|46								 |16			 |
  	|Invalid Page Range		|q					|droolslanguage:Scala		|page			|4				 |200				|46								 |0				 |
  	
  	Scenario Outline: Searching GitHub repositories using sort criteria
  	
  	Given User send sort criteria <sortParam> with value <sortOption> and <order> along with query param <queryParam> having value <queryParamValue> to search GitHub using <scenarioName>
  	When User makes a GET request with path param /search/repositories 
  	Then User should receive HTTP statuscode <statusCode>
  	And response should have items in <order> order
  	
  	Examples: 	
  	|scenarioName					|queryParam	|queryParamValue			 	|sortParam|sortOption  |statusCode|order|
  	|Valid Sort option		|q					|created:2019-01-01			|sort			|updated		 |200				|desc |
  	|Valid Sort option		|q					|created:2019-01-01			|sort			|updated		 |200				|asc  |
  	
  	
  
  	
  	
  	

