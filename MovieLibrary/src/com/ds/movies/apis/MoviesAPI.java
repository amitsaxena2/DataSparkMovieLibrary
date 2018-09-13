package com.ds.movies.apis;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.ds.movies.helpers.Movie;
import com.ds.movies.helpers.MovieHelper;
import com.ds.movies.helpers.UnauthorizedRequest;
import com.google.gson.Gson;

/*
 * This class contains the APIs pertaining to Movies.
 */

@Path("/movie")
public class MoviesAPI {
 
	@GET
	@Path("/{param}")
	/*
	 * http://localhost:8080/MovieLibrary/rest/movie/Johnny%20English
	 */
	public String getMovie(@PathParam("param") String movieName) {
		MovieHelper movieHelper = new MovieHelper();
		return new Gson().toJson(movieHelper.getMovieDetails(movieName));
 
	}
	
	@GET
	@Path("/suggestions/{param}")
	/*
	 * http://localhost:8080/MovieLibrary/rest/movie/suggestions/Jo
	 */
	public String getSuggestions(@PathParam("param") String movieName) {
		MovieHelper movieHelper = new MovieHelper();
		return new Gson().toJson(movieHelper.getSuggestions(movieName));
 
	}
	
	@GET
	@Path("/relatedMovies/{param}")
	/*
	 * http://localhost:8080/MovieLibrary/rest/movie/relatedMovies/Black%20Panther
	 */
	public String getRelatedMovies(@PathParam("param") String movieName) {
		MovieHelper movieHelper = new MovieHelper();
		return new Gson().toJson(movieHelper.getRelatedMovies(movieName));
 
	}
	
	@POST
	@Path("/updateMovie")
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	 * http://localhost:8080/MovieLibrary/rest/movie/updateMovie/
	 * 
	 * Sample Request Object 
	 * {"name":"Black Panther","director":"Ryan Coogler","yearOfProduction":2018,"noOfAwards":0,"actors":["Chadwick Boseman","Lupita Nyong\u0027o","Danai Gurira","Michael B. Jordan"],"tags":["action","sci-fi","adventure","marvel","horror"]}
	 */
	public String updateMovie(@Context HttpHeaders headers,String movieJson) {
		if (headers != null) {
	       for (String header : headers.getRequestHeaders().keySet()) {
	          System.out.println("Header:"+header+
	                             "Value:"+headers.getRequestHeader(header));
	       }
	   }
		System.out.println(headers.getRequestHeader("authtoken"));
		if(headers.getRequestHeader("authtoken").toString().replace("[", "").replace("]", "").equalsIgnoreCase("amitsaxena")) {
			MovieHelper movieHelper = new MovieHelper();
			return new Gson().toJson(movieHelper.updateMovie(new Gson().fromJson(movieJson, Movie.class)));
		}else {
			return new Gson().toJson(new UnauthorizedRequest());
		}
 
	}
}