package com.ds.movies.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class MovieHelper {
	
	List<Movie> moviesList;
	File movieDetailsFile = new File(System.getProperty("user.home")+"/dsfiles/MovieDetails.txt");
	File tagFile = new File(System.getProperty("user.home")+"/dsfiles/TagFile.txt");
	public List<Movie> getSuggestions(String startingChars) {
		
		boolean returnAllMatchingMovies = true;
		return getMoviesList(startingChars, returnAllMatchingMovies);
	}
	public List<Movie> getMovieDetails(String movieName) {
		boolean returnAllMatchingMovies = false;
		return getMoviesList(movieName, returnAllMatchingMovies);

	}
	public List<Movie> getMoviesList(String movieName,boolean fetchAllMatching){
		Movie movie=new Movie();
		moviesList = new ArrayList<Movie>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(movieDetailsFile));
			boolean newMovieFound=false;
			String st;
			String key, value;
			while ((st = br.readLine()) != null) {
				if (st.split(":").length>1) {
					key = st.split(":")[0].trim().toLowerCase();
					value = st.split(":")[1].trim();
					if (key.equalsIgnoreCase("name") && 
							(
									(!fetchAllMatching && value.toLowerCase().equalsIgnoreCase(movieName.toLowerCase())) 
							|| ((fetchAllMatching && value.toLowerCase().startsWith(movieName.toLowerCase())))
									)){
						newMovieFound = true;
						movie=new Movie();
						movie.setName(value);
					} else if (newMovieFound && key.equalsIgnoreCase("director")) {
						movie.setDirector(value);
					} else if (newMovieFound && key.equalsIgnoreCase("year of production")) {
						movie.setYearOfProduction(Integer.parseInt(value));
					} else if (newMovieFound && key.equalsIgnoreCase("noOfAwards")) {
						movie.setNoOfAwards(Integer.parseInt(value));
					} else if (newMovieFound && key.equalsIgnoreCase("actors")) {
						movie.setActors(value.split(","));
						newMovieFound = false;
						movie.setTags(getMovieTags(movie.getName()));
						moviesList.add(movie);
					} 
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return moviesList;
		
	}
	
	public String[] getMovieTags(String movieName){
		String[] tag = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(tagFile));
			String st;
			String key, value;
			while ((st = br.readLine()) != null) {
				if (st.split(":").length>1) {
					key = st.split(":")[0].trim().toLowerCase();
					value = st.split(":")[1].trim();
					System.out.println(key+" : "+value);
					if (key.equalsIgnoreCase(movieName.toLowerCase())){
						tag= value.split(",");
					} 
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tag;
		
	}

	public List<Movie> getAllRelatedMovies(Movie parentMovie){
		Movie movie=new Movie();
		moviesList = new ArrayList<Movie>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(movieDetailsFile));
			boolean newMovieFound=false;
			String st;
			String key, value;
			while ((st = br.readLine()) != null) {
				if (st.split(":").length>1) {
					key = st.split(":")[0].trim().toLowerCase();
					value = st.split(":")[1].trim();
					if (key.equalsIgnoreCase("name")){
						newMovieFound = true;
						movie=new Movie();
						movie.setName(value);
					} else if (newMovieFound && key.equalsIgnoreCase("director")) {
						movie.setDirector(value);
					} else if (newMovieFound && key.equalsIgnoreCase("year of production")) {
						movie.setYearOfProduction(Integer.parseInt(value));
					} else if (newMovieFound && key.equalsIgnoreCase("noOfAwards")) {
						movie.setNoOfAwards(Integer.parseInt(value));
					} else if (newMovieFound && key.equalsIgnoreCase("actors")) {
						movie.setActors(value.split(","));
						newMovieFound = false;
						movie.setTags(getMovieTags(movie.getName()));
						if (!movie.getName().equalsIgnoreCase(parentMovie.getName()) && null != movie.getTags()
								&& isRelated(parentMovie.getTags(), movie.getTags())) {
							System.out.println(new Gson().toJson(movie));
							moviesList.add(movie);
						}
					} 
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return moviesList;
	}
	
	public List<Movie> getRelatedMovies(String movieName) {
		
		Movie movie = getMovieDetails(movieName).get(0);
		return getAllRelatedMovies(movie);
	}
	
	 public boolean isRelated(String a[],String b[]){
	        int commonTagCount = 0;
	        for(int i=0;i<a.length;i++){
	            for(int j=0;j<b.length;j++){
	                if(a[i].equalsIgnoreCase(b[j])){
	                	commonTagCount++;
	                }
	            }
	        }
	        boolean isRelated = false;
	        if(commonTagCount >= 2)
	        	isRelated=true;
        	else
        		isRelated=false;
	        return isRelated;
	    }

	public String updateMovie(Movie movie) {
		List<String> lines;
		try {
			lines = Files.readAllLines(tagFile.toPath(),Charset.defaultCharset());
			changeValueOf(lines,movie.getName(), movie.getTags()); // the name and the value you want to modify
	        Files.write(tagFile.toPath(), changeValueOf(lines,movie.getName(), movie.getTags()), Charset.defaultCharset());
	        return "Record Updated Susccessfully";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
        
	}
	
	private List<String> changeValueOf(List<String> lines,String key, String[] newVal){
		System.out.println("Here .. ");
        List<String> newLines = new ArrayList<String>();
        for(String line: lines){
        	System.out.println("Line : "+line);
            if(line.contains(key)){
                String[] vals = line.split(":");
                newLines.add(vals[0]+": "+Arrays.toString(newVal).replace("[", "").replace("]", ""));
            }else{
                newLines.add(line);
            }

        }
        return newLines;
    }

}
