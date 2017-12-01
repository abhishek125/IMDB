package org.abhi.spring.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.Callable;

import org.abhi.spring.model.Movie;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConcurrentRatingHelper implements  Callable<Movie>{

	protected String movieName;
	protected String year;
	public ConcurrentRatingHelper(String movieName,String year) {
	this.movieName=movieName;
	this.year=year;
	}
	@Override
	public Movie call() {
		return makeAPICall("http://www.theimdbapi.org/api/find/movie?title=" + movieName + "&year=" + year);

	}
	protected Movie makeAPICall(String url) {
		//System.out.println(Thread.currentThread().getName()+" started");
		String responseString = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			httpClient.getConnectionManager().shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Movie movie=null;
		try {
			movie = setValues(responseString,year,URLDecoder.decode(movieName,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName()+" ended");
		return movie;
	}

	protected Movie setValues(String responseString,String year,String movieName) {
		Movie movie = new Movie();
		if (!responseString.equals("null")) {
			JSONArray jsonarray = new JSONArray(responseString);
			JSONObject json = jsonarray.getJSONObject(0);
			movie.setName((String) json.get("title"));
			movie.setImdbId((String) json.get("imdb_id"));
			movie.setYear((String) json.get("year"));
			movie.setRating((String)json.get("rating"));
			movie.setVotes((String) json.get("rating_count"));
			movie.setPlot((String) json.get("description"));
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
