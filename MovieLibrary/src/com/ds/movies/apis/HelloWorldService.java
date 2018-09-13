package com.ds.movies.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/*
 * Added the class to test if the APIs are up or not.
 */
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Hello : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
}
