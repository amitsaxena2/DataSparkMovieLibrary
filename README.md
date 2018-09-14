# DataSparkMovieLibrary

Author: @AmitSaxena

API End-Points are contained in the class /MovieLibrary/src/com/ds/movies/apis/MoviesAPI.java

Code contains a POJO called Movie in class /MovieLibrary/src/com/ds/movies/helpers/Movie.java

Data Source Files

The AIP refers to the two data source files that should be located at the following locations:

Movie Details File: System.getProperty("user.home")+"/dsfiles/MovieDetails.txt"
and 
Tags File: System.getProperty("user.home")+"/dsfiles/TagFile.txt"

API Details

1. GET movie details API (i.e. given movie name return details)
http://localhost:8080/MovieLibrary/rest/movie/<Movie Name>

2. API based on the first 3 or more characters the user types
http://localhost:8080/MovieLibrary/rest/movie/suggestions/<1 or more chars>

3. GET related movies API.
http://localhost:8080/MovieLibrary/rest/movie/relatedMovies/<Movie Name>

4. The Admin should have an option to update movie tags
http://localhost:8080/MovieLibrary/rest/movie/updateMovie/ 

Sample Request Object 

{"name":"Black Panther","director":"Ryan Coogler","yearOfProduction":2018,"noOfAwards":0,"actors":["Chadwick Boseman","Lupita Nyong\u0027o","Danai Gurira","Michael B. Jordan"],"tags":["action","sci-fi","adventure","marvel","horror"]}
