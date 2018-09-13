package com.ds.movies.helpers;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Movie {
	
	private String name;
	private String director;
	private int yearOfProduction;
	private int noOfAwards;
	private String[] actors;
	private String[] tags;
	
	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Movie(String name, String director, int yearOfProduction, int noOfAwards, String[] actors, String[] tags) {
		super();
		this.name = name;
		this.director = director;
		this.yearOfProduction = yearOfProduction;
		this.noOfAwards = noOfAwards;
		this.tags = tags;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	public int getNoOfAwards() {
		return noOfAwards;
	}
	public void setNoOfAwards(int noOfAwards) {
		this.noOfAwards = noOfAwards;
	}
	public String[] getActors() {
		return actors;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", director=" + director + ", yearOfProduction=" + yearOfProduction
				+ ", noOfAwards=" + noOfAwards + ", actors=" + Arrays.toString(actors) + ", tags="
				+ Arrays.toString(tags) + "]";
	}
	
	

}
