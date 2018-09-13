Author: @AmitSaxena

This is a maven project which generates a WAR.

The description is given keeping in mind that the WAR is run on Tomcat 8.5 server - 8080 port.

API End-Points are contained in the class /MovieLibrary/src/com/ds/movies/apis/MoviesAPI.java

Code contains a POJO called Movie in class /MovieLibrary/src/com/ds/movies/helpers/Movie.java

API EndPoints

1. GET movie details API (i.e. given movie name return details)
http://localhost:8080/MovieLibrary/rest/movie/<Movie Name>

2. GET API based on the first 3 or more characters the user types
http://localhost:8080/MovieLibrary/rest/movie/suggestions/<1 or more chars>

3. GET related movies API.
http://localhost:8080/MovieLibrary/rest/movie/relatedMovies/<Movie Name>

4. POST API - The Admin should have an option to update movie tags
http://localhost:8080/MovieLibrary/rest/movie/updateMovie/ 

	Since this is available to only Admins, I have included a header key and value, which is validated before servicing the API request

	In Postman, users will have to add a header with following details:

	Key = authToken
	Value = amitsaxena

	Sample Request JSON for POST Request
  
	{"name":"Black Panther","director":"Ryan Coogler","yearOfProduction":2018,"noOfAwards":0,"actors":["Chadwick Boseman","Lupita Nyong\u0027o","Danai Gurira","Michael B. Jordan"],"tags":["action","sci-fi","adventure","marvel","horror"]}
