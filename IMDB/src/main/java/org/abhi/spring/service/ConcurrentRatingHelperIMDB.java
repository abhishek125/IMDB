package org.abhi.spring.service;

import java.util.concurrent.Callable;

import org.abhi.spring.model.Movie;
import org.json.JSONObject;

public class ConcurrentRatingHelperIMDB extends ConcurrentRatingHelper implements Callable<Movie>{
	private String imdbKey;
	public ConcurrentRatingHelperIMDB(String movieName, String year,String key) {
		super(movieName, year);
		this.imdbKey=key;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Movie call() {
		// TODO Auto-generated method stub
		String url="http://www.omdbapi.com/?apikey="+imdbKey+"&t="+movieName+"&year="+year;
		//System.out.print("inside imdb apicall"+url+"\n");
		return makeAPICall(url);
	}
	@Override
	protected Movie makeAPICall(String url) {
		return super.makeAPICall(url);
	}
	
	protected Movie setValues(String responseString,String year,String movieName) {
		Movie movie = new Movie();
		//System.out.println("mystirng"+responseString);
		if (!responseString.equals("null")) {
			JSONObject json = new JSONObject(responseString);
			movie.setName((String) json.get("Title"));
			movie.setImdbId((String) json.get("imdbID"));
			movie.setYear((String) json.get("year"));
			movie.setRating((String)json.get("imdbRating"));
			movie.setVotes((String) json.get("imdbVotes"));
			movie.setPlot((String) json.get("plot"));
		}
		if(responseString.equals("null") || movie.getRating().equals(""))
		{
			movie.setRating("0");
			movie.setVotes("0");
			movie.setName(movieName);
			movie.setImdbId("not available");
			movie.setYear(year);
			movie.setPlot("movie not found");
		}
		return movie;
	}

}
